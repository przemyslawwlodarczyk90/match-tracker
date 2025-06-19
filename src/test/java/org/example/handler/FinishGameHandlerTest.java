package org.example.handler;

import org.example.exception.MatchNotFoundException;
import org.example.model.Match;
import org.example.repository.InMemoryMatchRepository;
import org.example.repository.MatchRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinishGameHandlerTest {

    @Test
    void shouldRemoveMatchFromRepository() {
        // given
        InMemoryMatchRepository repository = new InMemoryMatchRepository();
        repository.add(new Match("Germany", "France"));
        FinishGameHandler handler = new FinishGameHandler(repository);

        // when
        handler.handle("Germany", "France");

        // then
        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    void shouldThrowWhenFinishingNonExistentMatch() {
        // given
        MatchRepository repository = new InMemoryMatchRepository();
        FinishGameHandler handler = new FinishGameHandler(repository);

        // when + then
        assertThatThrownBy(() -> handler.handle("Germany", "Italy"))
                .isInstanceOf(MatchNotFoundException.class)
                .hasMessage("Match not found: Germany vs Italy");
    }
}
