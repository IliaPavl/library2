package com.example.libra.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

@Entity
@Table(name = "coments")
public class Coment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id_first_com")
    private User author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "page_id")
    private Page page;

    private Date dateCreate;

    private String coment;

    public Coment() {
    }

    public Coment(User user, Page page, String message, Book byId) {
        dateCreate=new Date(Calendar.getInstance().getTime().getTime());
        author=user;
        this.page =page;
        coment=message;
        book =byId;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book idBook) {
        this.book = idBook;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page idPage) {
        this.page = idPage;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate() {
        this.dateCreate = new Date(Calendar.getInstance().getTime().getTime());
    }
}
