package pl.agh.lab10;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class representing a Zombie implementing the Sprite interface
 */
public class Zombie implements Sprite {

    BufferedImage tape;    // Buffered image object to hold the zombie image
    int x;           // x position of the zombie on the screen
    int y;            // y position of the zombie on the screen
    double scale;     // Scale factor of the zombie image

    int index = 0;          // Index of the current zombie image
    int HEIGHT = 548;       // Height of the zombie image
    int WIDTH = 1226;       // Width of the zombie image

    int x2 = 626;           // x position of the second part of the zombie image
    int y2 = 320;           // y position of the second part of the zombie image

    /**
     * Constructor with parameters to set the values of x, y, scale, and tape
     */
    Zombie(int x, int y, double scale, BufferedImage tape) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.tape = tape;
    }

    /**
     * Method to draw the zombie on the screen using Graphics object and JPanel object
     */
    public void draw(Graphics g, JPanel parent) {
        // Get the sub-image of the zombie image with the current index
        Image img = tape.getSubimage(index * 40, 0, 40, 62);
        // Draw the sub-image on the screen using Graphics object with the given x, y, scale, and other variables
        g.drawImage(img, x, y - (int) (HEIGHT * scale) / 2 + y2, (int) ((WIDTH - x2) * scale), (int) (HEIGHT * scale), parent);
    }
    /**
     * Method to move the zombie to the next position
     */
    public void next() {
        this.x -= 20 * scale;       // Move the zombie to the left with a factor of scale
        index = (index + 1) % 10;   // Update the index for the next zombie image
    }

    /**
     * Method to check if the zombie is visible on the screen
     */
    @Override
    public boolean isVisible() {
        if (x + (int) ((WIDTH - x2) * scale) >= 0 && x <= WIDTH) {
            return true;
        } else {
            SynchronizedSprites.increment();    // Increment the counter of invisible sprites
            return false;
        }
    }

    /**
     * This method checks whether a given point (_x, _y) is within the bounding box of the zombie sprite.
     * It returns true if the point is not within the bounding box, and false otherwise.
     */
    public boolean isHit(int _x, int _y) {
        return !(_x < x || _x > x + (int) ((WIDTH - x2) * scale) || _y < y - (int) (HEIGHT * scale) / 2 + y2 || _y > y + (int) (HEIGHT * scale) / 2 + y2);
    }

    /**
     * This method checks if the current zombie sprite is closer to the player than the other sprite.
     * If the other sprite is not a zombie, it returns false.
     */
    public boolean isCloser(Sprite other) {
        if (other instanceof Zombie z) {
            return scale > z.scale;
        }
        return false;
    }
}
