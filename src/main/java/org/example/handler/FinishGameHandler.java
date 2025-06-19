package org.example.handler;

import org.example.exception.MatchNotFoundException;
import org.example.repository.MatchRepository;

public class FinishGameHandler {

    private final MatchRepository repository;

    public FinishGameHandler(MatchRepository repository) {
        this.repository = repository;
    }

    public void handle(String home, String away) {
        repository.find(home, away)
                .ifPresentOrElse(
                        repository::remove,
                        () -> { throw new MatchNotFoundException(home, away); }
                );
    }
}
