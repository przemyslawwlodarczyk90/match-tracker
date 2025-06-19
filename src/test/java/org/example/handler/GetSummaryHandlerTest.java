package org.example.handler;

import org.example.model.Match;
import org.example.repository.InMemoryMatchRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetSummaryHandlerTest {

    @Test
    void shouldReturnMatchesSortedByTotalScoreThenStartTime() throws InterruptedException {
        InMemoryMatchRepository repository = new InMemoryMatchRepository();

        Match m1 = new Match("Germany", "France");
        m1.setHomeScore(2);
        m1.setAwayScore(2);

        Thread.sleep(10);
        Match m2 = new Match("Spain", "Brazil");
        m2.setHomeScore(1);
        m2.setAwayScore(1);

        Thread.sleep(10);
        Match m3 = new Match("Argentina", "Australia");
        m3.setHomeScore(3);
        m3.setAwayScore(2);

        repository.add(m1);
        repository.add(m2);
        repository.add(m3);

        GetSummaryHandler handler = new GetSummaryHandler(repository);
        List<Match> result = handler.handle();

        assertEquals(List.of(m3, m1, m2), result);
    }

    @Test
    void shouldReturnEmptyListWhenNoMatchesExist() {
        InMemoryMatchRepository repository = new InMemoryMatchRepository();
        GetSummaryHandler handler = new GetSummaryHandler(repository);

        List<Match> result = handler.handle();

        assertTrue(result.isEmpty());
    }
}
