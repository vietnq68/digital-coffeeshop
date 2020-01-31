package io.digital.orderservice.service;

import io.digital.orderservice.entity.Order;
import io.digital.orderservice.entity.Product;
import io.digital.orderservice.repository.OrderRepository;
import io.digital.orderservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseService<Product> {
}
