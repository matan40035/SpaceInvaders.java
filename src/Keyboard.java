import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    private MainScene mainScene;
    private MainLogin mainLogin;
    private final int LEVEL_DIFF1 = 1;
    private final int LEVEL_DIFF2 = 2;


    public Keyboard(MainScene mainScene) {
        this.mainScene = mainScene;
    }

    public Keyboard(MainLogin mainLogin) {
        this.mainLogin = mainLogin;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {

            this.mainScene.moveShipLeft();
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.mainScene.moveShipRight();

        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.mainScene.fire();
            this.mainScene.Ready();
            this.mainScene.startShot();
        }
        if (e.getKeyCode() == KeyEvent.VK_1) {
            MainScene.difficultyLevel(LEVEL_DIFF1);
        }
        if (e.getKeyCode() == KeyEvent.VK_2) {
            MainScene.difficultyLevel(LEVEL_DIFF2);
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.mainLogin.start();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.mainScene.stopShot();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
