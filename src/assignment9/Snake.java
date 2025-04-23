package assignment9;
import java.util.LinkedList;
import edu.princeton.cs.introcs.StdDraw;

public class Snake {
    private static final double SEGMENT_SIZE = 0.03;
    private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
    private LinkedList<BodySegment> segments;
    private double deltaX;
    private double deltaY;

    public Snake() {
        segments = new LinkedList<>();
        segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE));  // Start at the center
        deltaX = 0;
        deltaY = 0;
    }

    public void changeDirection(int direction) {
        if (direction == 1) {  // Up
            deltaY = MOVEMENT_SIZE;
            deltaX = 0;
        } else if (direction == 2) {  // Down
            deltaY = -MOVEMENT_SIZE;
            deltaX = 0;
        } else if (direction == 3) {  // Left
            deltaY = 0;
            deltaX = -MOVEMENT_SIZE;
        } else if (direction == 4) {  // Right
            deltaY = 0;
            deltaX = MOVEMENT_SIZE;
        }
    }

    public void move() {
        // Move the segments starting from the tail
        for (int i = segments.size() - 1; i > 0; i--) {
            BodySegment current = segments.get(i);
            BodySegment previous = segments.get(i - 1);
            current.setPosition(previous.getX(), previous.getY());
        }

        // Move the head
        BodySegment head = segments.getFirst();
        head.setPosition(head.getX() + deltaX, head.getY() + deltaY);
    }

    public void draw() {
        StdDraw.setPenColor(StdDraw.GREEN);
        for (BodySegment segment : segments) {
            segment.draw();
        }
    }

    public boolean eatFood(Food f) {
        BodySegment head = segments.getFirst();
        double distance = Math.sqrt(Math.pow(f.getX() - head.getX(), 2) + Math.pow(f.getY() - head.getY(), 2));
        if (distance < SEGMENT_SIZE) {
            segments.addLast(new BodySegment(segments.getLast().getX(), segments.getLast().getY(), SEGMENT_SIZE));  // Add a new segment
            return true;  // Food eaten
        }
        return false;  // No food eaten
    }

    public boolean isInBounds() {
        BodySegment head = segments.getFirst();
        double x = head.getX();
        double y = head.getY();
        return x >= 0 && x <= 1 && y >= 0 && y <= 1;  // Check if the snake is within bounds
    }
}
