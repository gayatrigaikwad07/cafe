package com.inn.cafe.com.inn.cafe.POJO;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
@NamedQuery (name = "NewUser.findByEmailId", query="select u from NewUser u where u.email=:email")
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="NUser")
public class NewUser implements Serializable {
    private static final long serialVersionULD = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name ="id")
    private Integer id;

    @Column(name ="name",insertable = false, updatable = false)
    private String name;

    @Column(name ="name",insertable = false, updatable = false)
    private String contactNumber;

    @Column(name ="email",insertable = false, updatable = false)
    private String email;

    @Column(name ="password",insertable = false, updatable = false)
    private String password;

    @Column(name ="status",insertable = false, updatable = false)
    private String status;

    @Column(name ="role",insertable = false, updatable = false)
    private String role;


    public void setStatus(String status) {
    }

    public void setPassword(String password) {
    }

    public void setEmail(String email) {
    }

    public void setContactNumber(String contactNumber) {
    }

    public void setName(String name) {

    }

    public void setRole(String role) {
    }

    public Object getAuthorities() {

        return null;
    }
}
