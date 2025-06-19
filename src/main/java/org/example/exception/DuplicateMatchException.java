package org.example.exception;

public class DuplicateMatchException extends RuntimeException {
    public DuplicateMatchException(String home, String away) {
        super("Match already exists: " + home + " vs " + away);
    }
}
