package io.digital.orderservice.service;

import io.digital.orderservice.contants.OrderStatus;
import io.digital.orderservice.entity.Order;
import io.digital.orderservice.entity.Queue;
import io.digital.orderservice.exception.BaseException;
import io.digital.orderservice.exception.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

@Service
public class OrderService extends BaseService<Order> {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private QueueService queueService;
    @Transactional
    public Order placeOrder(Order order) {
        logger.info("Creating new order for product: {}, shop: {}, customer: {}",new Object[]{order.getProductId(),
                order.getShopId(),order.getCustomerId()});
        if(order.getShopId() == null || order.getProductId() == null) {
            logger.error("Shop or product cannot be null");
            throw new BaseException(ErrorCode.INVALID_REQUEST,"Shop or product cannot be null");
        }
        List<Queue> queues = queueService.findByShopId(order.getShopId());
        Queue elected = electQueue(queues);
        String positionCode = randomPosition(elected.getQueueNumber());

        elected.enqueue();

        order.setPositionCode(positionCode);
        order.setQueueId(elected.getId());
        order.setStatus(OrderStatus.WAITING);
        order.setQueueNumber(elected.getQueueNumber());
        order = create(order);

        queueService.update(elected.getId(),elected);
        logger.info("Created order for customer: {} with position code: {}",order.getCustomerId(),order.getPositionCode());
        return order;
    }

    @Transactional
    public void finishOrder(Integer id) {
        Order order = get(id);
        order.setStatus(OrderStatus.DONE);
        update(id,order);

        Queue queue = queueService.get(order.getQueueId());
        queue.dequeue();
        queueService.update(queue.getId(),queue);
    }

    private Queue electQueue(List<Queue> queues) {
        logger.info("Getting the least busy queue");
        if(queues == null || queues.isEmpty()) {
            logger.error("Shop has no queue");
            throw new BaseException(ErrorCode.GENERAL_ERROR,"Shop has no queue");
        }
        Queue elected = queues.get(0);
        for(int i = 1; i < queues.size(); i++) {
            Queue queue = queues.get(i);
            if(queue.getEndIndex() < elected.getEndIndex()) {
                elected = queue;
            }
        }
        logger.info("Found least busy queue: {} for shop: {}",elected.getQueueNumber(),elected.getShopId());
        return elected;
    }

    private String randomPosition(Integer queueNumber) {
        Random rnd = new SecureRandom();
        int random = rnd.nextInt(999);
        return queueNumber + String.format("%3d",random);
    }
}
