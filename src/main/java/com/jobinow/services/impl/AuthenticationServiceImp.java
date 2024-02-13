package com.jobinow.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobinow.exceptions.ResourceNotFoundException;
import com.jobinow.model.dto.requests.AuthenticationRequest;
import com.jobinow.model.dto.requests.RegisterRequest;
import com.jobinow.model.dto.responses.AuthenticationResponse;
import com.jobinow.model.entities.User;
import com.jobinow.model.enums.Role;
import com.jobinow.model.enums.UserStatus;
import com.jobinow.repositories.TokenRepository;
import com.jobinow.repositories.UserRepository;
import com.jobinow.security.JwtService;
import com.jobinow.services.spec.AuthenticationService;
import com.jobinow.services.spec.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * Implementation of the Service class {@link AuthenticationService} for user authentication and token management.
 * This service provides methods to register a new user, authenticate a user, and refresh access tokens.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">ouharri outman</a>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {
    private final JwtService jwtService;
    private final UserService userService;
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Registers a new user and generates access and refresh tokens.
     *
     * @param request Registration request containing user details
     * @return AuthenticationResponse containing access and refresh tokens
     */
    public AuthenticationResponse register(RegisterRequest request) {
        return createUser(request, Role.USER);
    }

    /**
     * Registers a new job seeker and generates access and refresh tokens.
     *
     * @param request Registration request containing job seeker details
     * @return AuthenticationResponse containing access and refresh tokens
     */
    public AuthenticationResponse registerJobSeeker(RegisterRequest request) {
        return this.createUser(request, Role.JOB_SEEKER);
    }

    /**
     * Registers a new manager and generates access and refresh tokens.
     *
     * @param request Registration request containing manager details
     * @return AuthenticationResponse containing access and refresh tokens
     */
    public AuthenticationResponse registerManager(RegisterRequest request) {
        return this.createUser(request, Role.MANAGER);
    }

    /**
     * Registers a new agent and generates access and refresh tokens.
     *
     * @param request Registration request containing agent details
     * @return AuthenticationResponse containing access and refresh tokens
     */
    public AuthenticationResponse registerAgent(RegisterRequest request) {
        return this.createUser(request, Role.AGENT);
    }

    /**
     * Creates a new user, saves it to the database, and generates access and refresh tokens.
     *
     * @param request Registration request containing user details
     * @param role    Role of the user to be created
     * @return AuthenticationResponse containing access and refresh tokens for the newly created user
     */
    public AuthenticationResponse registerRecruiter(RegisterRequest request) {
        return this.createUser(request, Role.RECRUITER);
    }

    /**
     * Creates a new user based on the registration request and assigns a specified role.
     * This method involves setting up a new user with the provided details, encoding the password,
     * and saving the user to the database. It then generates JWT access and refresh tokens for the
     * new user and saves these tokens. This process is essential for registering new users and
     * providing them with the necessary tokens to access secured endpoints.
     *
     * @param request The registration request containing the new user's details such as name, email, and password.
     * @param role    The role to be assigned to the new user, which determines the user's permissions and access.
     * @return AuthenticationResponse containing the generated JWT access and refresh tokens.
     */
    @Transactional
    private AuthenticationResponse createUser(RegisterRequest request, Role role) {
        log.info("Creating a new user with role: {}", role);

        var user = User.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .status(UserStatus.ONLINE)
                .password(passwordEncoder.encode(request.password()))
                .build();
        user.setRole(role);

        var savedUser = repository.save(user);
        log.info("User with ID {} created successfully.", savedUser.getId());

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        userService.saveUserToken(savedUser, jwtToken);
        log.info("Access and refresh tokens generated and saved for user with ID: {}", savedUser.getId());

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * Authenticates a user and generates new access and refresh tokens.
     *
     * @param request Authentication request containing user credentials
     * @return AuthenticationResponse containing new access and refresh tokens
     */
    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );
        } catch (BadCredentialsException ex) {
            log.error("Authentication failed for user: {}", request.email(), ex);
            throw new BadCredentialsException("Invalid credentials");
        } catch (AuthenticationException ex) {
            log.error("Authentication failed for user: {}", request.email(), ex);
            throw new ResourceNotFoundException("Authentication failed");
        }
        var user = repository.findByEmail(request.email())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        user.setStatus(UserStatus.ONLINE);
        repository.save(user);
        userService.revokeAllUserTokens(user);
        userService.saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * Refreshes the access token using a valid refresh token.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse for writing the new tokens
     * @throws IOException if an error occurs during response writing
     */
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                userService.revokeAllUserTokens(user);
                userService.saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    /**
     * Checks whether a token is valid.
     *
     * @param token Token to be checked
     * @return True if the token is valid, false otherwise
     */
    public Boolean checkToken(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return false;
        }
        String jwt = token.substring(7);
        var userEmail = jwtService.extractUsername(jwt);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            return jwtService.isTokenValid(jwt, user);
        }
        return false;
    }

}