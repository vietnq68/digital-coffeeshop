package io.digital.orderservice.service;

import io.digital.orderservice.configuration.JwtConfiguration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
public class TokenProvider {

    @Autowired
    private JwtConfiguration jwtConfiguration;

    public String createJWT(String subject) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtConfiguration.getSecretKey());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(UUID.randomUUID().toString())
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(jwtConfiguration.getIssuer())
                .signWith(signatureAlgorithm, signingKey);

        if (jwtConfiguration.getTtlSeconds() >= 0) {
            long expMillis = nowMillis + jwtConfiguration.getTtlSeconds()*1000;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    public Claims decodeJWT(String jwt) {

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(jwtConfiguration.getSecretKey()))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }
}
