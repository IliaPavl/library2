package com.example.libra.domain;

import javax.persistence.*;

@Entity
public class LibraryUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "name_lib")
    private TypesLibUser nameLib;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    private User user;

    public TypesLibUser getNameLib() {
        return nameLib;
    }

    public void setNameLib(TypesLibUser nameLib) {
        this.nameLib = nameLib;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
