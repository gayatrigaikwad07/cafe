package com.inn.cafe.com.inn.cafe.Service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface CategoryService {
    ResponseEntity<String> addNewCategory(Map<String,String> requestMap);
}
