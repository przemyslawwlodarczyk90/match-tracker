package org.example.handler;

import org.example.exception.MatchNotFoundException;
import org.example.model.Match;
import org.example.repository.InMemoryMatchRepository;
import org.example.repository.MatchRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinishGameHandlerTest {

    @Test
    void shouldRemoveMatchFromRepository() {
        InMemoryMatchRepository repository = new InMemoryMatchRepository();
        repository.add(new Match("Germany", "France"));

        FinishGameHandler handler = new FinishGameHandler(repository);
        handler.handle("Germany", "France");

        assertTrue(repository.findAll().isEmpty());
    }

    @Test
    void shouldThrowWhenFinishingNonExistentMatch() {
        MatchRepository repository = new InMemoryMatchRepository();
        FinishGameHandler handler = new FinishGameHandler(repository);

        assertThrows(MatchNotFoundException.class, () ->
                handler.handle("Germany", "Italy")
        );
    }
}
