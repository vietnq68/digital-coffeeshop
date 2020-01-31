package io.digital.orderservice.controller;

import io.digital.orderservice.controller.request.CreateTokenRequest;
import io.digital.orderservice.exception.BadRequestException;
import io.digital.orderservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oauth")
public class OAuthController {
    @Autowired
    AuthService authService;
    @RequestMapping(method = RequestMethod.POST,value = "token")
    public ResponseEntity token(@RequestBody CreateTokenRequest request) {
        if(request.mobileNumber == null || request.password == null) {
            throw new BadRequestException("Mobile number or password cannot be null");
        }
        String token = authService.token(request.mobileNumber,request.password);
        return ResponseEntity.ok(token);
    }
}
