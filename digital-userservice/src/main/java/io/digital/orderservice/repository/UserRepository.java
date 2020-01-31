package io.digital.orderservice.repository;

import io.digital.orderservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByMobileNumber(String mobileNumber);
}
