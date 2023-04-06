package pl.agh.lab10;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ZombieGame {
    /**
     * The run method initializes and runs the game.
     */
    public static void run() {
        // Create a new JFrame object for the game
        JFrame frame = new JFrame("Zombie");

        // Create a new DrawPanel object and set it as the content pane for the JFrame
        DrawPanel panel = new DrawPanel(Main.class.getResource("/resources/game_background_lower.png"), ZombieFactory.get());
        frame.setContentPane(panel);

        // Set the size and location of the JFrame
        frame.setSize(1226, 548);
        frame.setLocationRelativeTo(null);

        // Set the default close operation of the JFrame to dispose of it when closed
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Allow the JFrame to be resizable and visible
        frame.setResizable(true);
        frame.setVisible(true);

        // Add a window listener to the JFrame to exit the game when the window is closed
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        // Set the layout of the JFrame to null
        frame.setLayout(null);

        // Create an "EXIT" JButton object and add it to the JFrame
        JButton exitButton = new JButton("EXIT");
        exitButton.setForeground(Color.RED);
        exitButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        exitButton.setBounds(1100, 65, 80, 35);
        exitButton.setBackground(Color.PINK);
        exitButton.addActionListener(e -> {
            try {
                new UserInterface().end(panel.getStats());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            panel.animationThread.stop();

        });
        frame.add(exitButton);
    }
}
