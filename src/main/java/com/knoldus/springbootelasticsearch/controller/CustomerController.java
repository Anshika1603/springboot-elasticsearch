package com.knoldus.springbootelasticsearch.controller;

import com.knoldus.springbootelasticsearch.model.Customer;
import com.knoldus.springbootelasticsearch.model.dto.CustomerDTO;
import com.knoldus.springbootelasticsearch.service.CustomerService;
import com.knoldus.springbootelasticsearch.service.impl.CustomerServiceImpl;
import com.knoldus.springbootelasticsearch.utils.PathResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(PathResources.SAVE)
    public ResponseEntity<CustomerDTO> saveUser(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(this.customerService.save(customerDTO), HttpStatus.OK);
    }

    @GetMapping(PathResources.FIND_ONE + "/{" + PathResources.ID + "}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(this.customerService.findById(id), HttpStatus.OK);
    }

    @GetMapping(PathResources.FIND_ALL)
    public ResponseEntity<List<CustomerDTO>> findById() {
        return new ResponseEntity<>(this.customerService.findAll(), HttpStatus.OK);
    }
}
