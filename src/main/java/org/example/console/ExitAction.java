package org.example.console;

public class ExitAction implements MenuAction {
    @Override
    public void execute() {
        System.out.println("Bye!");
    }
}