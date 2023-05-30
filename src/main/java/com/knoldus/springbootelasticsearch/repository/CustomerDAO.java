package com.knoldus.springbootelasticsearch.repository;

import com.knoldus.springbootelasticsearch.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
}
