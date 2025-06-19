package org.example.console;

public class UnknownAction implements MenuAction {
    @Override
    public void execute() {
        System.out.println("Unknown option. Please choose 1-5.");
    }}