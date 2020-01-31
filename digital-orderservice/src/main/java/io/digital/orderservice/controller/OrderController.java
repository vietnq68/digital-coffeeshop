package io.digital.orderservice.controller;

import io.digital.orderservice.entity.Order;
import io.digital.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        Order placedOrder = orderService.placeOrder(order);
        return ResponseEntity.ok(order);
    }

    @RequestMapping(value = "{id}/finish",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity finishOrder(@PathVariable("id") Integer id) {
        orderService.finishOrder(id);
        return ResponseEntity.ok().build();
    }
}
