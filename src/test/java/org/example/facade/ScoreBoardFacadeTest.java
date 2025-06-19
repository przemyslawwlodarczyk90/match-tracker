package org.example.facade;

import org.example.model.Match;
import org.example.repository.InMemoryMatchRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardFacadeTest {

    @Test
    void shouldStartGameAndAddMatchToRepository() {
        ScoreBoardFacade facade = new ScoreBoardFacade(new InMemoryMatchRepository());

        facade.startGame("Spain", "Brazil");

        List<Match> summary = facade.getSummary();
        assertEquals(1, summary.size());
        Match match = summary.getFirst();
        assertEquals("Spain", match.getHomeTeam());
        assertEquals("Brazil", match.getAwayTeam());
        assertEquals(0, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
    }
}
