package org.example.handler;

import org.example.model.Match;
import org.example.repository.InMemoryMatchRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StartGameHandlerTest {

    @Test
    void shouldAddNewMatchToRepository() {
        // given
        InMemoryMatchRepository repository = new InMemoryMatchRepository();
        StartGameHandler handler = new StartGameHandler(repository);
        String homeTeam = "Nigeria";
        String awayTeam = "Japan";

        // when
        handler.handle(homeTeam, awayTeam);

        // then
        List<Match> allMatches = repository.findAll();
        assertEquals(1, allMatches.size(), "Repository should contain exactly one match");

        Optional<Match> match = repository.find(homeTeam, awayTeam);
        assertTrue(match.isPresent(), "Expected match to be present in repository");
        assertEquals(homeTeam, match.get().getHomeTeam());
        assertEquals(awayTeam, match.get().getAwayTeam());
        assertEquals(0, match.get().getHomeScore());
        assertEquals(0, match.get().getAwayScore());
    }
}
