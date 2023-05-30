package com.knoldus.springbootelasticsearch.service;

import com.knoldus.springbootelasticsearch.model.Customer;
import com.knoldus.springbootelasticsearch.model.dto.CustomerDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CustomerService {
    CustomerDTO save(CustomerDTO customerDTO);
    CustomerDTO findById(Long id);
    List<CustomerDTO> findAll();
}
