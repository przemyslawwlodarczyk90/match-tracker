package org.example.console;

import org.example.facade.ScoreBoardFacade;
import org.example.repository.InMemoryMatchRepository;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreBoardConsoleMenuTest {

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
    void fullFlowShouldWork() {
        // given
        StringBuilder sb = new StringBuilder();
        sb.append("1\nEngland\nPoland\n");      // Start game
        sb.append("2\n1\n2\n3\n");              // Update score
        sb.append("4\n");                       // Show summary
        sb.append("3\nEngland\nPoland\n");      // Finish game
        sb.append("4\n");                       // Show summary
        sb.append("5\n");                       // Exit

        ByteArrayInputStream inputStream = new ByteArrayInputStream(sb.toString().getBytes());
        Scanner scanner = new Scanner(inputStream);
        ScoreBoardFacade facade = new ScoreBoardFacade(new InMemoryMatchRepository());
        ScoreBoardConsoleMenu menu = new ScoreBoardConsoleMenu(facade, scanner);

        // when
        menu.run();

        // then
        String output = outputStream.toString();
        assertThat(output)
                .contains("Game started: England vs Poland")
                .contains("Score updated for England vs Poland")
                .contains("1. England 2 - Poland 3")
                .contains("Game finished: England vs Poland")
                .contains("No games currently on the scoreboard.")
                .contains("Bye!");
    }
}
