package pl.agh.lab10;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class ZombieFactory implements SpriteFactory {
    BufferedImage tape = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/walkingdead.png")));

    /**
     * Creates a new Zombie sprite at the given coordinates with a random scale.
     * @param x The x coordinate of the Zombie sprite.
     * @param y The y coordinate of the Zombie sprite.
     * @return The newly created Zombie sprite.
     */
    @Override
    public Sprite newSprite(int x, int y) {
        // Generate a random scale between 0.1 and 0.4
        double scale = ThreadLocalRandom.current().nextDouble(0.1, 0.4);
        // Create a new Zombie sprite with the given coordinates, scale, and image
        return new Zombie(x, y, scale, tape);
    }

    // Private constructor to prevent external instantiation
    private ZombieFactory() throws IOException {}

    // Singleton instance of the ZombieFactory class
    private static ZombieFactory instance = null;

    // Initialize the singleton instance with a private constructor and handle IOExceptions
    static {
        try {
            instance = new ZombieFactory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the singleton instance of the ZombieFactory class.
     * @return The singleton instance of the ZombieFactory class.
     */
    public static ZombieFactory get() {
        return instance;
    }
}
