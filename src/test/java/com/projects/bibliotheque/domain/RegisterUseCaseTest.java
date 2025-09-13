package com.projects.bibliotheque.domain;

import com.projects.bibliotheque.application.exception.BusinessException;
import com.projects.bibliotheque.application.jpaOutputPort.LecteurRepositoryPort;
import com.projects.bibliotheque.application.useCase.RegisterUseCase;
import com.projects.bibliotheque.infra.adapter.jpa.lecteur.LecteurRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterUseCaseTest {

    LecteurRepositoryPort lecteurRepositoryPort;
    RegisterUseCase registerUseCase;

    @BeforeEach
    void setUp() {
        this.lecteurRepositoryPort =  new LecteurRepositoryAdapter();
        this.registerUseCase = new RegisterUseCase(lecteurRepositoryPort);
    }

    @Test
    void should_register_lecteur_with_generated_id_and_default_status() {
        // GIVEN
        String validName = "nomValide";
        String emailValide = "TEST@GMAIL.COM";

        // WHEN
        registerUseCase.register(validName, emailValide);
        Optional<Lecteur> lecteurOptional = lecteurRepositoryPort.findByEmail(emailValide);

        // THEN
        assertTrue(lecteurOptional.isPresent());
        Lecteur lecteurCreated = lecteurOptional.get();

        assertNotNull(lecteurCreated.getIdentifiant());
        assertEquals("ACTIVE", lecteurCreated.getStatus());
        assertEquals(validName, lecteurCreated.getName());
        assertEquals(emailValide, lecteurCreated.getEmail().getEmailValue());
    }

    @Test
    void should_throw_error_when_email_is_already_exist() {
        // GIVEN
        String email = "test@test.com";
        Lecteur lecteur = new Lecteur("nomTest", email);
        lecteurRepositoryPort.save(lecteur);

        //THEN
        assertThrows(BusinessException.class, () -> registerUseCase.register("nomTest2", email));
    }

    @Test
    void should_throw_error_when_email_is_invalid() {
        // GIVEN
        String invalidEmail = "test";
        String nomValid = "testName";

        //WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> this.registerUseCase.register(nomValid, invalidEmail));
    }
}
