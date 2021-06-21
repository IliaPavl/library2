package com.example.libra.domain;


import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nameBook;
    private int lenghtBook;
    private double rating;
    private int likes;
    private int views;
    private double marks;
    private String imgBook;
    private Date datePublish;
    private Date dateUpdate;
    private String about;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coments")
    private Coment coment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recenz")
    private Recenz recenz;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rate_age")
    private AgeRate ageRate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_status")
    private Status status;

    @ElementCollection(targetClass = Genre.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "book_genres", joinColumns = @JoinColumn(name = "book_id"))
    private List<Genre> genres;

    public String getImgBook() {
        return imgBook;
    }

    public void setImgBook(String imgBook) {
        this.imgBook = imgBook;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public int getLenghtBook() {
        return lenghtBook;
    }

    public void setLenghtBook(int lenghtBook) {
        this.lenghtBook = lenghtBook;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public AgeRate getAgeRate() {
        return ageRate;
    }

    public void setAgeRate(AgeRate ageRate) {
        this.ageRate = ageRate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getDatePublish() {
        return datePublish;
    }

    public void setDateUpdateReg(){
        this.dateUpdate=new Date(Calendar.getInstance().getTime().getTime());
    }

    public void setDatePublish() {
        this.datePublish=new Date(Calendar.getInstance().getTime().getTime());
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDatePublish(Date datePublish) {
        this.datePublish = datePublish;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
