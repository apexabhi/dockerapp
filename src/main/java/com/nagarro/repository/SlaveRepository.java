package com.nagarro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.entity.Seller;
import com.nagarro.entity.Slaves;

@Repository
public interface SlaveRepository extends JpaRepository<Slaves, Long> {
	public Seller findBySeller_SellerId(Long id);
}