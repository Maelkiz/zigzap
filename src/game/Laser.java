package game;

import processing.core.*;

public class Laser extends Particle {
    public PApplet app;
    PVector direction;
    public int speed;
    public int color;
    public int length;


    public Laser(PApplet app, PVector position, PVector direction) {
        super(position.x, position.y);
        this.app = app;
        speed = 15000;
        this.direction = direction.normalize();
        velocity.set(this.direction.x, this.direction.y).mult(speed);
        color = Colors.rgb(255, 150, 150);
        length = 150;
    }

    public void render() {
        PVector front = position.copy().add(direction.copy().mult(length));
        PVector back = position.copy();

        app.push();
        // Light
        app.stroke(Colors.rgba(Colors.WHITE, 30));
        app.strokeWeight(20);
        app.line(back.x, back.y, front.x, front.y);
        app.stroke(Colors.rgba(color, 30));
        app.strokeWeight(14);
        app.line(back.x, back.y, front.x, front.y);

        // Color
        app.stroke(color);
        app.strokeWeight(8);
        app.line(back.x, back.y, front.x, front.y);

        // White
        app.stroke(Colors.WHITE);
        app.strokeWeight(2);
        app.line(back.x, back.y, front.x, front.y);
        app.pop();
    }
}
