package org.example.console;

import org.example.facade.ScoreBoardFacade;

public class ShowSummaryAction implements MenuAction {
    private final ScoreBoardFacade facade;

    public ShowSummaryAction(ScoreBoardFacade facade) {
        this.facade = facade;
    }

    @Override
    public void execute() {
        var summary = facade.getSummary();
        System.out.println("--- Current matches (ordered by total score, then time) ---");
        if (summary.isEmpty()) {
            System.out.println("No games currently on the scoreboard.");
        } else {
            int idx = 1;
            for (var m : summary) {
                System.out.printf("%d. %s %d - %s %d%n",
                        idx++,
                        m.getHomeTeam(), m.getHomeScore(),
                        m.getAwayTeam(), m.getAwayScore());
            }
        }
    }

}
