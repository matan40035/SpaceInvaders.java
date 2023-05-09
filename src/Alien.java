import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Alien {
    private BufferedImage alien;
    private int width = 20;
    private int height = 20;
    private int x;
    private int y;
    private Rectangle rectangle;

    public Alien(int x, int y) {
        try {
            this.alien = ImageIO.read(new File("res/invader1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.x = x;
        this.y = y;

        this.rectangle = new Rectangle(x, y, width, height);

    }

    public void destroy() {
        this.x = 1000;
        this.y = 1000;
        this.width = 0;
        this.height = 0;
    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(this.alien, this.x, this.y, width, height, null);
    }
}
