package game;

import java.util.HashSet;
import java.util.Iterator;
import processing.core.*;

public final class Player extends Particle {
    private PApplet app;
    private int size;
    private int speed;
    private Trail trail;
    public CircleCollider collider;
    HashSet<Laser> lasers;
    Light light;

    public Player(PApplet app, float x, float y) {
        super(x, y, Colors.ORANGE);
        this.app = app;
        
        size = 20;
        speed = 1000;
        velocity.set(1, 1).normalize().mult(speed);
        position.set(x, y);
        trail = new Trail(app, 25);
        collider = new CircleCollider(position, size / 2);
        lasers = new HashSet<>(1000);
        light = new Light(app, position);
    }

    public void update() {
        trail.update(position);
        super.update();
        render();

        for (Laser laser : lasers) {
            laser.render();
            laser.update();
        }

        Iterator<Laser> iterator = lasers.iterator();
        while (iterator.hasNext()) {
            Laser laser = iterator.next();
            float distanceToPlayer = PApplet.dist(
                laser.position.x, laser.position.y, 
                position.x, position.y
            );
            if (distanceToPlayer > 2500) iterator.remove();
        }
    }  

    public void render() {
        trail.render();
        light.render();
        app.push();
        app.strokeWeight(2);
        app.stroke(color);
        app.circle(position.x, position.y, size);
        app.pop();
    }

    public void goLeft() {
        velocity.x = Math.abs(velocity.x) * -1;
    }

    public void goRight() {
        velocity.x = Math.abs(velocity.x);
    }

    public void goUp() {
        velocity.y = Math.abs(velocity.y) * -1;
    }

    public void goDown() {
        velocity.y = Math.abs(velocity.y);
    }

    public void shoot(PVector target) {
        PVector nextFrameMovement = PVector.mult(velocity, Time.deltaTime());
        PVector projectedPlayerPosition = PVector.add(position, nextFrameMovement);
        PVector laserDirection = PVector.sub(target, projectedPlayerPosition).normalize();
        lasers.add(new Laser(app, projectedPlayerPosition, laserDirection));
        Sounds.shoot.play();

        /*
        PVector a = player.position.copy();
        PVector b = a.copy().add(laserDirection.copy().mult(2500));
        if (asteroid.collider.intersectsLine(a, b)) {
            enemyHurt.play();
            asteroid.takeDamage(1);
        }
        */
    }
}
