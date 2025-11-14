package game;

import processing.core.*;

public final class Colors {
    private static PApplet app = new PApplet(); 
    
    // Achromatics
    public static final int BLACK = grayScale(0);
    public static final int WHITE = grayScale(255);

    // Primaries
    public static final int RED = rgb(255, 0, 0);
    public static final int GREEN = rgb(0, 255, 0);
    public static final int BLUE = rgb(0, 0, 255);
    public static final int YELLOW = rgb(255, 255, 0);
    public static final int MAGENTA = rgb(255, 0, 255);
    public static final int CYAN = rgb(0, 255, 255);

    // Others
    public static final int ORANGE = rgb(240, 120, 20);
    public static final int PURPLE = rgb(190, 0, 255);
    public static final int PINK = rgb(255, 20, 150);
    public static final int LIME = rgb(160, 255, 110);
    public static final int TEAL = rgb(0, 255, 170);
    public static final int LIGHT_BLUE = rgb(120, 180, 255);

    public static int grayScale(int value) {
        value = PApplet.constrain(value, 0, 255);
        return app.color(value);
    }

    public static int rgb(int r, int g, int b) {
        return app.color(r, g, b);
    }

    public static int rgba(int r, int g, int b, int a) {
        return app.color(r, g, b, a);
    }

    public static int rgba(int color, int alpha) {
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;
        return app.color(r, g, b, alpha);
    }

    public static int lerp(int c1, int c2, float lerpFactor) {
        return app.lerpColor(c1, c2, lerpFactor);
    }

    public static int lerpAlpha(int color, float lerpFactor) {
        lerpFactor = PApplet.constrain(lerpFactor, 0, 1);
        int alpha = (int) PApplet.map(lerpFactor, 0, 1, 0, 255);
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;
        return app.color(r, g, b, alpha);
    }

    private Colors() { }
}
