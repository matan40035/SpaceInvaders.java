import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PhotoLogin {
    private BufferedImage photoLogin;
    private final int X = 0;
    private final int Y = 0;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    public PhotoLogin() {
        try {
            this.photoLogin = ImageIO.read(new File("res/mainlogin.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(this.photoLogin, this.X, this.Y, this.WIDTH, this.HEIGHT, null);
    }
}
