package pl.agh.lab10;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class UserInterface {
    private ArrayList<Integer> stats;

    public UserInterface() {
    }

    public UserInterface(ArrayList<Integer> stats) {
        this.stats = stats;
    }

    public void start() throws IOException {
        JFrame frame = new JFrame("Start Interface");

        // Create a new DrawPanel object and set it as the content pane for the JFrame
        BufferedImage wPic = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/resources/game_start.png")));
        JLabel wIcon = new JLabel(new ImageIcon(wPic));
        frame.setContentPane(wIcon);

        // Set the size and location of the JFrame
        frame.setSize(1226, 548);
        frame.setLocationRelativeTo(null);

        // Set the default close operation of the JFrame to dispose of it when closed
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Allow the JFrame to be resizable and visible
        frame.setResizable(true);
        frame.setVisible(true);

        // Add a window listener to the JFrame to exit the game when the window is closed
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        // Set the layout of the JFrame to null
        frame.setLayout(null);

        JButton startButton = new JButton("START GAME");
        startButton.setForeground(Color.RED);
        startButton.setFont(new Font("Dialog", Font.PLAIN, 25));
        startButton.setBounds(860, 210, 270, 50);
        startButton.setBackground(Color.PINK);
        startButton.addActionListener(e -> ZombieGame.run());
        frame.add(startButton);

        // Create an "EXIT" JButton object and add it to the JFrame
        JButton exitButton = new JButton("EXIT");
        exitButton.setForeground(Color.RED);
        exitButton.setFont(new Font("Dialog", Font.PLAIN, 25));
        exitButton.setBounds(935, 275, 120, 50);
        exitButton.setBackground(Color.PINK);
        exitButton.addActionListener(e -> System.exit(0));
        frame.add(exitButton);
    }

    public void end(ArrayList<Integer> stats) throws IOException {
        JFrame frame = new JFrame("End Interface");

        BufferedImage wPic = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/resources/game_end.png")));
        JLabel wIcon = new JLabel(new ImageIcon(wPic));
        frame.setContentPane(wIcon);

        // Set the size and location of the JFrame
        frame.setSize(1226, 548);
        frame.setLocationRelativeTo(null);

        // Set the default close operation of the JFrame to dispose of it when closed
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Allow the JFrame to be resizable and visible
        frame.setResizable(true);
        frame.setVisible(true);

        // Add a window listener to the JFrame to exit the game when the window is closed
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        // Set the layout of the JFrame to null
        frame.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK);
        panel.setBounds(475, 150, 240, 30);
        frame.add(panel);
        JLabel stats_text1 = new JLabel();
        stats_text1.setText("Shots taken: " + stats.get(1));
        stats_text1.setBounds(500, 160, 200, 10);
        stats_text1.setFont(new Font("Dialog", Font.PLAIN, 15));
        stats_text1.setForeground(Color.RED);
        stats_text1.setVisible(true);
        panel.add(stats_text1);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.PINK);
        panel2.setBounds(475, 180, 240, 30);
        frame.add(panel2);
        JLabel stats_text2 = new JLabel();
        stats_text2.setText("Shots hit: " + stats.get(0));
        stats_text2.setBounds(500, 185, 200, 10);
        stats_text2.setFont(new Font("Dialog", Font.PLAIN, 15));
        stats_text2.setForeground(Color.RED);
        stats_text2.setVisible(true);
        panel2.add(stats_text2);

        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.PINK);
        panel3.setBounds(475, 210, 240, 40);
        frame.add(panel3);
        JLabel stats_text3 = new JLabel();
        stats_text3.setText("Zombies escaped: " + stats.get(2));
        stats_text3.setBounds(500, 190, 200, 10);
        stats_text3.setFont(new Font("Dialog", Font.PLAIN, 15));
        stats_text3.setForeground(Color.RED);
        stats_text3.setVisible(true);
        panel3.add(stats_text3);

        JPanel panel4 = new JPanel();
        panel4.setBackground(Color.PINK);
        panel4.setBounds(475, 240, 240, 40);
        frame.add(panel4);
        JLabel stats_text4 = new JLabel();
        stats_text4.setText("Accuracy: " + Math.round(stats.get(0) / (double) stats.get(1) * 100) + "%");
        stats_text4.setBounds(500, 155, 200, 10);
        stats_text4.setFont(new Font("Dialog", Font.PLAIN, 15));
        stats_text4.setForeground(Color.RED);
        stats_text4.setVisible(true);
        panel4.add(stats_text4);

        // Create an "EXIT" JButton object and add it to the JFrame
        JButton exitButton = new JButton("EXIT");
        exitButton.setForeground(Color.RED);
        exitButton.setFont(new Font("Dialog", Font.PLAIN, 25));
        exitButton.setBounds(535, 290, 120, 50);
        exitButton.setBackground(Color.PINK);
        exitButton.addActionListener(e -> System.exit(0));
        frame.add(exitButton);

        // Create a Restart JButton and add it to JFrame
        JButton restartButton = new JButton("RESTART GAME");
        restartButton.setForeground(Color.RED);
        restartButton.setFont(new Font("Dialog", Font.PLAIN, 25));
        restartButton.setBounds(475, 350, 240, 50);
        restartButton.setBackground(Color.PINK);
        restartButton.addActionListener(e -> ZombieGame.run());
        frame.add(restartButton);
    }
}

