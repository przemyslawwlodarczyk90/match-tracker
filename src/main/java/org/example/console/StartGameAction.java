package org.example.console;

import org.example.facade.ScoreBoardFacade;
import java.util.Scanner;


public class StartGameAction implements MenuAction {
    private final ScoreBoardFacade facade;
    private final Scanner scanner;

    public StartGameAction(ScoreBoardFacade facade, Scanner scanner) {
        this.facade = facade;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Home team: ");
        String home = scanner.nextLine().trim();
        System.out.print("Away team: ");
        String away = scanner.nextLine().trim();
        facade.startGame(home, away);
        System.out.println("Game started: " + home + " vs " + away);
    }
}
