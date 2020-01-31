package io.digital.apigateway.filter;

import io.digital.apigateway.Constants;
import io.digital.apigateway.controller.ErrorResponse;
import io.digital.apigateway.exception.InvalidTokenException;
import io.digital.apigateway.exception.TokenExpiredException;
import io.digital.apigateway.service.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);

    private TokenProvider tokenProvider;


    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public AuthorizationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(request));
            chain.doFilter(request,response);
        } catch (InvalidTokenException| TokenExpiredException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(),e.getErrorDescription());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write(errorResponse.toString());
        }

    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = getToken(request);
        if(token != null) {
            try {

                Claims claims = tokenProvider.decodeJWT(token);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(claims.getSubject(),
                        null,
                        new ArrayList<>());
                return authentication;
            } catch(ExpiredJwtException e) {
                throw new TokenExpiredException();
            } catch(Exception e) {
                throw new InvalidTokenException();
            }
        }
        return null;
    }

    public TokenProvider getTokenProvider() {
        return tokenProvider;
    }

    public void setTokenProvider(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    private String getToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(Constants.AUTHORIZATION_HEADER);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(Constants.BEARER_TOKEN_PREFIX.length());
        }
        return null;
    }
}
