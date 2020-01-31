package io.digital.orderservice.service;

import io.digital.orderservice.exception.InvalidTokenException;
import io.digital.orderservice.exception.TokenExpiredException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    public String token(String username, String password) {
        userService.authenticate(username,password);
        return tokenProvider.createJWT(username);
    }

    public void validateToken(String token) {
        try {
            tokenProvider.decodeJWT(token);
        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }
}
