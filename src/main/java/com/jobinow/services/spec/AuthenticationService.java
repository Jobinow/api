package com.jobinow.services.spec;

import com.jobinow.model.dto.requests.AuthenticationRequest;
import com.jobinow.model.dto.requests.RegisterRequest;
import com.jobinow.model.dto.responses.AuthenticationResponse;
import com.jobinow.model.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Interface for user authentication and token management.
 */
public interface AuthenticationService {

    /**
     * Registers a new user and generates access and refresh tokens.
     *
     * @param request Registration request containing user details
     * @return AuthenticationResponse containing access and refresh tokens
     */
    AuthenticationResponse register(RegisterRequest request);

    /**
     * Registers a job seeker user and generates access and refresh tokens.
     *
     * @param request Registration request containing user details
     * @return AuthenticationResponse containing access and refresh tokens
     */
    AuthenticationResponse registerJobSeeker(RegisterRequest request);

    /**
     * Registers a manager user and generates access and refresh tokens.
     *
     * @param request Registration request containing user details
     * @return AuthenticationResponse containing access and refresh tokens
     */
    AuthenticationResponse registerManager(RegisterRequest request);

    /**
     * Registers an agent user and generates access and refresh tokens.
     *
     * @param request Registration request containing user details
     * @return AuthenticationResponse containing access and refresh tokens
     */
    AuthenticationResponse registerAgent(RegisterRequest request);

    /**
     * Registers a recruiter user and generates access and refresh tokens.
     *
     * @param request Registration request containing recruiter user details
     * @return AuthenticationResponse containing access and refresh tokens
     */
    AuthenticationResponse registerRecruiter(RegisterRequest request);

    /**
     * Authenticates a user and generates new access and refresh tokens.
     *
     * @param request Authentication request containing user credentials
     * @return AuthenticationResponse containing new access and refresh tokens
     */
    AuthenticationResponse authenticate(AuthenticationRequest request);

    /**
     * Refreshes the access token using a valid refresh token.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse for writing the new tokens
     * @throws IOException if an error occurs during response writing
     */
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     * Checks if a token is valid.
     *
     * @param token Token to check
     * @return true if the token is valid, false otherwise
     */
    Boolean checkToken(String token);

}