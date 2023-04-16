import javax.swing.*;

public class GameFrame extends JFrame {

    GameFrame() {
        this.add(new GamePanel());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);             // hides this Window, its subcomponents, and all of its owned children
        this.setLocationRelativeTo(null);  // the window is placed in the center of the screen
    }
}
