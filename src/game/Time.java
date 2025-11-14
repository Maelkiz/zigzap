package game;

public final class Time {
    private static long timeStamp;
    private static float deltaTime;

    static {
        timeStamp = System.nanoTime();
        deltaTime = 0;
    }

    public static void tick() {
        long newTimeStamp = System.nanoTime();
        deltaTime = (newTimeStamp - timeStamp) / 1_000_000_000f; 
        timeStamp = newTimeStamp;
    }

    public static float deltaTime() {
        return deltaTime;
    }
}
