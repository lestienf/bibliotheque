package com.projects.bibliotheque.infra.adapter.jpa.lecteur;

import com.projects.bibliotheque.application.jpaOutputPort.LecteurRepositoryPort;
import com.projects.bibliotheque.domain.Lecteur;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class LecteurRepositoryAdapter implements LecteurRepositoryPort {

    private final Map<UUID, Lecteur> lecteurs = new HashMap<>();

    @Override
    public Optional<Lecteur> findByEmail(String email) {
        return lecteurs.values().stream()
                .filter(lecteur -> email.equals(lecteur.getEmail().getEmailValue()))
                .findFirst();
    }

    @Override
    public void save(Lecteur lecteur) {
        lecteurs.put(lecteur.getIdentifiant(), lecteur);
    }
}
