package com.projects.bibliotheque.domain;


public class Email {

    private String email;

    public Email(String email) {
        if(this.emailInvalid(email)) {
            throw new IllegalArgumentException("Email is invalid %s".formatted(email));
        }
        this.email = email;
    }

    private boolean emailInvalid(String email) {
        return !email.contains("@");
    }

    public String getEmailValue() {
        return this.email;
    }
}
