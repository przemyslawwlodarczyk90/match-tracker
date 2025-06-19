package org.example.handler;

import org.example.comparator.MatchSummaryComparator;
import org.example.model.Match;
import org.example.repository.MatchRepository;

import java.util.List;

public class GetSummaryHandler {

    private final MatchRepository repository;

    public GetSummaryHandler(MatchRepository repository) {
        this.repository = repository;
    }

    public List<Match> handle() {
        return repository.findAll().stream()
                .sorted(new MatchSummaryComparator())
                .toList();
    }
}
