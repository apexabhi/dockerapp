package com.nagarro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.entity.LogisticPartners;

@Repository
public interface LogisticRepository extends JpaRepository<LogisticPartners, Long> {

}