package game;

import java.util.HashSet;
import java.util.Set;

public class Input {
    private static final Set<Integer> pressedKeys = new HashSet<>();

    public static boolean keyIsDown(int keyCode) {
        return pressedKeys.contains(keyCode);
    }

    public static boolean keyIsDown(Key key) {
        return pressedKeys.contains(key.getKeyCode());
    }

    public static void setKeyIsDown(int keyCode, boolean value) {
        if (value) pressedKeys.add(keyCode);
        else pressedKeys.remove(keyCode); 
    }
    
    private Input() { }
}

