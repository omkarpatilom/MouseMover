import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class MouseMover {
    private static Timer timer;
    private static Robot robot;
    private static Random random = new Random();

    public static void main(String[] args) {
        try {
            // Initialize the Robot instance
            robot = new Robot();

            // Create and start the timer
            startTimer();

            System.out.println("Press Ctrl+C to exit...");
            while (true) {
                // Keep the program running
                Thread.sleep(1000);
            }

        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void startTimer() {
        timer = new Timer();
        int interval = getRandomInterval(); // Generate a random interval between 1 and 60 seconds
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                moveMouseRandomly();
                int newInterval = getRandomInterval(); // Generate a new random interval
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        moveMouseRandomly();
                    }
                }, newInterval);
            }
        }, 0, interval);
        System.out.println("Timer started with an interval of " + interval / 1000 + " seconds.");
    }

    private static int getRandomInterval() {
        return random.nextInt(60) * 1000 + 1000; // Random interval between 1 and 60 seconds in milliseconds
    }

    private static void moveMouseRandomly() {
        // Get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Generate random X and Y coordinates within the screen boundaries
        int randomX = random.nextInt((int) screenSize.getWidth());
        int randomY = random.nextInt((int) screenSize.getHeight());

        // Move the mouse to the random coordinates
        robot.mouseMove(randomX, randomY);
        System.out.println("Moved mouse to (" + randomX + ", " + randomY + ").");
    }
}
