package org.example.console;

import org.example.facade.ScoreBoardFacade;

public class ShowSummaryAction implements MenuAction {
    private final ScoreBoardFacade facade;

    public ShowSummaryAction(ScoreBoardFacade facade) {
        this.facade = facade;
    }

    @Override
    public void execute() {
        // To be implemented
    }
}
