import javax.swing.*;
import java.awt.*;


public class MainLogin extends JPanel {
    private final int SIZE_FONT = 13;
    private final PhotoLogin photoLogin;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private boolean start = false;
    private Font font = new Font("Arial", Font.BOLD, SIZE_FONT);
    public MainLogin() {
        this.setSize(WIDTH, HEIGHT);
        this.setBackground(Color.black);
        this.photoLogin = new PhotoLogin();
        this.requestFocus(true);
        this.setFocusable(true);
        this.addKeyListener(new Keyboard(this));
    }
    public boolean isStart() {
        return start;
    }
    public void start() {
        this.start = true;
    }
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        this.photoLogin.draw(graphics2D);
        graphics2D.setColor(Color.white);
        graphics2D.setFont(font);
        graphics2D.drawString("Welcome to the game! Use the left and right arrow keys to move your spaceship. Press the spacebar to shoot.", 50, 400);
        graphics2D.drawString("Good luck!", 350, 430);
        graphics2D.drawString("Press 1 for Easy or 2 for Hard ", 290, 470);
        repaint();
    }
}


