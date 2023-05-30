package com.knoldus.springbootelasticsearch.service;

import com.knoldus.springbootelasticsearch.utils.ResultQuery;

import java.io.IOException;

public interface SearchService {
    ResultQuery searchFromQuery(String query) throws IOException;
}
