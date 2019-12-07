package com.khoubyari.example.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder={"id", "firstName", "lastName", "email", "roleId"})
@Entity
@Table(name = "user_table")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @XmlTransient
    private String firstName;

    @XmlTransient
    private String lastName;

    @XmlTransient
    @Column(unique = true)
    private String email;

    @XmlTransient
    private String password;

    @XmlTransient
    private boolean enabled;

    @XmlTransient
    private boolean tokenExpired;

    @XmlTransient
    @ManyToOne()
    @JoinColumn(name = "role_id", updatable=false, insertable=false)
    private Role role;

    @XmlTransient
    @Column(name = "role_id")
    private String roleId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @XmlTransient
    public String getRoleId() {
        return roleId;
    }
    
    @XmlTransient
    public String getEmail() {
        return email;
    }

    @XmlTransient
    public String getFirstName() {
        return firstName;
    }

    @XmlTransient
    public String getLastName() {
        return lastName;
    }

    @XmlTransient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(roleId));
        return roles;
    }

    @XmlTransient
    @Override
    public String getPassword() {
        return password;
    }

    @XmlTransient
    @Override
    public String getUsername() {
        return email;
    }

    @XmlTransient
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @XmlTransient
    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @XmlTransient
    @Override
    public boolean isCredentialsNonExpired() {
        return !tokenExpired;
    }

    @XmlTransient
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @XmlTransient
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
