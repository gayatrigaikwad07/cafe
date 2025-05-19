package com.inn.cafe.com.inn.cafe.POJO;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Data
@Entity
@DynamicUpdate
@DynamicInsert


public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;




}
