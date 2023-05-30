package com.knoldus.springbootelasticsearch.controller;

import com.knoldus.springbootelasticsearch.service.SearchService;
import com.knoldus.springbootelasticsearch.utils.Constants;
import com.knoldus.springbootelasticsearch.utils.PathResources;
import com.knoldus.springbootelasticsearch.utils.ResultQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(PathResources.SEARCH_CONTROLLER)
public class SearchController {
    @Autowired
    SearchService searchService;


//    public SearchController(SearchService searchService) {
//        this.searchService = searchService;
//    }

    @GetMapping(Constants.SEARCH_QUERY + "/{" + Constants.QUERY + "}")
    public ResponseEntity<ResultQuery> searchQuery(@PathVariable String query) throws IOException {
        return new ResponseEntity<> (searchService.searchFromQuery(query.trim().toLowerCase()), HttpStatus.OK);
    }
}
