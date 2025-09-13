package com.projects.bibliotheque.domain;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Lecteur {

    private UUID identifiant;

    private String name;

    private Email email;

    private String status;

    public Lecteur(String name, String email) {
        this.identifiant = UUID.randomUUID();
        this.name = name;
        this.email = new Email(email);
        this.status = "ACTIVE";
    }


}
