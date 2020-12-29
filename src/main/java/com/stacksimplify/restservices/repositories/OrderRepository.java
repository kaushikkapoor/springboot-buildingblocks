package com.stacksimplify.restservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.Entities.Order;

@RestController
public interface OrderRepository extends JpaRepository<Order, Long> {

}
