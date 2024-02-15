package com.example.khaier.config;

import com.example.khaier.security.AuthFilter;
import com.example.khaier.security.DelegatedAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//    @Qualifier("delegatedAuthenticationEntryPoint")
    private final DelegatedAuthenticationEntryPoint jwtUnAuthResponse;
    private final AuthFilter authFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    @Profile({"local"})
    public SecurityFilterChain notAuthenticatedFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers(
                                "/api/v*/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/actuator/**"
                                ).permitAll()
                                .anyRequest()
                                .authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(
                        authFilter, UsernamePasswordAuthenticationFilter.class)
                  .exceptionHandling(c -> c.authenticationEntryPoint(jwtUnAuthResponse)
                );


        return http.build();
    }

}
