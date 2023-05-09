import javax.swing.*;

public class Window extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private MainScene mainScene;
    private MainLogin mainLogin;
    public Window() {
        this.setTitle("Space invaders");
        this.setLayout(null);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainLogin = new MainLogin();
        this.mainLogin.setVisible(true);
        this.add(this.mainLogin);
        new Thread(() -> {
            while (!mainLogin.isStart()) {
                repaint();
                if (mainLogin.isStart()) {
                    this.mainScene = new MainScene();
                    this.mainScene.setVisible(true);
                    this.add(this.mainScene);
                    this.mainLogin.setVisible(false);
                }
            }
        }).start();
        this.setVisible(true);
    }
}
