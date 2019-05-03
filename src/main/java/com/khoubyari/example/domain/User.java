package com.khoubyari.example.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "user_table")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;
    private String password;
    private boolean enabled;
    private boolean tokenExpired;

    @ManyToOne()
    @JoinColumn(name = "role_id", updatable=false, insertable=false)
    private Role role;

    @Column(name = "role_id")
    private String roleId;

    public long getId() {
        return id;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(roleId));
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !tokenExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public ArrayList<String> getRoles() {
        ArrayList<String> roles = new ArrayList<>();
        roles.add(roleId);
        return roles;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTokenExpired(boolean tokenExpired) {
        this.tokenExpired = tokenExpired;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
