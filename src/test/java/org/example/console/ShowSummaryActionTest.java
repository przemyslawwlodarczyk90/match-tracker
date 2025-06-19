package org.example.console;

import org.example.facade.ScoreBoardFacade;
import org.example.model.Match;
import org.example.repository.InMemoryMatchRepository;
import org.junit.jupiter.api.*;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

class ShowSummaryActionTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void shouldDisplayCurrentMatchesSummary() {
        // given
        InMemoryMatchRepository repository = new InMemoryMatchRepository();
        repository.add(new Match("Spain", "Brazil"));
        ScoreBoardFacade facade = new ScoreBoardFacade(repository);

        ShowSummaryAction action = new ShowSummaryAction(facade);

        // when
        action.execute();

        // then
        String output = outputStream.toString();
        assertThat(output)
                .contains("--- Current matches (ordered by total score, then time) ---")
                .contains("1. Spain 0 - Brazil 0");
    }

    @Test
    void shouldDisplayNoMatchesMessageWhenEmpty() {
        // given
        InMemoryMatchRepository repository = new InMemoryMatchRepository();
        ScoreBoardFacade facade = new ScoreBoardFacade(repository);

        ShowSummaryAction action = new ShowSummaryAction(facade);

        // when
        action.execute();

        // then
        String output = outputStream.toString();
        assertThat(output)
                .contains("No games currently on the scoreboard.");
    }
}
