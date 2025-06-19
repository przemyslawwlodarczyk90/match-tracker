package org.example.exception;

public class MatchNotFoundException extends RuntimeException {
    public MatchNotFoundException(String home, String away) {
        super("Match not found: " + home + " vs " + away);
    }
}
