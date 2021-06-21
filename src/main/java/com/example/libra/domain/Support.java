package com.example.libra.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

@Entity
@Table(name = "support")
public class Support {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String coment;

    private String answer;
    //!
    private Date dateCreate;
    //!
    private boolean isitnew;
    //!
    private boolean isRead;

    public Support() {
        dateCreate=new Date(Calendar.getInstance().getTime().getTime());
        isRead=false;
    }

    public Support(User user1, String comment) {
        dateCreate=new Date(Calendar.getInstance().getTime().getTime());
        author=user1;
        this.coment=comment;
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

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate() {
        this.dateCreate = new Date(Calendar.getInstance().getTime().getTime());
    }

    public boolean isIsitnew() {
        return isitnew;
    }

    public void setIsitnew(boolean isitnew) {
        this.isitnew = isitnew;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
