package pl.agh.lab10;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class DrawPanel extends JPanel implements CrossHairListener {
    private SpriteFactory factory;
    BufferedImage background;
    SynchronizedSprites sprites = new SynchronizedSprites();
    CrossHair crossHair = new CrossHair(this);
    int hitCounter = 0;
    int shotCounter = 0;
    double accuracy = 0.00;
    AnimationThread animationThread;

    // Constructor for the class DrawPanel that takes in a URL and a SpriteFactory object
    DrawPanel(URL backgroundImagageURL, SpriteFactory factory) {
        try {
            // Reading the background image from the URL
            background = ImageIO.read(backgroundImagageURL);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // Setting the factory variable to the provided SpriteFactory object
        this.factory = factory;
        // Creating an AnimationThread object and starting it
        animationThread = new AnimationThread();
        animationThread.start();
        // Adding the CrossHair object as a CrossHairListener and adding MouseMotionListener and MouseListener to the panel
        crossHair.addCrossHairListener(this);
        addMouseMotionListener(crossHair);
        addMouseListener(crossHair);
    }

    // Method to paint the panel
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // Drawing the background image
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        // Drawing the sprites and crosshair
        sprites.draw(g2d, this);
        crossHair.draw(g);
        // Drawing the score and shots taken on the panel
        g.setColor(Color.PINK);
        g.drawRect(1050, 25, 130, 33);
        g.fillRect(1050, 25, 130, 33);
        g.setColor(Color.RED);
        g.drawString("Score: " + hitCounter + " Escaped: " + SynchronizedSprites.escaped, 1060, 40);
        g.drawString("Shots taken: " + shotCounter, 1060, 53);

    }

    // Overriding the onShotsFired method of CrossHairListener interface
    @Override
    public void onShotsFired(int x, int y) {
        shotCounter++;
        // Looping through the sprites to see if any sprite has been hit by the shot
        for (int i = sprites.sprites.size() - 1; i >= 0; i--) {
            if (sprites.sprites.get(i).isHit(x, y)) {
                sprites.sprites.remove(sprites.sprites.get(i));
                hitCounter++;
                break;
            }
        }
    }

    // Method to get the stats of the game
    public ArrayList<Integer> getStats() {
        ArrayList<Integer> stats = new ArrayList<>();
        stats.add(hitCounter);
        stats.add(shotCounter);
        stats.add(SynchronizedSprites.escaped.intValue());
        SynchronizedSprites.escaped = new AtomicInteger(0);
        return stats;
    }

    // Define a Comparator for the Sprite class, which will sort Sprites by distance from the camera
    static class SpriteComparator implements Comparator<Sprite> {
        @Override
        public int compare(Sprite o1, Sprite o2) {
            // If o1 is closer to the camera than o2, return 1
            if (o1.isCloser(o2)) {
                return 1;
                // Otherwise, return -1
            } else {
                return -1;
            }
        }
    }

    // Define a Thread subclass for running an animation
    class AnimationThread extends Thread {
        public void run() {
            try {
                // Sleep for 1 second before starting the animation loop
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Enter an infinite loop to update the animation
            for (int i = 0; ; i++) {
                // Advance the Sprite collection to the next frame
                sprites.next();
                // Remove any Sprites that are not visible from the collection
                sprites.sprites.removeIf(sprite -> !sprite.isVisible());
                // Repaint the screen to show the updated Sprite positions
                repaint();
                try {
                    // Sleep for a short period of time to allow the screen to update
                    sleep(1000 / 120);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Every 70 iterations, add a new Sprite to the collection using the factory.newSprite method
                if (i % 70 == 0) {
                    sprites.add(factory.newSprite(getWidth(), (int) (0.1 * getHeight())));
                }
            }
        }
    }
}
