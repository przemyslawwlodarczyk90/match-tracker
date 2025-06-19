package org.example.handler;

import org.example.repository.MatchRepository;

public class UpdateScoreHandler {

    private final MatchRepository repository;

    public UpdateScoreHandler(MatchRepository repository) {
        this.repository = repository;
    }

    public void handle(String home, String away, int homeScore, int awayScore) {

    }
}
