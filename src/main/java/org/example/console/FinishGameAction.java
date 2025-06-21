package org.example.console;

import org.example.facade.ScoreBoardFacade;
import org.example.model.Match;

import java.util.List;
import java.util.Scanner;

public class FinishGameAction implements MenuAction {
    private final ScoreBoardFacade facade;
    private final Scanner scanner;

    public FinishGameAction(ScoreBoardFacade facade, Scanner scanner) {
        this.facade = facade;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        List<Match> currentMatches = facade.getSummary();
        if (currentMatches.isEmpty()) {
            System.out.println("No games available to finish.");
            return;
        }
        System.out.println("--- Choose a game to finish ---");
        for (int i = 0; i < currentMatches.size(); i++) {
            Match m = currentMatches.get(i);
            System.out.printf("%d. %s %d - %s %d%n",
                    i + 1,
                    m.getHomeTeam(), m.getHomeScore(),
                    m.getAwayTeam(), m.getAwayScore());
        }
        System.out.print("Enter game number: ");
        int gameNumber;
        try {
            gameNumber = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }
        if (gameNumber < 1 || gameNumber > currentMatches.size()) {
            System.out.println("Invalid game number.");
            return;
        }
        Match selected = currentMatches.get(gameNumber - 1);
        facade.finishGame(selected.getHomeTeam(), selected.getAwayTeam());
        System.out.println("Game finished: " + selected.getHomeTeam() + " vs " + selected.getAwayTeam());
    }
}
