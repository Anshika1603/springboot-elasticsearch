package com.knoldus.springbootelasticsearch.repository.elastic;

import com.knoldus.springbootelasticsearch.model.Customer;
import com.knoldus.springbootelasticsearch.model.CustomerModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CustomerRepository extends ElasticsearchRepository<CustomerModel, Long> {
}
