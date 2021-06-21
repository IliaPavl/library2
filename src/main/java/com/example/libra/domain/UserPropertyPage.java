package com.example.libra.domain;

import javax.persistence.*;

@Entity
@Table(name = "user_property")
public class UserPropertyPage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String colorText;
    private String sizeText;
    private String colorBorder;

    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    public UserPropertyPage() {
    }

    public UserPropertyPage(String colorText, String sizeText, String colorBorder, User user) {
        this.colorText = colorText;
        this.sizeText = sizeText;
        this.colorBorder = colorBorder;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColorText() {
        return colorText;
    }

    public void setColorText(String colorText) {
        this.colorText = colorText;
    }

    public String getSizeText() {
        return sizeText;
    }

    public void setSizeText(String sizeText) {
        this.sizeText = sizeText;
    }

    public String getColorBorder() {
        return colorBorder;
    }

    public void setColorBorder(String colorBorder) {
        this.colorBorder = colorBorder;
    }
}
