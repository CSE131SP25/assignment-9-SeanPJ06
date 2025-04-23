package assignment9;

import edu.princeton.cs.introcs.StdDraw;

public class Game {
    private Snake snake;
    private Food food;
    private int score;  // Score counter

    public Game() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);

        snake = new Snake();  // Initialize snake
        food = new Food();    // Initialize food
        score = 0;  // Initialize score to 0
    }

    public void play() {
        while (true) {
            int dir = getKeypress();  // Get user input for direction
            snake.changeDirection(dir);  // Change snake's direction

            snake.move();  // Move the snake

            // Check if snake eats the food
            if (snake.eatFood(food)) {
                food = new Food();  // Generate new food
                score++;  // Increment score
            }

            updateDrawing();  // Update the drawing (snake and food)

            // Check for collisions or if the snake is out of bounds
            if (!snake.isInBounds()) {
                gameOver();  // Game over if the snake is out of bounds
                break;
            }

            StdDraw.pause(50);  // Pause to control game speed
        }
    }

    private int getKeypress() {
        if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_W)) {
            return 1;  // Up
        } else if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_S)) {
            return 2;  // Down
        } else if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_A)) {
            return 3;  // Left
        } else if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_D)) {
            return 4;  // Right
        } else {
            return -1;  // No key press
        }
    }

    private void updateDrawing() {
        StdDraw.clear();  // Clear the screen for the next frame
        snake.draw();     // Draw the snake
        food.draw();      // Draw the food

        // Display the score
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(0.9, 0.95, "Score: " + score);

        StdDraw.show();   // Show the updated frame
    }

    private void gameOver() {
        StdDraw.clear();  // Clear the screen for the game over screen
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(0.5, 0.5, "Game Over!");
        StdDraw.show();   // Display the game over message
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.play();  // Start the game loop
    }
}
