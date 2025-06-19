package org.example.console;

import org.example.facade.ScoreBoardFacade;

import java.util.Map;
import java.util.Scanner;

public class ScoreBoardConsoleMenu {
    private final Map<String, MenuAction> actions;
    private final Scanner scanner;

    public ScoreBoardConsoleMenu(ScoreBoardFacade facade, Scanner scanner) {
        this.scanner = scanner;
        this.actions = Map.of(
                "1", new StartGameAction(facade, scanner),
                "2", new UpdateScoreAction(facade, scanner),
                "3", new FinishGameAction(facade, scanner),
                "4", new ShowSummaryAction(facade),
                "5", new ExitAction()
        );
    }

    public void run() {
        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();
            MenuAction action = actions.getOrDefault(choice, new UnknownAction());
            try {
                action.execute();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            if (action instanceof ExitAction) break;
        }
    }

    private void printMenu() {
        System.out.println("\nFOOTBALL WORLD CUP SCOREBOARD");
        System.out.println("1. Start a new game");
        System.out.println("2. Update score");
        System.out.println("3. Finish game");
        System.out.println("4. Show summary");
        System.out.println("5. Exit");
        System.out.print("Choose option: ");
    }
}
