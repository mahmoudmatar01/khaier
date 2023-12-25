package com.example.khaier.security;

import com.example.khaier.exceptions.DelegatedAuthenticationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import java.io.IOException;

@Component("delegatedAuthenticationEntryPoint")
public class DelegatedAuthenticationEntryPoint implements AuthenticationEntryPoint{
    private final HandlerExceptionResolver resolver;
    private final static Logger LOGGER = LoggerFactory.getLogger(DelegatedAuthenticationEntryPoint.class);


    public DelegatedAuthenticationEntryPoint(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws DelegatedAuthenticationException {
        LOGGER.warn("Enter the `Commence` method");
        LOGGER.error(authException.getMessage());
        resolver.resolveException(request, response, null, authException);

    }
}
