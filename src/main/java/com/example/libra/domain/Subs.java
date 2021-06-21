package com.example.libra.domain;

import javax.persistence.*;

@Entity
@Table(name = "subs")
public class Subs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long idWhoSub;

    private Long idAuthor;

    public Subs() {
    }

    public Subs(Long idWhoSub, Long idAuthor) {
        this.idWhoSub = idWhoSub;
        this.idAuthor = idAuthor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdWhoSub() {
        return idWhoSub;
    }

    public void setIdWhoSub(Long idWhoSub) {
        this.idWhoSub = idWhoSub;
    }

    public Long getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Long idAuthor) {
        this.idAuthor = idAuthor;
    }
}
