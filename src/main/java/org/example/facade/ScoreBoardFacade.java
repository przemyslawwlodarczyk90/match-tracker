package org.example.facade;

import org.example.handler.FinishGameHandler;
import org.example.handler.GetSummaryHandler;
import org.example.handler.StartGameHandler;
import org.example.handler.UpdateScoreHandler;
import org.example.model.Match;
import org.example.repository.MatchRepository;

import java.util.List;

public class ScoreBoardFacade {

    private final StartGameHandler startGameHandler;
    private final UpdateScoreHandler updateScoreHandler;
    private final FinishGameHandler finishGameHandler;
    private final GetSummaryHandler getSummaryHandler;

    public ScoreBoardFacade(MatchRepository repository) {
        this.startGameHandler = new StartGameHandler(repository);
        this.updateScoreHandler = new UpdateScoreHandler(repository);
        this.finishGameHandler = new FinishGameHandler(repository);
        this.getSummaryHandler = new GetSummaryHandler(repository);
    }

    public void startGame(String home, String away) {
        startGameHandler.handle(home, away);
    }

    public void updateScore(String home, String away, int homeScore, int awayScore) {
        updateScoreHandler.handle(home, away, homeScore, awayScore);
    }

    public void finishGame(String home, String away) {
        finishGameHandler.handle(home, away);
    }

    public List<Match> getSummary() {
        return getSummaryHandler.handle();
    }
}
