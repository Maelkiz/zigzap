package game;

import processing.core.PVector;

public class CircleCollider {
    public PVector center;
    public int radius;

    public CircleCollider(PVector center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public boolean intersectsLine(PVector a, PVector b) {
        double dx = b.x - a.x;
        double dy = b.y - a.y;
        double r2 = radius * radius;

        // If segment is a point, just check distance from that point to center.
        double segLen2 = dx * dx + dy * dy;
        if (segLen2 == 0.0) {
            double dist2 = (a.x - center.x)*(a.x - center.x) + (a.y - center.y)*(a.y - center.y);
            return dist2 <= r2;
        }

        // Project center C onto the line defined by the segment, param t
        double t = ((center.x - a.x) * dx + (center.y - a.y) * dy) / segLen2;

        // Clamp t to [0,1] to restrict to the segment
        if (t < 0.0) t = 0.0;
        else if (t > 1.0) t = 1.0;

        // Closest point on segment to center
        double closestX = a.x + t * dx;
        double closestY = a.y + t * dy;

        // Compare squared distance to avoid sqrt
        double dist2 = (closestX - center.x)*(closestX - center.x) + (closestY - center.y)*(closestY - center.y);
        return dist2 <= r2;
    }

    public boolean intersets(CircleCollider other) {
        double dist2 = (other.center.x - center.x)*(other.center.x - center.x) + (other.center.y - center.y)*(other.center.y - center.y);
        return dist2 <= (other.radius + radius) * (other.radius + radius);
    }
}  

