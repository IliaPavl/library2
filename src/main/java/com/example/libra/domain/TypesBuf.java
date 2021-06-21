package com.example.libra.domain;

public class TypesBuf {
    private Long idType;
    private String nameType;
    private Integer howBooks;

    public TypesBuf(Long idType, String nameType, Integer howBooks) {
        this.idType = idType;
        this.nameType = nameType;
        this.howBooks = howBooks;
    }

    public TypesBuf() {
    }

    public Long getIdType() {
        return idType;
    }

    public void setIdType(Long idType) {
        this.idType = idType;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public Integer getHowBooks() {
        return howBooks;
    }

    public void setHowBooks(Integer howBooks) {
        this.howBooks = howBooks;
    }
}
