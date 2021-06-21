package com.example.libra.domain;

import javax.persistence.*;

@Entity
@Table(name = "friends")
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sub")
    private User whoSubFriend;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "whoSub")
    private User authorFriend;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Friends() {
    }

    public Friends(User whoSubFriend, User authorFriend) {
        this.whoSubFriend = whoSubFriend;
        this.authorFriend = authorFriend;
    }

    public User getiDwhoSubFriend() {
        return whoSubFriend;
    }

    public void setiDwhoSubFriend(User iDwhoSubFriend) {
        this.whoSubFriend = iDwhoSubFriend;
    }

    public User getAuthorFriend() {
        return authorFriend;
    }

    public void setAuthorFriend(User idAuthorFriend) {
        this.authorFriend = idAuthorFriend;
    }
}


