package io.digital.orderservice.controller;

import io.digital.orderservice.entity.User;
import io.digital.orderservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity get(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @RequestMapping(method = RequestMethod.POST, value = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody User entity) {
        User user = userService.create(entity);
        return ResponseEntity.ok(user);
    }
}
