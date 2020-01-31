package io.digital.orderservice.service;

import io.digital.orderservice.entity.Queue;
import io.digital.orderservice.repository.QueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueueService extends BaseService<Queue> {

    @Autowired
    private QueueRepository queueRepository;

    public List<Queue> findByShopId(Integer shopId) {
        return queueRepository.findByShopId(shopId);
    }
}
