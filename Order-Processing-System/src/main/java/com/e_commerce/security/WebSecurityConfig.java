package com.e_commerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final UserDetailsService userDetailsService;
    private final JWTAuthenticationFilter jwtAuthorizationFilter;
    private final SecurityConfig securityConfig;

    @Bean
	/*
	 * public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
	 * Exception { http .sessionManagement(session ->
	 * session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless
	 * session .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for stateless
	 * applications .authorizeHttpRequests(auth -> auth
	 * .requestMatchers("/api/auth/register").permitAll()
	 * .requestMatchers("/order-processing-system/register").permitAll() // Allow
	 * register without token .requestMatchers("/api/auth/login").permitAll() //
	 * Allow login without token .requestMatchers("/api/auth/**").permitAll() //
	 * Public endpoints for authentication
	 * .requestMatchers("/api/users/**").permitAll() // Public user endpoints
	 * .requestMatchers("/api/admin/**").hasRole("ADMIN") // Admin access
	 * .requestMatchers("/api/manager/**").hasAnyRole("ADMIN", "MANAGER") // Admin
	 * and Manager access .requestMatchers("/api/team-lead/**").hasAnyRole("ADMIN",
	 * "MANAGER", "TEAM_LEAD") // Team Lead access
	 * .requestMatchers("/api/member/**").hasAnyRole("ADMIN", "MANAGER",
	 * "TEAM_LEAD", "MEMBER") // Member access
	 * .requestMatchers(securityConfig.getAllowedPublicApis().toArray(new
	 * String[0])).permitAll() // Dynamic public APIs .anyRequest().authenticated())
	 * // Any other request requires authentication .exceptionHandling(exception ->
	 * exception .authenticationEntryPoint(jwtAuthenticationEntryPoint)) // Handle
	 * unauthorized access .addFilterBefore(jwtAuthorizationFilter,
	 * UsernamePasswordAuthenticationFilter.class); // Add JWT filter
	 * 
	 * return http.build(); }
	 */
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session
            .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for stateless applications
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/register").permitAll()  // Allow register without authentication
                .requestMatchers("/api/auth/login").permitAll() // Allow login without authentication
                .anyRequest().authenticated()) // Any other request requires authentication
            .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
            .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}
