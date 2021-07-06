package com.example.libra.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Имя пользователя не введено")
    private String username;
    @NotBlank(message = "Пароль не введен")
    private String password;

    private String userImg;

    private String userFontImg;
    //!
    private Date dateCreate;

    private boolean active;

    public User(){
        dateCreate=new Date(Calendar.getInstance().getTime().getTime());
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_personal")
    private UserPersonal userPersonal;

    @Email(message = "Некорректно введен email")
    @NotBlank(message = "Email не должен быть пустым")
    private String email;
    private boolean vip;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    public boolean isRec() {
        return roles.contains(Role.REC);
    }

    public boolean isModer() {
        return roles.contains(Role.MODER);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
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
        return isActive();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getVip() {
        return vip;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public String getUserFontImg() {
        return userFontImg;
    }

    public UserPersonal getUserPersonal() {
        return userPersonal;
    }

    public void setUserPersonal(UserPersonal userPersonal) {
        this.userPersonal = userPersonal;
    }

    public void setUserFontImg(String userFontImg) {
        this.userFontImg = userFontImg;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate() {
        this.dateCreate = new Date(Calendar.getInstance().getTime().getTime());
    }
}
