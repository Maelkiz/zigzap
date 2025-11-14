package game;

import processing.core.PApplet;
import processing.core.PVector;

public class Camera {
    private final PApplet app;
    
    public final PVector position = new PVector();
    public final PVector origin = new PVector();

    // World bounds (optional). Set to null or huge bounds if you don't want clamping.
    private float worldMinX = Float.NEGATIVE_INFINITY;
    private float worldMaxX = Float.POSITIVE_INFINITY;
    private float worldMinY = Float.NEGATIVE_INFINITY;
    private float worldMaxY = Float.POSITIVE_INFINITY;

    // Camera settings
    private float deadZoneWidth = 0;
    private float deadZoneHeight = 0;
    private float smoothSpeed = 10;
    private float snapDistance = 800; 

    public Camera(PApplet app) {
        this.app = app;
    }

    public Camera(PApplet app, PVector start) {
        this.app = app;
        this.position.set(start);
    }

    public void update(PVector followTarget, PVector lookAhead) {
        float dt = Time.deltaTime();
        
        // predicted target (for look-ahead)
        PVector desired = new PVector(followTarget.x + lookAhead.x, followTarget.y + lookAhead.y);

        // center camera on screen center
        float halfW = app.width * 0.5f;
        float halfH = app.height * 0.5f;

        // compute delta from camera to desired target
        float targetCamX = desired.x;
        float targetCamY = desired.y;

        float dx = targetCamX - position.x;
        float dy = targetCamY - position.y;

        // optional snapping if very far
        if (snapDistance < Float.POSITIVE_INFINITY) {
            float dist = PApplet.dist(position.x, position.y, targetCamX, targetCamY);
            if (dist > snapDistance) {
                position.x = targetCamX;
                position.y = targetCamY;
                clampToBounds(halfW, halfH);
                return;
            }
        }

        // dead zone overshoot per axis
        float overshootX = 0;
        if (Math.abs(dx) > deadZoneWidth * 0.5f) {
            float signX = Math.signum(dx);
            overshootX = Math.abs(dx) - deadZoneWidth * 0.5f;
            // normalized overshoot (0..1) relative to half-screen (so extremes approach 1)
            float normX = PApplet.constrain(overshootX / (halfW - deadZoneWidth * 0.5f), 0, 1);
            // smoothing factor per frame using time-based smoothing: 1 - exp(-k*dt)
            float lerpFactorX = 1 - (float)Math.exp(-smoothSpeed * dt);
            // move proportionally to normalized overshoot * lerpFactor
            position.x = PApplet.lerp(position.x, targetCamX - signX * (deadZoneWidth * 0.5f), normX * lerpFactorX);
        }

        float overshootY = 0;
        if (Math.abs(dy) > deadZoneHeight * 0.5f) {
            float signY = Math.signum(dy);
            overshootY = Math.abs(dy) - deadZoneHeight * 0.5f;
            float normY = PApplet.constrain(overshootY / (halfH - deadZoneHeight * 0.5f), 0, 1);
            float lerpFactorY = 1 - (float)Math.exp(-smoothSpeed * dt);
            position.y = PApplet.lerp(position.y, targetCamY - signY * (deadZoneHeight * 0.5f), normY * lerpFactorY);
        }

        clampToBounds(halfW, halfH);

        origin.x = position.x - app.width / 2f;
        origin.y = position.y - app.height / 2f;
    }

    // Apply camera transform (call before drawing world). Use push/pop around UI as needed.
    public void applyTranslation() {
        app.translate(-origin.x, -origin.y);
    }

    // Set world bounds (call once if you want clamped camera)
    public void setWorldBounds(float minX, float minY, float maxX, float maxY) {
        this.worldMinX = minX;
        this.worldMinY = minY;
        this.worldMaxX = maxX;
        this.worldMaxY = maxY;
    }

    private void clampToBounds(float halfW, float halfH) {
        // ensure camera doesn't go beyond world bounds (if bounds are set)
        if (worldMinX != Float.NEGATIVE_INFINITY) {
            position.x = PApplet.constrain(position.x, worldMinX + halfW, worldMaxX - halfW);
            position.y = PApplet.constrain(position.y, worldMinY + halfH, worldMaxY - halfH);
        }
    }

    public void drawDeadBox() {
        app.pushStyle();
        app.noFill();
        app.stroke(255, 0, 0);
        app.strokeWeight(2);
        app.rectMode(PApplet.CENTER);
        app.rect(app.width / 2f, app.height / 2f, deadZoneWidth, deadZoneHeight);
        app.popStyle();
    }
}