import java.awt.EventQueue;
import javax.swing.JFrame;

public class Nanatsunotaizai extends JFrame {

    public Nanatsunotaizai() {

        initUI();
    }

    private void initUI() {

        add(new Board());

        setResizable(false);
        pack();

        setTitle("Nanatsu no taizai :D");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame ex = new Nanatsunotaizai();
            ex.setVisible(true);
        });
    }
}
