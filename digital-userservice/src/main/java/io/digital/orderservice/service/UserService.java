package io.digital.orderservice.service;

import io.digital.orderservice.entity.User;
import io.digital.orderservice.exception.AuthenticationException;
import io.digital.orderservice.exception.BadRequestException;
import io.digital.orderservice.exception.EntityDuplicateException;
import io.digital.orderservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User>{
    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void authenticate(String mobileNumber, String password) {
        User user = userRepository.findByMobileNumber(mobileNumber);
        if(user == null) {
            throw new AuthenticationException("Wrong username or password");
        }
        if(!passwordEncoder.matches(password,user.getPassword())) {
            throw new AuthenticationException("Wrong username or password");
        }
    }

    @Override
    public User create(User entity) {
        if(entity.getMobileNumber() == null) {
            throw new BadRequestException("Mobile number cannot be null");
        }
        if(entity.getPassword() == null) {
            throw new BadRequestException("Password cannot be null");
        }
        User user = userRepository.findByMobileNumber(entity.getMobileNumber());
        if(user != null) {
            throw new EntityDuplicateException("There is already a user with this mobile number");
        }
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return super.create(entity);
    }
}
