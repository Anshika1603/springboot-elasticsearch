package com.knoldus.springbootelasticsearch.utils;

import com.knoldus.springbootelasticsearch.mapper.CustomerMapper;
import com.knoldus.springbootelasticsearch.model.Customer;
import com.knoldus.springbootelasticsearch.repository.CustomerDAO;
import com.knoldus.springbootelasticsearch.repository.elastic.CustomerRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ElasticSynchronizer {
    private CustomerDAO customerDAO;
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    private static final Logger LOG = LoggerFactory.getLogger(ElasticSynchronizer.class);

    @Autowired
    public ElasticSynchronizer(CustomerDAO customerDAO, CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerDAO = customerDAO;
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }


    @Scheduled(cron = "0 */3 * * * *")
    @Transactional
    public void sync() {
        LOG.info("Start Syncing - {}", LocalDateTime.now());
        this.syncUsers();
        LOG.info(" End Syncing - {}", LocalDateTime.now());
    }

    private void syncUsers() {

        Specification<Customer> userSpecification = (root, criteriaQuery, criteriaBuilder) ->
                getModificationDatePredicate(criteriaBuilder, root);
        List<Customer> userList;
        if (customerRepository.count() == 0) {
            userList = customerDAO.findAll();
        } else {
            userList = customerDAO.findAll(userSpecification);
        }
        for(Customer customer: userList) {
//            LOG.info("Syncing User - {}", customer.getId());
            customerRepository.save(this.customerMapper.toCustomerModel(customer));
        }
    }

    private static Predicate getModificationDatePredicate(CriteriaBuilder cb, Root<?> root) {
        Expression<Timestamp> currentTime;
        currentTime = cb.currentTimestamp();
        Expression<Timestamp> currentTimeMinus = cb.literal(new Timestamp(System.currentTimeMillis() -
                (Constants.INTERVAL_IN_MILLISECONDE)));
        return cb.between(root.<Date>get(Constants.MODIFICATION_DATE),
                currentTimeMinus,
                currentTime
        );
    }
}
