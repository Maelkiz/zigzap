package game;

import processing.core.PApplet;
import processing.core.PVector;

public class Game {
    private PApplet app;
    private Camera camera;
    private Player player;
    private Asteroid asteroid;
    private Background background;

    public Game(PApplet app) {
        Sounds.load(app);
        this.app = app;
        camera = new Camera(app);
        player = new Player(app, 0, 0);
        asteroid = new Asteroid(app, app.width * 0.66f, app.height * 0.66f);
        background = new Background(app);
    }

    public void update() {
        app.background(0);
        //background.renderWithParallax(player.position);
        camera.update(player.position, new PVector());
        camera.applyTranslation();
        player.update();
        asteroid.update();

        if (player.collider.intersets(asteroid.collider)) {
            //ding.play();
        }

        if (asteroid.hp <= 0) {
            asteroid.hp = 3;
            asteroid.position.set(app.random(app.width), app.random(app.height));
            Sounds.enemyDeath.play();
        }

        enforceBounds(-app.width, 2 * app.width, -app.height, 2 * app.height);
    }

    public void enforceBounds(float minX, float maxX, float minY, float maxY) {
        // enforce left/right bounds
        if (player.position.x < minX) {
            player.position.x = minX;
        } else if (player.position.x > maxX) {
            player.position.x = maxX;
        }
        // enforce top/bottom bounds
        if (player.position.y < minY) {
            player.position.y = minY;
        } else if (player.position.y > maxY) {
            player.position.y = maxY;
        }
        app.push();
        app.noFill();
        app.stroke(Colors.WHITE);    
        app.strokeWeight(2);
        app.rect(minX, minY, 3 * app.width, 3 * app.height);
        app.pop();
    }

    public void handleKeyPress(int key) {
        switch (key) {
            case 'w', 'W' -> player.goUp();
            case 'a', 'A' -> player.goLeft();
            case 'd', 'D' -> player.goRight();
            case 'm', 'M' -> toggleMusic();
        } 
    }

    public void handleKeyRelease(int key) {
        switch (key) {
            case 'w', 'W' -> player.goDown();
            case 'a', 'A' -> {
                if (Input.keyIsDown(Key.D)) player.goRight();
            }
            case 'd', 'D' -> {
                if (Input.keyIsDown(Key.A)) player.goLeft();
            }
        } 
    }

    public void handleMouseClick(int mouseButton) {
        if (mouseButton == PApplet.LEFT) player.shoot(mouseInWorldSpace());
    }

    private void toggleMusic() {
        if (Sounds.soundtrack.isPlaying()) Sounds.soundtrack.stop();
        else Sounds.soundtrack.loop();
    }

    public PVector mouseInWorldSpace() {
        return new PVector(
            camera.origin.x + app.mouseX, 
            camera.origin.y + app.mouseY
        );
    }
}
