package org.example.console;

import org.example.facade.ScoreBoardFacade;
import org.example.model.Match;
import org.example.repository.InMemoryMatchRepository;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

class FinishGameActionTest {

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
    void shouldFinishGameAndPrintConfirmation() {
        // given
        InMemoryMatchRepository repository = new InMemoryMatchRepository();
        repository.add(new Match("Italy", "France"));
        ScoreBoardFacade facade = new ScoreBoardFacade(repository);

        String userInput = "1\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(inputStream);

        FinishGameAction action = new FinishGameAction(facade, scanner);

        // when
        action.execute();

        // then
        String output = outputStream.toString();
        assertThat(output)
                .contains("--- Choose a game to finish ---")
                .contains("1. Italy 0 - France 0")
                .contains("Enter game number:")
                .contains("Game finished: Italy vs France");

        assertThat(repository.findAll()).isEmpty();
    }
}
