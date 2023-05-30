package com.knoldus.springbootelasticsearch.model;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Document(indexName = "user")
@Getter
@Setter
@NoArgsConstructor
public class CustomerModel {

        private Long id;
        private String firstname;
        private String lastname;
        private Date modificationDate;

}
