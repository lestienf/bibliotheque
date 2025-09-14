package com.projects.bibliotheque.domain;

import com.projects.bibliotheque.application.useCase.ReserveBookUseCase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReserverBookUseCaseTest {


    @ParameterizedTest
    @CsvSource({"WAITING, 0", "READY_FOR_PICKUP, 1"})
    void should_create_reservation(String statusExpected, Integer nombreExemplaires) {
        // GIVEN
        Lecteur lecteur = new Lecteur("nomTest", "test@test.com");
        Book book = new Book("Le petit prince", nombreExemplaires);
        ReserveBookUseCase reserveBookUseCase = new ReserveBookUseCase();

        // WHEN
        Reservation reservation = reserveBookUseCase.reserve(lecteur, book);

        //THEN
        assertEquals(statusExpected, reservation.getStatus());
    }
}
