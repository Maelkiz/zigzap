package game;

import processing.core.PApplet;
import processing.core.PVector;

public class Light {
    private PApplet app;
    private PVector position;
    private int radius;
    private int color;
    private int layers;

    public Light(PApplet app, PVector position) {
        this(app, position, Colors.WHITE, 100);
    }

    public Light(PApplet app, PVector position, int color, int radius) {
        this.app = app;
        this.position = position;
        this.color = color;
        this.radius = radius;
        layers = radius / 10;
    }

    public void render() {
        app.push();
        for (int i = 0; i < layers; i++) {
            int minAlpha = 0;
            int maxAlpha = 10;
            int alpha = (int) PApplet.map(i, 0, layers, minAlpha, maxAlpha);
            app.fill(Colors.rgba(color, alpha));

            int minDiameter = radius / layers * 2;
            int maxDiameter = radius * 2;
            int diameter = (int) PApplet.map(i, 0, layers, minDiameter, maxDiameter);
            app.circle(position.x, position.y, diameter);
        }
        app.pop();
    }
}