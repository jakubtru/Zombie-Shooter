package pl.agh.lab10;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class SynchronizedSprites {
    ArrayList<Sprite> sprites = new ArrayList<>();   // Declare an ArrayList to hold Sprite objects
    static AtomicInteger escaped = new AtomicInteger(0);  // Declare a static AtomicInteger to count the number of sprites that have escaped

    // Synchronized method to move all sprites to their next position
    public synchronized void next() {
        for (var sprite : sprites) {
            sprite.next();
        }
    }

    // Synchronized static method to increment the count of escaped sprites
    public static synchronized void increment() {
        escaped.addAndGet(1);
    }

    // Synchronized method to add a new sprite to the ArrayList
    public synchronized void add(Sprite sprite) {
        sprites.add(sprite);
    }

    // Synchronized method to draw all sprites in order
    public synchronized void draw(Graphics2D g, DrawPanel drawPanel) {
        sprites.sort(new DrawPanel.SpriteComparator());  // Sort sprites by their position
        for (var sprite : sprites) {
            sprite.draw(g, drawPanel);  // Draw each sprite using its draw() method
        }
    }
}
