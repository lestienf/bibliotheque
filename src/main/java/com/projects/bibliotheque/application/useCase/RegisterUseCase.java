package com.projects.bibliotheque.application.useCase;


import com.projects.bibliotheque.application.exception.BusinessException;
import com.projects.bibliotheque.application.jpaOutputPort.LecteurRepositoryPort;
import com.projects.bibliotheque.domain.Lecteur;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class RegisterUseCase {

    private LecteurRepositoryPort lecteurRepositoryPort;

    public void register(String name, String email) {
        var lecteurAlreadyExist = this.lecteurRepositoryPort.findByEmail(email).isPresent();
        if(lecteurAlreadyExist) {
            throw new BusinessException("Lecteur already exist with this email %s".formatted(email));
        }

        var lecteur = new Lecteur(name, email);
        this.lecteurRepositoryPort.save(lecteur);
    }
}
