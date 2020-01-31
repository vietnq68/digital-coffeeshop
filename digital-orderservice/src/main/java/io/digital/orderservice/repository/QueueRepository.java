package io.digital.orderservice.repository;

import io.digital.orderservice.entity.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueueRepository extends JpaRepository<Queue,Integer> {
    List<Queue> findByShopId(Integer shopId);
}
