package org.example.handler;

import org.example.exception.MatchNotFoundException;
import org.example.model.Match;
import org.example.repository.InMemoryMatchRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UpdateScoreHandlerTest {

    @Test
    void shouldUpdateMatchScore() {
        // given
        InMemoryMatchRepository repository = new InMemoryMatchRepository();
        repository.add(new Match("Italy", "England"));
        UpdateScoreHandler handler = new UpdateScoreHandler(repository);

        // when
        handler.handle("Italy", "England", 3, 2);

        // then
        assertThat(repository.find("Italy", "England"))
                .isPresent()
                .get()
                .satisfies(match -> {
                    assertThat(match.getHomeScore()).isEqualTo(3);
                    assertThat(match.getAwayScore()).isEqualTo(2);
                });
    }

    @Test
    void shouldThrowIfMatchNotFound() {
        // given
        InMemoryMatchRepository repository = new InMemoryMatchRepository();
        UpdateScoreHandler handler = new UpdateScoreHandler(repository);

        // when + then
        assertThatThrownBy(() -> handler.handle("Japan", "USA", 1, 1))
                .isInstanceOf(MatchNotFoundException.class)
                .hasMessage("Match not found: Japan vs USA");
    }
}
