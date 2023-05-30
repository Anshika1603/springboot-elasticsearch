package com.knoldus.springbootelasticsearch.service.impl;

import com.knoldus.springbootelasticsearch.mapper.CustomerMapper;
import com.knoldus.springbootelasticsearch.model.Customer;
import com.knoldus.springbootelasticsearch.model.dto.CustomerDTO;
import com.knoldus.springbootelasticsearch.repository.CustomerDAO;
import com.knoldus.springbootelasticsearch.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerDAO customerDAO;
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerDAO customerDAO, CustomerMapper customerMapper) {
        this.customerDAO = customerDAO;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        System.out.println(customerDTO.getLastname());
        System.out.println(customerDTO.getFirstname());
        System.out.println(customerDTO.getId());
        Customer customer = this.customerDAO.save(this.customerMapper.toCustomer(customerDTO));
        System.out.println(customer.getId());
        System.out.println(customer.getFirstname());
        System.out.println(customer.getLastname());
        return this.customerMapper.toCustomerDTO(customer);
    }

    @Override
    public CustomerDTO findById(Long id) {
        return this.customerMapper.toCustomerDTO(this.customerDAO.findById(id).orElse(null));
    }

    @Override
    public List<CustomerDTO> findAll() {
        return this.customerMapper.toCustomerDtos(this.customerDAO.findAll());
    }
}

