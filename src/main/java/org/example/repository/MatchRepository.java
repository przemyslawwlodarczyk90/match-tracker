package org.example.repository;

import org.example.model.Match;

import java.util.List;
import java.util.Optional;

public abstract class MatchRepository {
    public abstract void add(Match match);
    public abstract void remove(Match match);
    public abstract Optional<Match> find(String homeTeam, String awayTeam);
    public abstract List<Match> findAll();
}
