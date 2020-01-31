package io.digital.apigateway.configuration;

import io.digital.apigateway.controller.CustomAccessDeniedHandler;
import io.digital.apigateway.filter.AuthorizationFilter;
import io.digital.apigateway.service.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private TokenProvider tokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                .authorizeRequests()
                .antMatchers("/api/oauth/token").permitAll()
                .anyRequest().authenticated()
                .and().addFilter(authorizationFilter());
        //TODO - add authority here
    }

    private AuthorizationFilter authorizationFilter() throws Exception {
        AuthorizationFilter filter = new AuthorizationFilter(authenticationManager());
        filter.setTokenProvider(tokenProvider);
        return filter;
    }
}