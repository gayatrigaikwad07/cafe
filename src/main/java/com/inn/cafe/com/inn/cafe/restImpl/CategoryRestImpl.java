package com.inn.cafe.com.inn.cafe.restImpl;

import com.inn.cafe.com.inn.cafe.POJO.Category;
import com.inn.cafe.com.inn.cafe.Service.CategoryService;
import com.inn.cafe.com.inn.cafe.Service.UserService;
import com.inn.cafe.com.inn.cafe.Utils.UserUtils;
import com.inn.cafe.com.inn.cafe.constents.UserConstents;
import com.inn.cafe.com.inn.cafe.rest.CategoryRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoryRestImpl implements CategoryRest {

    @Autowired
    CategoryService categoryService;

    @Override
    public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
        try{
            return categoryService.addNewCategory(requestMap);

        }
        catch(Exception ex){
            ex.printStackTrace ();
        }
        return UserUtils.getResponseEntity (UserConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
        try{
            return categoryService.getAllCategory(filterValue);

        } catch (Exception ex) {
            ex.printStackTrace ();
        }
        return new ResponseEntity <>(new ArrayList<> (), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
        try{
            return categoryService.updateCategory(requestMap);

        }
        catch(Exception ex){
            ex.printStackTrace ();
        }
        return UserUtils.getResponseEntity (UserConstents.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
