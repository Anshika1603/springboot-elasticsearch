package com.knoldus.springbootelasticsearch.mapper;

import com.knoldus.springbootelasticsearch.model.Customer;
import com.knoldus.springbootelasticsearch.model.CustomerModel;
import com.knoldus.springbootelasticsearch.model.dto.CustomerDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toCustomerDTO(Customer customer);

    List<CustomerDTO> toCustomerDtos(List<Customer> users);

    Customer toCustomer(CustomerDTO customerDTO);

    List<Customer> toCustomers(List<CustomerDTO> customerDTOS);

    CustomerModel toCustomerModel(Customer customer);
}
