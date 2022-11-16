import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH = 600;
    private final int B_HEIGHT = 581;
    private final int DOT_SIZE = 20;
    private final int DELAY = 40;
    private int x, y;
    private int ymn = 180;
    private int monster_x[] = new int[5];
    private int monster_y[] = new int[5];
    private int lvl = 1;

    private boolean inGame = true;
    private boolean menu = true;
    private boolean start = false;
    private boolean level = false;
    private boolean bando1 = true;
    private boolean bando2 = false;
    private boolean bando3 = false;

    private Timer timer;
    private Image map;
    private Image mouse;
    private Image map1;
    private Image map2;
    private Image map3;
    private Image main;
    private Image monster;
    private Image monster1;

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }

    private void loadImages() {
        // menu
        ImageIcon map0 = new ImageIcon("zetcode/map1.png");
        map = map0.getImage();

        ImageIcon mouse1 = new ImageIcon("zetcode/main2.jpg");
        mouse = mouse1.getImage();
        // start
        ImageIcon map4 = new ImageIcon("zetcode/map1.png");
        map1 = map4.getImage();

        ImageIcon map5 = new ImageIcon("zetcode/map2.jpg");
        map2 = map5.getImage();

        ImageIcon map6 = new ImageIcon("zetcode/map2.jpg");
        map3 = map6.getImage();

        ImageIcon main1 = new ImageIcon("zetcode/main2.jpg");
        main = main1.getImage();

        ImageIcon qv = new ImageIcon("zetcode/main2.jpg");
        monster = qv.getImage();

        ImageIcon qv1 = new ImageIcon("zetcode/main2.jpg");
        monster1 = qv1.getImage();

    }

    private void initGame() {
        if (inGame) {
            locateMonster();
            timer = new Timer(DELAY / lvl, this);
            timer.start();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        if (inGame) {
            if (level) {
                g.drawImage(map, 0, 0, this);
                g.drawImage(mouse, 220, ymn, this);
                level(g);
            }
            if (menu) {
                g.drawImage(map, 0, 0, this);
                g.drawImage(mouse, 220, ymn, this);
                menu(g);
            }
            if (start) {
                // bando
                if (bando1) {
                    g.drawImage(map1, 0, 0, this);
                }
                if (bando2) {
                    g.drawImage(map2, 0, 0, this);
                }
                if (bando3) {
                    g.drawImage(map3, 0, 0, this);
                }
                // quaivat
                for (int z = 0; z < 5; z++) {
                    if ((z % 2) == 0) {
                        g.drawImage(monster, monster_x[z], monster_y[z], this);
                    } else {
                        g.drawImage(monster1, monster_x[z], monster_y[z], this);
                    }
                }
                // nhanvat
                g.drawImage(main, x, y, this);
            }
            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }
    }

    private void menu(Graphics g) {
        String st = "Start";
        String lv = "Level";
        String ex = "Exit";
        Font small = new Font("Helvetica", Font.BOLD, 20);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(st, 250, 200);
        g.drawString(lv, 250, 250);
        g.drawString(ex, 250, 300);
    }

    private void level(Graphics g) {
        // de
        String ez = "Dễ";
        Font big = new Font("Helvetica", Font.BOLD, 20);
        if (lvl == 1) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.white);
        }
        g.setFont(big);
        g.drawString(ez, 250, 210);
        // kho
        String hard = "Khó";
        Font small = new Font("Helvetica", Font.BOLD, 20);
        if (lvl == 2) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.white);
        }
        g.setFont(small);
        g.drawString(hard, 250, 260);
        // dia nguc
        String evil = "Địa ngục";
        Font evil1 = new Font("Helvetica", Font.BOLD, 20);
        if (lvl == 10) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.white);
        }
        g.setFont(evil1);
        g.drawString(evil, 250, 310);
    }

    private void gameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    private void checkMonster() {

    }

    private void moveMonster() {

    }

    private void checkCollision() {

        if (y > B_HEIGHT) {
            y = 0;
        }

        if (y < 0) {
            y = B_HEIGHT;
        }

        if (x > B_WIDTH) {
            x = 0;
        }

        if (x < 0) {
            x = B_WIDTH;
        }

        if (!inGame) {
            timer.stop();
        }
    }

    private void locateMonster() {
        if (inGame) {
            for (int z = 0; z < 5; z++) {
                int r = (int) (Math.random() * 20);
                monster_x[z] = ((r * DOT_SIZE));

                r = (int) (Math.random() * 20);
                monster_y[z] = ((r * DOT_SIZE));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkMonster();
            checkCollision();
            moveMonster();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            if (level) {
                // di chuyen chuot
                if (key == KeyEvent.VK_UP && ymn > 200) {
                    ymn -= 50;
                }
                if (key == KeyEvent.VK_DOWN && ymn < 250) {
                    ymn += 50;
                }
                // cac nut
                if (key == KeyEvent.VK_LEFT) {
                    menu = true;
                    level = false;
                    ymn = 180;
                }
                if (key == KeyEvent.VK_ENTER && ymn == 190) {
                    menu = true;
                    level = false;
                    ymn = 180;
                    lvl = 1;
                }
                if (key == KeyEvent.VK_ENTER && ymn == 240) {
                    menu = true;
                    level = false;
                    ymn = 180;
                    lvl = 2;
                }
                if (key == KeyEvent.VK_ENTER && ymn == 290) {
                    menu = true;
                    level = false;
                    ymn = 180;
                    lvl = 10;
                }
            }
            if (menu) {
                // di chuyen chuot
                if (key == KeyEvent.VK_UP && ymn > 200) {
                    ymn -= 50;
                }
                if (key == KeyEvent.VK_DOWN && ymn < 250) {
                    ymn += 50;
                }
                // cac nut
                if (key == KeyEvent.VK_RIGHT && ymn == 180) {
                    menu = false;
                    start = true;
                    x = -DOT_SIZE;
                    y = 0;
                }
                if (key == KeyEvent.VK_RIGHT && ymn == 230) {
                    menu = false;
                    level = true;
                    ymn = 190;
                }
                if (key == KeyEvent.VK_RIGHT && ymn == 280) {
                    System.exit(0);
                }
            }
            if (start && menu == false) {
                // thoat game
                if (key == KeyEvent.VK_E) {
                    System.exit(0);
                }
                // ra ngoai menu
                if (key == KeyEvent.VK_ESCAPE) {
                    menu = true;
                    start = false;
                }
                // doimap
                if (key == KeyEvent.VK_LEFT && x == 0 && y == 0 && bando1) {
                    bando1 = false;
                    bando2 = true;
                    x = B_WIDTH;
                    locateMonster();
                }

                if (key == KeyEvent.VK_RIGHT && x == B_WIDTH - 20 && y == 0 && bando2) {
                    bando1 = true;
                    bando2 = false;
                    x = -DOT_SIZE;
                    locateMonster();
                }

                if (key == KeyEvent.VK_RIGHT && x == B_WIDTH - 20 && y == 0 && bando1) {
                    bando1 = false;
                    bando3 = true;
                    x = -DOT_SIZE;
                    locateMonster();
                }

                if (key == KeyEvent.VK_LEFT && x == 0 && y == 0 && bando3) {
                    bando1 = true;
                    bando3 = false;
                    x = B_WIDTH;
                    locateMonster();
                }
                // dichuyen
                if (key == KeyEvent.VK_LEFT) {
                    x -= DOT_SIZE;
                }

                if (key == KeyEvent.VK_RIGHT) {
                    x += DOT_SIZE;
                }

                if (key == KeyEvent.VK_UP) {
                    y -= DOT_SIZE;
                }

                if (key == KeyEvent.VK_DOWN) {
                    y += DOT_SIZE;
                }

            }
        }
    }
}