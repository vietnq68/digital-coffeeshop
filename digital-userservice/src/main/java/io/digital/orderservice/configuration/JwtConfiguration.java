package io.digital.orderservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfiguration {
    @Value("${jwt.secret.key:xxxyyy}")
    private String secretKey;
    @Value("${jwt.issuer:101digital}")
    private String issuer;
    @Value("${jwt.ttl.seconds:3600}")
    private int ttlSeconds;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public int getTtlSeconds() {
        return ttlSeconds;
    }

    public void setTtlSeconds(int ttlSeconds) {
        this.ttlSeconds = ttlSeconds;
    }
}
