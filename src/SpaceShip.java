import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpaceShip {
    private BufferedImage spaceship;
    private final int WIDTH = 100;
    private final int HEIGHT = 100;

    public int getDEFAULT_X() {
        return DEFAULT_X;
    }

    private final int DEFAULT_X=350;
    private final int DEFAULT_Y=450;
    private int x;
    private int y;
    private Rectangle rectangle;
    public SpaceShip(){
        try {
            this.spaceship = ImageIO.read(new File("res/rocket.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.x = 0;
        this.y = 0;

        this.rectangle = new Rectangle(DEFAULT_X,DEFAULT_Y,WIDTH,HEIGHT);

    }

    public void moveRight() {
        if (x > 380) {
        } else {
            this.x += 5;
        }
    }
    public void moveLeft(){
        if (x<-380){
        }else {
            this.x -= 5;
        }
    }


    public Rectangle getRectangle(){
        return this.rectangle;
    }
    public void draw(Graphics2D graphics2D){
        graphics2D.drawImage(this.spaceship, DEFAULT_X+this.x,DEFAULT_Y+this.y, WIDTH, HEIGHT,null);
        this.rectangle.setBounds(DEFAULT_X+this.x,DEFAULT_Y+this.y, WIDTH, HEIGHT);
    }
    public int getX() {
        return x;
    }





}
