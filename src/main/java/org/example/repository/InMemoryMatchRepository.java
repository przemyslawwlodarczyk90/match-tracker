package org.example.repository;

import org.example.model.Match;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryMatchRepository extends MatchRepository {
    private final List<Match> matches = new ArrayList<>();

    @Override
    public void add(Match match) {
        matches.add(match);
    }

    @Override
    public void remove(Match match) {
        matches.remove(match);
    }

    @Override
    public Optional<Match> find(String homeTeam, String awayTeam) {
        return matches.stream()
                .filter(m -> m.getHomeTeam().equals(homeTeam) && m.getAwayTeam().equals(awayTeam))
                .findFirst();
    }

    @Override
    public List<Match> findAll() {
        return List.copyOf(matches);
    }
}
