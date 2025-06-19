package org.example.console;

import org.example.facade.ScoreBoardFacade;
import org.example.model.Match;
import org.example.repository.InMemoryMatchRepository;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

class UpdateScoreActionTest {

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
    void shouldUpdateSelectedMatchScoreAndPrintConfirmation() {
        // given
        InMemoryMatchRepository repository = new InMemoryMatchRepository();
        Match match = new Match("England", "Poland");
        repository.add(match);
        ScoreBoardFacade facade = new ScoreBoardFacade(repository);

        // Menu: wybierz mecz nr 1, wpisz nowe wyniki
        String userInput = "1\n3\n2\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(inputStream);

        UpdateScoreAction action = new UpdateScoreAction(facade, scanner);

        // when
        action.execute();

        // then
        String output = outputStream.toString();
        assertThat(output)
                .contains("Choose a game to update score")
                .contains("1. England 0 - Poland 0")
                .contains("New home score:")
                .contains("New away score:")
                .contains("Score updated for England vs Poland");

        // Sprawdzamy też stan repozytorium
        Match updated = repository.find("England", "Poland").orElseThrow();
        assertThat(updated.getHomeScore()).isEqualTo(3);
        assertThat(updated.getAwayScore()).isEqualTo(2);
    }


}
