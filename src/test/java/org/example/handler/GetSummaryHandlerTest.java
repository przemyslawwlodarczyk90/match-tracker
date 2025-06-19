package org.example.handler;

import org.example.model.Match;
import org.example.repository.InMemoryMatchRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GetSummaryHandlerTest {

    @Test
    void shouldReturnMatchesSortedByTotalScoreThenStartTime() {
        // given
        InMemoryMatchRepository repository = new InMemoryMatchRepository();
        LocalDateTime now = LocalDateTime.now();

        Match m1 = new Match("Germany", "France", now);
        m1.setHomeScore(2);
        m1.setAwayScore(2);

        Match m2 = new Match("Spain", "Brazil", now.plusSeconds(1));
        m2.setHomeScore(1);
        m2.setAwayScore(1);

        Match m3 = new Match("Argentina", "Australia", now.plusSeconds(2));
        m3.setHomeScore(3);
        m3.setAwayScore(2);

        repository.add(m1);
        repository.add(m2);
        repository.add(m3);

        GetSummaryHandler handler = new GetSummaryHandler(repository);

        // when
        List<Match> result = handler.handle();

        // then
        assertThat(result).containsExactly(m3, m1, m2);
    }

    @Test
    void shouldReturnEmptyListWhenNoMatchesExist() {
        // given
        InMemoryMatchRepository repository = new InMemoryMatchRepository();
        GetSummaryHandler handler = new GetSummaryHandler(repository);

        // when
        List<Match> result = handler.handle();

        // then
        assertThat(result).isEmpty();
    }
}
