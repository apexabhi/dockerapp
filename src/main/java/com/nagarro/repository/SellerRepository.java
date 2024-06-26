package com.nagarro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.entity.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

}
