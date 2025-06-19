package org.example.handler;

import org.example.exception.DuplicateMatchException;
import org.example.exception.TeamAlreadyPlayingException;
import org.example.model.Match;
import org.example.repository.InMemoryMatchRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        assertThat(repository.findAll()).hasSize(1)
                .first()
                .satisfies(match -> {
                    assertThat(match.getHomeTeam()).isEqualTo(homeTeam);
                    assertThat(match.getAwayTeam()).isEqualTo(awayTeam);
                    assertThat(match.getHomeScore()).isZero();
                    assertThat(match.getAwayScore()).isZero();
                });

        assertThat(repository.find(homeTeam, awayTeam)).isPresent();
    }

    @Test
    void shouldThrowExceptionWhenMatchAlreadyExists() {
        // given
        InMemoryMatchRepository repository = new InMemoryMatchRepository();
        repository.add(new Match("Spain", "Brazil"));
        StartGameHandler handler = new StartGameHandler(repository);

        // when + then
        assertThatThrownBy(() -> handler.handle("Spain", "Brazil"))
                .isInstanceOf(DuplicateMatchException.class)
                .hasMessage("Match already exists: Spain vs Brazil");
    }

    @Test
    void shouldThrowWhenTeamIsAlreadyPlaying() {
        // given
        InMemoryMatchRepository repository = new InMemoryMatchRepository();
        StartGameHandler handler = new StartGameHandler(repository);
        handler.handle("England", "Poland"); // England i Poland już grają

        // when + then
        assertThatThrownBy(() -> handler.handle("England", "France"))
                .isInstanceOf(TeamAlreadyPlayingException.class)
                .hasMessage("Team already playing: England");


        assertThatThrownBy(() -> handler.handle("Germany", "Poland"))
                .isInstanceOf(TeamAlreadyPlayingException.class)
                .hasMessage("Team already playing: Poland");
    }

}
