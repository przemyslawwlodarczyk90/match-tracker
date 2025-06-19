package org.example.handler;

import org.example.model.Match;
import org.example.repository.InMemoryMatchRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateScoreHandlerTest {

    @Test
    void shouldUpdateMatchScore() {
        InMemoryMatchRepository repository = new InMemoryMatchRepository();
        repository.add(new Match("Italy", "England"));
        UpdateScoreHandler handler = new UpdateScoreHandler(repository);

        handler.handle("Italy", "England", 3, 2);

        Match match = repository.find("Italy", "England").orElseThrow();
        assertEquals(3, match.getHomeScore());
        assertEquals(2, match.getAwayScore());
    }

    @Test
    void shouldThrowIfMatchNotFound() {
        InMemoryMatchRepository repository = new InMemoryMatchRepository();
        UpdateScoreHandler handler = new UpdateScoreHandler(repository);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            handler.handle("Japan", "USA", 1, 1);
        });

        assertEquals("Match Japan vs USA not found", exception.getMessage());
    }
}
