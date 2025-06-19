package org.example.console;

import org.example.facade.ScoreBoardFacade;
import org.example.repository.InMemoryMatchRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

class StartGameActionTest {

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
    void shouldStartGameAndPrintConfirmation() {
        // given
        String userInput = "England\nPoland\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(inputStream);
        ScoreBoardFacade facade = new ScoreBoardFacade(new InMemoryMatchRepository());
        StartGameAction action = new StartGameAction(facade, scanner);

        // when
        action.execute();

        // then
        String output = outputStream.toString();
        assertThat(output)
                .contains("Home team:")
                .contains("Away team:")
                .contains("Game started: England vs Poland");
    }
}
