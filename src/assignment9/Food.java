package assignment9;

import edu.princeton.cs.introcs.StdDraw;

public class Food {
    public static final double FOOD_SIZE = 0.02;
    private double x, y;

    public Food() {
        // Randomly set the position of the food within bounds
        x = Math.random(); // Random x between 0 and 1
        y = Math.random(); // Random y between 0 and 1
    }

    // Getter methods
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // Setter methods for position
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    // Draw method
    public void draw() {
        // Set the color for the food to red
        StdDraw.setPenColor(StdDraw.RED);
        // Draw the food at the specified coordinates
        StdDraw.filledCircle(x, y, FOOD_SIZE);
    }
}
