package main;

import main.GamePanel;

import javax.swing.JFrame; //folosit creare fereastra
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, FontFormatException {
        JFrame window = new JFrame ();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Wizard Tower");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); //face ca fereastra sa fie reglabila la o dimensiune dorita

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
        }
    }


