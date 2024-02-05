package com.jobinow.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.jobinow.exceptions.ResourceNotFoundException;
import com.jobinow.model.dto.Oauth.TokenDto;
import com.jobinow.model.dto.Oauth.UrlDto;
import com.jobinow.model.dto.requests.AuthenticationRequest;
import com.jobinow.model.dto.requests.RegisterRequest;
import com.jobinow.model.dto.responses.AuthenticationResponse;
import com.jobinow.model.entities.Token;
import com.jobinow.model.entities.User;
import com.jobinow.model.enums.Role;
import com.jobinow.model.enums.TokenType;
import com.jobinow.repositories.TokenRepository;
import com.jobinow.repositories.UserRepository;
import com.jobinow.security.JwtService;
import com.jobinow.services.spec.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

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
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Value("${spring.security.oauth2.resourceserver.opaque-token.clientId}")
    private String clientId;

    @Value("${spring.security.oauth2.resourceserver.opaque-token.clientSecret}")
    private String clientSecret;

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
     * @param role The role to be assigned to the new user, which determines the user's permissions and access.
     * @return AuthenticationResponse containing the generated JWT access and refresh tokens.
     */
    private AuthenticationResponse createUser(RegisterRequest request, Role role) {
        log.info("Creating a new user with role: {}", role);

        var user = User.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();
        user.setRole(role);

        var savedUser = repository.save(user);
        log.info("User with ID {} created successfully.", savedUser.getId());

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        saveUserToken(savedUser, jwtToken);
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
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * Saves a new user token to the database.
     *
     * @param user     User for whom the token is generated
     * @param jwtToken JWT token to be saved
     */
    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    /**
     * Revokes all valid tokens for a user by marking them as expired and revoked.
     *
     * @param user User for whom tokens are revoked
     */
    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (!validUserTokens.isEmpty()) {
            validUserTokens.forEach(token -> {
                token.setExpired(true);
                token.setRevoked(true);
            });
            tokenRepository.saveAll(validUserTokens);
        }
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
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
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

    /**
     * Generates a URL for Google OAuth authentication.
     *
     * @return A UrlDto containing the Google OAuth URL.
     */
    public UrlDto getGoogleAuthUrl() {
        log.info("Generating Google OAuth URL");
        return new UrlDto(
                new GoogleAuthorizationCodeRequestUrl(
                        clientId,
                        "http://localhost:4200/",
                        Arrays.asList("email", "profile", "openid")
                ).build()
        );
    }

    /**
     * Retrieves a Google authentication token based on the provided code.
     *
     * @param code The authorization code from Google.
     * @return A TokenDto containing the Google authentication token.
     * @throws IOException If an error occurs during the token retrieval.
     */
    public TokenDto getGoogleTokenAuthentification(String code) throws IOException {
        log.info("Retrieving Google authentication token for code: {}", code);
        try {
            String Token = new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(),
                    new GsonFactory(),
                    clientId,
                    clientSecret,
                    code,
                    "http://localhost:4200/"
            )
                    .execute()
                    .getAccessToken();

            return new TokenDto(Token);
        } catch (Exception e) {
            log.error("Error retrieving Google authentication token: {}", e.getMessage());
            throw new IOException(e);
        }
    }

    /**
     * Authenticates a user with a Google authentication code.
     *
     * @param code The Google authentication code.
     * @return AuthenticationResponse containing access and refresh tokens.
     * @throws IOException If an error occurs during authentication.
     */
    public AuthenticationResponse authenticateFromGoogleCode(String code) throws IOException {
        log.info("Authenticating user with Google code: {}", code);

        GoogleIdToken.Payload payload = getGooglePayload(code);
        User user = getUserFromPayload(payload);

        return createAuthenticationResponse(user);
    }

    /**
     * Retrieves the payload from a Google ID Token based on the provided authentication code.
     * This method combines the process of fetching the Google Token Response and parsing the
     * Google ID Token to extract the payload, which contains user information.
     *
     * @param code The Google authentication code.
     * @return The payload of the Google ID Token.
     * @throws IOException If an error occurs while fetching or parsing the token.
     */
    private GoogleIdToken.Payload getGooglePayload(String code) throws IOException {
        GoogleTokenResponse tokenResponse = fetchGoogleTokenResponse(code);
        return parseGoogleIdToken(tokenResponse);
    }

    /**
     * Fetches Google Token Response using the provided Google authentication code.
     *
     * @param code The Google authentication code.
     * @return GoogleTokenResponse object containing the token information.
     * @throws IOException If there is an error in fetching the token response.
     */
    private GoogleTokenResponse fetchGoogleTokenResponse(String code) throws IOException {
        try {
            return new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(),
                    new GsonFactory(),
                    clientId,
                    clientSecret,
                    code,
                    "http://localhost:4200/"
            ).execute();
        } catch (IOException e) {
            log.error("Error fetching Google token response: {}", e.getMessage());
            throw new IOException("Failed to fetch Google token response", e);
        }
    }

    /**
     * Parses the Google ID Token from the Google Token Response.
     *
     * @param tokenResponse The response from Google containing the ID token.
     * @return Payload of the parsed Google ID Token.
     * @throws IOException If there is an error in parsing the ID token.
     */
    private GoogleIdToken.Payload parseGoogleIdToken(GoogleTokenResponse tokenResponse) throws IOException {
        try {
            GoogleIdToken idToken = tokenResponse.parseIdToken();
            return idToken.getPayload();
        } catch (IOException e) {
            log.error("Error parsing Google ID Token: {}", e.getMessage());
            throw new IOException("Failed to parse Google ID Token", e);
        }
    }

    /**
     * Retrieves or creates a User entity based on the information in the GoogleIdToken's payload.
     *
     * @param payload The payload of the Google ID Token containing user information.
     * @return The retrieved or newly created User entity.
     */
    private User getUserFromPayload(GoogleIdToken.Payload payload) {
        return repository.findByEmail(payload.getEmail())
                .orElseGet(() -> createUserFromPayload(payload));
    }

    /**
     * Creates a new User entity from the Google ID Token's payload.
     *
     * @param payload The payload of the Google ID Token containing user information.
     * @return The newly created User entity.
     */
    private User createUserFromPayload(GoogleIdToken.Payload payload) {
        User newUser = User.builder()
                .firstname(payload.get("given_name").toString())
                .lastname(payload.get("family_name").toString())
                .email(payload.getEmail())
                .image(payload.get("picture").toString())
                .role(Role.JOB_SEEKER)
                .build();
        return repository.save(newUser);
    }

    /**
     * Generates and builds an AuthenticationResponse for the specified user.
     *
     * @param user The User entity for whom the authentication response is to be generated.
     * @return AuthenticationResponse containing access and refresh tokens.
     */
    private AuthenticationResponse createAuthenticationResponse(User user) {
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        log.info("Generated and saved tokens for user: {}", user.getEmail());
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
}