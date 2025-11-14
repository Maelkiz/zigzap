package game;

import processing.core.PApplet;
import processing.core.PVector;

public class Trail {
    private PApplet app;
    private Particle[] particles;

    public Trail(PApplet app, int length) {
        this.app = app;
        particles = new Particle[length];
        for (int i = 0; i < particles.length; i++) {
            int color = app.lerpColor(
                app.color(255, 0, 0), 
                app.color(255, 255, 0), 
                PApplet.map(i, 0, 80, 0, 1)
            );
            particles[i] = new Particle(0, 0, color);
        }
    }

    public void update(PVector position) {
        float minGap = 20; 
        PVector lastStored = particles[particles.length - 1].position;

        if (PVector.dist(position, lastStored) > minGap) {
            for (int i = 0; i < particles.length - 1; i++) {
                PVector newPosition = particles[i+1].position.copy();
                particles[i].position.set(newPosition.x, newPosition.y);
            }
            particles[particles.length - 1].position.set(position.x, position.y);
        }
    }

    public void render() {
        for (int i = 0; i < particles.length; i++) {
            particles[i].update();
            app.push();
            app.noStroke();
            app.fill(particles[i].color);
            app.circle(particles[i].position.x, particles[i].position.y, i);
            app.pop();
        }
    }
}
