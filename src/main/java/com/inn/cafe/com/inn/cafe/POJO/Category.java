package com.inn.cafe.com.inn.cafe.POJO;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@NamedQuery (name = "Category.getAllCategory" ,query ="select category c from c")

@Data
@Entity
@DynamicUpdate
@DynamicInsert


public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name ="id")
    private Integer id;

    @Column(name = "name")
    private String name;




}
