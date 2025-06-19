package org.example.handler;

import org.example.exception.DuplicateMatchException;
import org.example.model.Match;
import org.example.repository.MatchRepository;

public class StartGameHandler {

    private final MatchRepository repository;

    public StartGameHandler(MatchRepository repository) {
        this.repository = repository;
    }

    public void handle(String home, String away) {
        if (repository.find(home, away).isPresent()) {
            throw new DuplicateMatchException(home, away);
        }
        Match match = new Match(home, away);
        repository.add(match);
    }
}
