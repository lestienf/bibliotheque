package com.projects.bibliotheque.application.jpaOutputPort;

import com.projects.bibliotheque.domain.Lecteur;

import java.util.Optional;

public interface LecteurRepositoryPort {

    public Optional<Lecteur> findByEmail(String email);

    public void save(Lecteur lecteur);
}
