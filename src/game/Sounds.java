package game;

import processing.core.PApplet;
import processing.sound.SoundFile;

public class Sounds {
    public static SoundFile shoot;
    public static SoundFile enemyHurt;
    public static SoundFile enemyDeath;
    public static SoundFile soundtrack;

    public static void load(PApplet app) {
        shoot = new SoundFile(app, "../assets/sounds/shoot.wav");
        enemyHurt = new SoundFile(app, "../assets/sounds/enemy-hurt.wav");
        enemyDeath = new SoundFile(app, "../assets/sounds/enemy-death.wav");
        soundtrack = new SoundFile(app, "../assets/sounds/soundtrack.mp3");
    }
}
