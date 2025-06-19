package org.example.console;

import org.example.facade.ScoreBoardFacade;

import java.util.Scanner;


public class ScoreBoardConsoleMenu {

    private final ScoreBoardFacade facade;
    private final Scanner scanner;

    public ScoreBoardConsoleMenu(ScoreBoardFacade facade, Scanner scanner) {
        this.facade = facade;
        this.scanner = scanner;
    }

    public void run() {
        // To be implemented: main menu loop delegating to MenuAction
    }
}
