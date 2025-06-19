package org.example.console;

import org.example.facade.ScoreBoardFacade;
import java.util.Scanner;

public class UpdateScoreAction implements MenuAction {
    private final ScoreBoardFacade facade;
    private final Scanner scanner;

    public UpdateScoreAction(ScoreBoardFacade facade, Scanner scanner) {
        this.facade = facade;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        // To be implemented
    }
}
