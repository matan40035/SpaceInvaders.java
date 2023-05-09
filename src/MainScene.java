import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class MainScene extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int SIZE_FONT = 20;
    private static final int SIZE_GAME_OVER = 100;
    private static int gameTime = 90;
    private static final int SIZE_TIMER = 20;


    private RecordScore recordScore;
    File file = new File("res/TopScore.txt");
    java.util.List<String> topScore;
    private SpaceShip spaceShip;
    private Shot shot;
    private ArrayList<Alien> aliens;
    private Rectangle shipRect;
    private Rectangle shotRect;
    private Rectangle alienRect;
    private boolean ready = false;
    private boolean stopShot = true;
    public int point = 0;
    private Font font = new Font("Arial", Font.BOLD, SIZE_FONT);
    private Font fontGameOver = new Font("Arial", Font.BOLD, SIZE_GAME_OVER);
    private JLabel timerLabel;
    private int timeLeft = gameTime;
    private boolean stopGame = true;
    String topRecordScore = "0";
    public MainScene() {
        this.setSize(WIDTH, HEIGHT);
        this.setBackground(Color.black);
        this.spaceShip = new SpaceShip();
        this.aliens = new ArrayList<>();
        createAliens();
        shipRect = this.spaceShip.getRectangle();
        int centerX = (int) (shipRect.getX() + shipRect.getWidth() / 2);
        int centerY = (int) shipRect.getY();
        this.shot = new Shot(centerX, centerY);
        shotRect = this.shot.getRectangle();
        alienRect = new Rectangle();
        this.requestFocus(true);
        this.setFocusable(true);
        this.addKeyListener(new Keyboard(this));
        new Thread(() -> {
            while (true) {
                checkCollision();
                updateTopScore();
            }
        }).start();
        this.timerLabel = new JLabel("Time: " + gameTime, SwingConstants.RIGHT);
        this.timerLabel.setFont(new Font("Arial", Font.BOLD, SIZE_TIMER));
        this.timerLabel.setForeground(Color.WHITE);
        this.add(timerLabel, BorderLayout.EAST);
        int delay = 1000;
        Timer timer = new Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                timeLeft--;
                timerLabel.setText("Time: " + timeLeft);
                if (timeLeft == 0) {
                    ((Timer) evt.getSource()).stop();
                    timerLabel.setText("Time is over");
                    stopGame();
                }
                if (aliens.size() == 0) {
                    ((Timer) evt.getSource()).stop();
                }
            }
        });
        timer.start();
        this.recordScore = new RecordScore();
    }
    public void moveShipRight() {
        if (stopGame) {
            this.spaceShip.moveRight();
            repaint();
        }
    }
    public void moveShipLeft() {
        if (stopGame) {
            this.spaceShip.moveLeft();
            repaint();
        }
    }
    public void fire() {
        new Thread(() -> {
            while (stopShot && stopGame) {
                this.shot.fireShot();
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
    public void createAliens() {
        for (int x = 10; x < Window.WIDTH; x += 50) {
            for (int y = 0; y < Window.HEIGHT / 2; y += 50) {
                this.aliens.add(new Alien(x, y));
            }
        }
    }
    public  void checkCollision() {
        for (Alien alien1 : aliens) {
            this.alienRect = alien1.getRectangle();
            if (this.shotRect.intersects(this.alienRect)) {
                point += 10;
                alien1.destroy();
                aliens.remove(alien1);
                shot.destroy();
                stopShot();
                repaint();
                break;
            }
        }
    }
    public boolean Ready() {
        ready = true;
        return true;
    }
    public void stopShot() {
        stopShot = false;
        repaint();
    }
    public void startShot() {
        stopShot = true;
        repaint();
    }
    public void stopGame() {
        stopGame = false;
    }
    public void createFileScore() {
        RecordScore.createFile("res/TopScore.txt");
    }
    public void updateTopScore() {
        try {
            topScore = RecordScore.readFromFile(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (Integer.parseInt((topScore.get(0))) < point) {
            String stringPoint = String.valueOf(point);
            RecordScore.writeToFile(file, stringPoint);
            topRecordScore = stringPoint;
        }
    }

    public static void difficultyLevel(int levelDiff) {
        if (levelDiff == 2) {
            gameTime = 60;
        }
    }
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        this.spaceShip.draw(graphics2D);
        for (Alien alien : aliens) {
            alien.draw(graphics2D);
        }
        graphics2D.setColor(Color.white);
        graphics2D.setFont(font);
        graphics2D.drawString("Point: " + point, 10, 550);

                if (ready) {
                    this.shot.draw(graphics2D, spaceShip.getX(), WIDTH, this);
            }
        if (timeLeft == 0) {
            stopGame();
            graphics2D.setColor(Color.white);
            graphics2D.setFont(fontGameOver);
            graphics2D.drawString("Game Over", 150, 400);
            repaint();
        }
        if (aliens.size() == 0) {
            stopGame();
            graphics2D.setColor(Color.white);
            graphics2D.setFont(fontGameOver);
            graphics2D.drawString("You Win", 150, 400);
            repaint();
        }
        graphics2D.setColor(Color.white);
        graphics2D.setFont(font);
        graphics2D.drawString("Top score: " + topScore.get(0), 630, 550);
    }
}

