package com.inn.cafe.com.inn.cafe.POJO;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

//@NamedQuery (name = "Category.getAllCategory" ,query ="select Category c from c")
@NamedQuery(name = "Category.getAllCategory", query = "SELECT c FROM Category c")


@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "category")

public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name ="id")
    private Integer id;

    @Column(name = "name")
    private String name;

}



