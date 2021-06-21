package com.example.libra.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

@Entity
@Table(name = "comentToUser")
public class ComentToUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id_author_com")
    private User author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id_page_com")
    private User pageUser;

    private String coment;

    private boolean personal;

    private boolean isitnew;

    private int numberMes;

    private Date dateCreate;

    private boolean isRead;

    public ComentToUser() {
        this.dateCreate=new Date(Calendar.getInstance().getTime().getTime());
        this.isRead=false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getPageUser() {
        return pageUser;
    }

    public void setPageUser(User pageUser) {
        this.pageUser = pageUser;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public boolean isPersonal() {
        return personal;
    }

    public void setPersonal(boolean personal) {
        this.personal = personal;
    }

    public boolean isIsitnew() {
        return isitnew;
    }

    public void setIsitnew(boolean is) {
        this.isitnew = is;
    }

    public int getNumberMes() {
        return numberMes;
    }

    public void setNumberMes(int numberMes) {
        this.numberMes = numberMes;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate() {
        this.dateCreate = new Date(Calendar.getInstance().getTime().getTime());
    }
}
