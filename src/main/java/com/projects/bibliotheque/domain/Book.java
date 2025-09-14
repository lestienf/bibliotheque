package com.projects.bibliotheque.domain;

public class Book {

    private String title;
    private Integer nombreExemplaires;

    public Book(String title, Integer nombreExemplaires) {
        this.title = title;
        this.nombreExemplaires = nombreExemplaires;
    }

    public String getTitle() {
        return this.title;
    }

    public Integer getNombreExemplaires() {
        return this.nombreExemplaires;
    }
}
