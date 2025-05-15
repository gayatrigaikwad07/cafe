package com.inn.cafe.com.inn.cafe.POJO;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@NamedQuery(name = "NewUser.findByEmailId", query="select u from NewUser u where u.email=:email")
@NamedQuery (name= "NewUser.getAllUser", query="select new com.inn.cafe.com.inn.cafe.Wrapper.UserWrapper(u.id,u.name,u.email,u.contactNumber, u.status) from NewUser u where u.role='NewUser'" )
@NamedQuery (name = "NewUser.updateStatus", query  ="update NewUser u set u.status=: status where u.id=:id")
@NamedQuery (name= "NewUser.getAllAdmin", query="select u.email from NewUser u where u.role='admin'" )

//
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="NUser")
public class NewUser implements Serializable, UserDetails {
    private static final long serialVersionULD = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer id;

    @Column(name ="name")
    private String name;

    @Column(name ="contactNumber")
    private String contactNumber;

    @Column(name ="email")
    private String email;

    @Column(name ="password")
    private String password;

    @Column(name ="status")
    private String status;

    @Column(name ="role")
    private String role;

    @Column(name = "enabled")
    private boolean enabled;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + (role != null ? role.toUpperCase() : "USER")));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return "true".equalsIgnoreCase(this.status);
    }
}
