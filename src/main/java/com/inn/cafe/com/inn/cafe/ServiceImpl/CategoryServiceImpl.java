package com.inn.cafe.com.inn.cafe.ServiceImpl;

import com.inn.cafe.com.inn.cafe.JWT.JwtFilter;
import com.inn.cafe.com.inn.cafe.POJO.Category;
import com.inn.cafe.com.inn.cafe.Service.CategoryService;
import com.inn.cafe.com.inn.cafe.Utils.UserUtils;
import com.inn.cafe.com.inn.cafe.constents.UserConstents;
import com.inn.cafe.com.inn.cafe.dao.CategoryDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static java.lang.Integer.*;

@Slf4j
@RestController
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
        try{
            if(jwtFilter.isAdmin ()){
                if(validateCategoryMap(requestMap, false)){
                    categoryDao.save(getCategoryFormMap (requestMap, false));
                    return UserUtils.getResponseEntity ("Category Added Successfully : ",HttpStatus.OK);
                }
                else{
                    return UserUtils.getResponseEntity (UserConstents.UNAUTHORIZED_ACCESS,HttpStatus.UNAUTHORIZED);
                }

            }


        }
        catch(Exception ex){
            ex.printStackTrace ();
        }
        return UserUtils.getResponseEntity (UserConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    private boolean validateCategoryMap(Map<String, String> requestMap, boolean validateId){
        if(requestMap.containsKey ("name")){
            if(requestMap.containsKey ("id") && validateId){
               return  true;
            }
           else if (!validateId){
                return true;
            }

        }
        return false;
    }
    private Category getCategoryFormMap(Map<String,String> requestMap, Boolean isAdd){
        Category category = new Category ();
        if(isAdd){
            category.setId(parseInt (requestMap.get ("id")));
        }
        category.setName (requestMap.get ("name"));
        return category;
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
        try{
            if (filterValue != null && !filterValue.isEmpty() && filterValue.equalsIgnoreCase("true")) {
                log.info ("Inside if");
                return new ResponseEntity<List<Category>>(categoryDao.getAllCategory(), HttpStatus.OK);
            }

            return new ResponseEntity<> (categoryDao.findAll (),HttpStatus.OK);

        }
        catch(Exception ex){
            ex.printStackTrace ();
        }
        return new ResponseEntity<List<Category>> (new ArrayList<> (),HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @Override
//    public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
//        try{
//            if(jwtFilter.isAdmin ()){
//                if(validateCategoryMap (requestMap,true)){
//                    Optional optional = categoryDao.findAllById (Integer.parseInt (requestMap.get("id")));
//                    if(!optional.isEmpty ()){
//                        categoryDao.save (getCategoryFormMap (requestMap,true));
//                        return UserUtils.getResponseEntity ("Category Updated Successfully",HttpStatus.OK);
//                    }
//                    else{
//                        return UserUtils.getResponseEntity ("Category does not exists",HttpStatus.UNAUTHORIZED);
//                    }
//                }
//                return UserUtils.getResponseEntity (UserConstents.INVALID_DATA,HttpStatus.BAD_REQUEST);
//
//            }
//            else{
//                return UserUtils.getResponseEntity (UserConstents.UNAUTHORIZED_ACCESS,HttpStatus.UNAUTHORIZED);
//            }
//
//        }
//        catch(Exception ex){
//            ex.printStackTrace ();
//        }
//        return null;
//    }

    @Override
    public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateCategoryMap(requestMap, true)) {
                    String idStr = requestMap.get("id");
                    if (idStr == null || idStr.trim().isEmpty()) {
                        return UserUtils.getResponseEntity("ID is missing or empty", HttpStatus.BAD_REQUEST);
                    }

                    int id;
                    try {
                        id = Integer.parseInt(idStr);
                    } catch (NumberFormatException e) {
                        return UserUtils.getResponseEntity("Invalid ID format", HttpStatus.BAD_REQUEST);
                    }

                    Optional optional = categoryDao.findAllById(id);
                    if (optional.isPresent()) {
                        categoryDao.save(getCategoryFormMap(requestMap, true));
                        return UserUtils.getResponseEntity("Category Updated Successfully", HttpStatus.OK);
                    } else {
                        return UserUtils.getResponseEntity("Category does not exist", HttpStatus.UNAUTHORIZED);
                    }
                }
                return UserUtils.getResponseEntity(UserConstents.INVALID_DATA, HttpStatus.BAD_REQUEST);
            } else {
                return UserUtils.getResponseEntity("Unauthorized", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return UserUtils.getResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
