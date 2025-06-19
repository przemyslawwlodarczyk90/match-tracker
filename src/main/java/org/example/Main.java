package org.example;

import org.example.console.ScoreBoardConsoleMenu;
import org.example.facade.ScoreBoardFacade;
import org.example.repository.InMemoryMatchRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ScoreBoardFacade facade = new ScoreBoardFacade(new InMemoryMatchRepository());
        Scanner scanner = new Scanner(System.in);
        ScoreBoardConsoleMenu menu = new ScoreBoardConsoleMenu(facade, scanner);
        menu.run();
    }
}
