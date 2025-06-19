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

    @Test
    void shouldUpdateScoreOfExistingMatch() {
        ScoreBoardFacade facade = new ScoreBoardFacade(new InMemoryMatchRepository());
        facade.startGame("Germany", "France");

        facade.updateScore("Germany", "France", 3, 2);

        Match match = facade.getSummary().getFirst();
        assertEquals(3, match.getHomeScore());
        assertEquals(2, match.getAwayScore());
    }

    @Test
    void shouldRemoveMatchAfterFinishGame() {
        ScoreBoardFacade facade = new ScoreBoardFacade(new InMemoryMatchRepository());
        facade.startGame("Italy", "Argentina");

        facade.finishGame("Italy", "Argentina");

        assertTrue(facade.getSummary().isEmpty());
    }
    @Test
    void shouldReturnMatchesSortedByScoreAndTime() throws InterruptedException {
        ScoreBoardFacade facade = new ScoreBoardFacade(new InMemoryMatchRepository());

        facade.startGame("Team A", "Team B");
        Thread.sleep(10);
        facade.startGame("Team C", "Team D");

        facade.updateScore("Team A", "Team B", 1, 1); // total: 2
        facade.updateScore("Team C", "Team D", 3, 3); // total: 6

        List<Match> summary = facade.getSummary();

        assertEquals("Team C", summary.get(0).getHomeTeam());
        assertEquals("Team A", summary.get(1).getHomeTeam());
    }
}
