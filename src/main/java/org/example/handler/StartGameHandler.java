package org.example.handler;

import org.example.model.Match;
import org.example.repository.MatchRepository;

public class StartGameHandler {

    private final MatchRepository repository;

    public StartGameHandler(MatchRepository repository) {
        this.repository = repository;
    }

    public void handle(String home, String away) {
        Match match = new Match(home, away);
        repository.add(match);
    }
}
