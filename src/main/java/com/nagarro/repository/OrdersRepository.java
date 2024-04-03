package com.nagarro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

}