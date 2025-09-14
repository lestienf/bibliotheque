package com.projects.bibliotheque.application.useCase;

import com.projects.bibliotheque.domain.Book;
import com.projects.bibliotheque.domain.Lecteur;
import com.projects.bibliotheque.domain.Reservation;
import org.springframework.stereotype.Service;

@Service
public class ReserveBookUseCase {

    public Reservation reserve(Lecteur lecteur, Book book) {
        return new Reservation(book, lecteur);
    }
}
