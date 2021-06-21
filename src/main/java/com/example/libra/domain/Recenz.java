package com.example.libra.domain;

import javax.persistence.*;

@Entity
@Table(name = "recenz")
public class Recenz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id_author_recenz")
    private User author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book bookid;

    private String recenzText;

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

    public Book getBookid() {
        return bookid;
    }

    public void setBookid(Book bookid) {
        this.bookid = bookid;
    }

    public String getRecenzText() {
        return recenzText;
    }

    public void setRecenzText(String recenzText) {
        this.recenzText = recenzText;
    }
}
