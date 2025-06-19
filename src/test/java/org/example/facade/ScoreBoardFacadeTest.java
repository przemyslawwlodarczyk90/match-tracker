package org.example.facade;

import org.example.model.Match;
import org.example.repository.InMemoryMatchRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreBoardFacadeTest {

    @Test
    void shouldStartGameAndAddMatchToRepository() {
        ScoreBoardFacade facade = new ScoreBoardFacade(new InMemoryMatchRepository());

        facade.startGame("Spain", "Brazil");

        List<Match> summary = facade.getSummary();

        assertThat(summary).hasSize(1)
                .first()
                .satisfies(match -> {
                    assertThat(match.getHomeTeam()).isEqualTo("Spain");
                    assertThat(match.getAwayTeam()).isEqualTo("Brazil");
                    assertThat(match.getHomeScore()).isZero();
                    assertThat(match.getAwayScore()).isZero();
                });
    }

    @Test
    void shouldUpdateScoreOfExistingMatch() {
        ScoreBoardFacade facade = new ScoreBoardFacade(new InMemoryMatchRepository());
        facade.startGame("Germany", "France");

        facade.updateScore("Germany", "France", 3, 2);

        Match match = facade.getSummary().getFirst();
        assertThat(match.getHomeScore()).isEqualTo(3);
        assertThat(match.getAwayScore()).isEqualTo(2);
    }

    @Test
    void shouldRemoveMatchAfterFinishGame() {
        ScoreBoardFacade facade = new ScoreBoardFacade(new InMemoryMatchRepository());
        facade.startGame("Italy", "Argentina");

        facade.finishGame("Italy", "Argentina");

        assertThat(facade.getSummary()).isEmpty();
    }

    @Test
    void shouldReturnMatchesSortedByScoreAndTime() {
        InMemoryMatchRepository repository = new InMemoryMatchRepository();
        ScoreBoardFacade facade = new ScoreBoardFacade(repository);

        LocalDateTime now = LocalDateTime.now();

        repository.add(new Match("Team A", "Team B", now));
        repository.add(new Match("Team C", "Team D", now.plusSeconds(1)));

        facade.updateScore("Team A", "Team B", 1, 1); // total: 2
        facade.updateScore("Team C", "Team D", 3, 3); // total: 6

        List<Match> summary = facade.getSummary();

        assertThat(summary)
                .extracting(Match::getHomeTeam)
                .containsExactly("Team C", "Team A");
    }
}
