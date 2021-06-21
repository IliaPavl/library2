package com.example.libra.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "usrP")
public class UserPersonal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String surname;
    private String gender;
    private String vkHref;
    private String facebookHref;
    private String instagramHref;
    private String skypeHref;
    private Date dateBirsday;
    private String statusLife;
    private String aboutUser;

    @ManyToMany
    @JoinColumn(name = "user_friends")
    private List<Friends> userFriends;

    @ManyToMany
    @JoinColumn(name = "user_subs")
    private List<Subs> userSubs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getVkHref() {
        return vkHref;
    }

    public void setVkHref(String vkHref) {
        this.vkHref = vkHref;
    }

    public String getFacebookHref() {
        return facebookHref;
    }

    public void setFacebookHref(String facebookHref) {
        this.facebookHref = facebookHref;
    }

    public String getInstagramHref() {
        return instagramHref;
    }

    public void setInstagramHref(String instagramHref) {
        this.instagramHref = instagramHref;
    }

    public String getSkypeHref() {
        return skypeHref;
    }

    public void setSkypeHref(String skypeHref) {
        this.skypeHref = skypeHref;
    }

    public Date getDateBirsday() {
        return dateBirsday;
    }

    public void setDateBirsday(Date dateBirsday) {
        this.dateBirsday = dateBirsday;
    }

    public String getStatusLife() {
        return statusLife;
    }

    public void setStatusLife(String statusLife) {
        this.statusLife = statusLife;
    }

    public List<Friends> getUserFriends() {
        return userFriends;
    }

    public void setUserFriends(List<Friends> userFriends) {
        this.userFriends = userFriends;
    }

    public List<Subs> getUserSubs() {
        return userSubs;
    }

    public void setUserSubs(List<Subs> userSubs) {
        this.userSubs = userSubs;
    }

    public String getAboutUser() {
        return aboutUser;
    }

    public void setAboutUser(String aboutUser) {
        this.aboutUser = aboutUser;
    }
}
