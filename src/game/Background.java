package game;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Background {
    PApplet app;
    PImage image;
    float parallaxStrengthX = 0.1f; 
    float parallaxStrengthY = 0.1f; 


    public Background(PApplet app) {
        this.app = app;
        image = app.loadImage("../assets/sprites/background-dark.jpg");
    }

    public void renderWithParallax(PVector parallaxPosition) {
        float offsetX = -(parallaxPosition.x - app.width/2) * parallaxStrengthX;
        float offsetY = -(parallaxPosition.y - app.height/2) * parallaxStrengthY;

        // Tile size (use image size so it works if image != window size)
        int tileW = image.width;
        int tileH = image.height;

        // Compute which 2x2 block of tiles might intersect the screen.
        // This guarantees at most 4 tiles are drawn while allowing tiling left/right/top/bottom.
        int startCol = (int)Math.floor(-offsetX / tileW);
        int startRow = (int)Math.floor(-offsetY / tileH);

        for (int cx = startCol; cx <= startCol + 1; cx++) {
            for (int ry = startRow; ry <= startRow + 1; ry++) {
                app.image(image, cx * tileW + offsetX, ry * tileH + offsetY);
            }
        }
    }
}
