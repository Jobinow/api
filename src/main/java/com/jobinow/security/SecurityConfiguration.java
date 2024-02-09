package com.jobinow.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * Configuration class for Spring Security settings.
 * This class configures the security aspects of the application, including CORS settings,
 * CSRF protection, session management, authentication providers, and JWT filter integration.
 * It also defines the security filter chain that specifies which requests are secured and how.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    private static final String[] WHITE_LIST_URL = {
            "/api/v2/**",
            "/api/v2/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"
    };
    private static final String[] ALLOW_ORIGIN = {
            "http://localhost:4200"
    };
    private static final String[] ALLOW_METHODS = {
            "GET",
            "POST",
            "PUT",
            "DELETE",
            "OPTIONS"
    };
    private static final String[] ALLOW_HEAD = {
            "Access-Control-Allow-Origin",
            "Access-Control-Allow-Methods",
            "Access-Control-Allow-Headers",
            "Access-Control-Max-Age",
            "Access-Control-Request-Headers",
            "Access-Control-Request-Method",
            "Origin",
            "Cache-Control",
            "Content-Type",
            "Authorization",
            "X-Requested-With"
    };
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    /**
     * Configures the security filter chain for the application.
     * This method sets up CORS, CSRF protection, session management, and configures authentication
     * and authorization for HTTP requests. It also integrates the JWT authentication filter and
     * defines logout behavior.
     *
     * @param http HttpSecurity object to configure security settings.
     * @return SecurityFilterChain object representing the configured security filter chain.
     * @throws Exception if an error occurs during the configuration process.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AntPathRequestMatcher[] requestMatchers = Arrays.stream(WHITE_LIST_URL)
                .map(AntPathRequestMatcher::new)
                .toArray(AntPathRequestMatcher[]::new);
        http
                .cors(httpSecurityCorsConfigurer ->
                        httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource())
                )
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(customizer ->
                        customizer.authenticationEntryPoint(
                                new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)
                        )
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(STATELESS)
                )
                .authorizeHttpRequests(req ->
                        req.requestMatchers(requestMatchers)
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) ->
                                        SecurityContextHolder.clearContext()
                                )
                )
                .oauth2ResourceServer(c -> c.opaqueToken(Customizer.withDefaults()));
        return http.build();
    }

    /**
     * Configures CORS (Cross-Origin Resource Sharing) settings for the application.
     * This method defines which origins, HTTP methods, and headers are allowed in CORS requests.
     *
     * @return CorsConfigurationSource object representing the CORS configuration.
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(ALLOW_ORIGIN));
        configuration.setAllowedMethods(Arrays.asList(ALLOW_METHODS));
        configuration.setAllowedHeaders(Arrays.asList(ALLOW_HEAD));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}