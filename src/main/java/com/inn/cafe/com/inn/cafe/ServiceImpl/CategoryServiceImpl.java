package com.inn.cafe.com.inn.cafe.ServiceImpl;

import com.inn.cafe.com.inn.cafe.JWT.JwtFilter;
import com.inn.cafe.com.inn.cafe.POJO.Category;
import com.inn.cafe.com.inn.cafe.Service.CategoryService;
import com.inn.cafe.com.inn.cafe.Utils.UserUtils;
import com.inn.cafe.com.inn.cafe.constents.UserConstents;
import com.inn.cafe.com.inn.cafe.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.Map;

import static java.lang.Integer.*;

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


}
