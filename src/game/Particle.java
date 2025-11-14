package game;

import processing.core.PVector;

public class Particle {
    public final PVector position; 
    public final PVector velocity;
    public final PVector acceleration;
    public int color;

    public Particle(PVector position, PVector velocity, PVector acceleration, int color) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.color = color;
    }

    public Particle(PVector position, PVector velocity, int color) {
        this(position, velocity, new PVector(), color);
    }

    public Particle(PVector position, int color) {
        this(position, new PVector(), new PVector(), color);
    }

    public Particle(float x, float y, int color) {
        this(new PVector(x, y), new PVector(), new PVector(), color);
    }

    public Particle(float x, float y) {
        this(new PVector(x, y), new PVector(), new PVector(), Colors.BLACK);
    }

    public void update() {
        velocity.add(PVector.mult(acceleration, Time.deltaTime()));
        position.add(PVector.mult(velocity, Time.deltaTime()));
        acceleration.set(0, 0);
    }
}
