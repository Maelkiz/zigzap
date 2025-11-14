import game.*;
import processing.core.PApplet;

public class App extends PApplet {
    Game game;

    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1");
        PApplet.main("App");
    }

    @Override
    public void settings() {
        fullScreen(P2D);
    }

    @Override 
    public void setup() {
        frameRate(60);
        noStroke();
        game = new Game(this);
        cursor(1);
    }

    @Override
    public void draw() {
        Time.tick();
        game.update();
    }

    @Override
    public void keyPressed() {
        Input.setKeyIsDown(keyCode, true);
        game.handleKeyPress(key);
    }

    public void keyReleased() {
        Input.setKeyIsDown(keyCode, false);
        game.handleKeyRelease(key);
    }
    
    @Override
    public void mouseClicked() {
        game.handleMouseClick(mouseButton);
    }
}