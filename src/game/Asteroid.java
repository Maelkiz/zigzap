package game;

import processing.core.*;

public final class Asteroid extends Particle {
    public PApplet app;
    public int size;
    public float speed;
    public CircleCollider collider;
    public int hp;

    public Asteroid(PApplet app, float x, float y) {
        super(x, y);
        this.app = app;
        size = 250;
        speed = 0.1f;
        velocity.set(0, 0);
        //velocity = (new PVector(app.random(-1, 1), app.random(-1, 1))).normalize().mult(speed);
        position.set(x, y);
        color = Colors.grayScale(100);
        collider = new CircleCollider(position, size / 2);
        hp = 3;
    }

    public void update() {
        position.add(velocity);
        collider.center = position;
        app.push();
        app.fill(color);
        app.circle(position.x, position.y, size);
        app.pop();
    }

    public void takeDamage(int damage) {
        hp -= damage;
    }
}
