package com.projects.bibliotheque.domain;

import com.sun.jdi.request.StepRequest;

public class Reservation {

    private Book book;

    private Lecteur lecteur;

    private String status;

    public Reservation(Book book, Lecteur lecteur) {
        this.book = book;
        this.lecteur = lecteur;
        this.setStatus(book);
    }

    public Book getBook() {
        return this.book;
    }

    public Lecteur getLecteur() {
        return this.lecteur;
    }

    public String getStatus() {
        return this.status;
    }

    private void setStatus(Book book) {
        if(book.getNombreExemplaires() > 0) {
            this.status = "READY_FOR_PICKUP";
        } else {
            this.status = "WAITING";
        }
    }

}
