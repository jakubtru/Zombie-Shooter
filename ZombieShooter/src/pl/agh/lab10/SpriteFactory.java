package pl.agh.lab10;

/**
 Creates a new Sprite object with the given coordinates.
 @param 'x' the x-coordinate of the sprite
 @param 'y' the y-coordinate of the sprite
 @return a new Sprite object with the given coordinates
 */

public interface SpriteFactory {
    Sprite newSprite(int x,int y);
}
