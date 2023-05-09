import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Shot {
    private BufferedImage shot;
    private final int WIDTH = 10;
    private final int HEIGHT = 20;
    private final int Y_SHIP = 450;
    private int x;
    private int y;
    private Rectangle rectangle;

    public Shot(int x, int y) {
        try {
            this.shot = ImageIO.read(new File("res/shot.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.x = x;
        this.y = y;
        this.rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
    }


    public void fireShot() {
        this.y -= 5;
    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }

    public void updateShot() {
        this.y = 450;

    }

    public void draw(Graphics2D graphics2D, int shipX, int width, MainScene mainScene) {
        graphics2D.drawImage(this.shot, shipX + width / 2, this.y, WIDTH, HEIGHT, null);
        this.rectangle.setBounds(shipX + width / 2, this.y, WIDTH, HEIGHT);
        if (this.y < 0) {
            this.y = Y_SHIP;
            mainScene.stopShot();
        }
    }


}
