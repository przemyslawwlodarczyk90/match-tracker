package org.example.exception;

public class TeamAlreadyPlayingException extends RuntimeException {
    public TeamAlreadyPlayingException(String team) {
        super("Team already playing: " + team);
    }
}