package org.example.comparator;

import org.example.model.Match;

import java.util.Comparator;

public class MatchSummaryComparator implements Comparator<Match> {
    @Override
    public int compare(Match m1, Match m2) {
        int totalScore1 = m1.getHomeScore() + m1.getAwayScore();
        int totalScore2 = m2.getHomeScore() + m2.getAwayScore();

        int scoreComparison = Integer.compare(totalScore2, totalScore1);
        if (scoreComparison != 0) {
            return scoreComparison;
        }

        return m2.getStartTime().compareTo(m1.getStartTime());
    }
}
