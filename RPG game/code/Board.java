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
    // khung game
    private final int B_WIDTH = 600;
    private final int B_HEIGHT = 581;
    // tốc độ nhân vật
    private final int DOT_SIZE = 20;
    // tốc độ quái
    private final int DELAY = 30;
    // vị trí nv
    private int x, y;
    // vị trí mũi tên menu
    private int ymn = 180;
    // vị trí quái
    private int monster_x[] = new int[5];
    private int monster_y[] = new int[5];
    // level bắt đầu
    private int lvl = 1;
    // đếm tgian dùng skill
    private int at1 = 0;
    private int at2 = 0;
    // biến mở đầu || kết thúc game
    private boolean inGame = true;
    // menu
    private boolean menu = true;
    private boolean start = false;
    private boolean level = false;
    private boolean character = false;
    // bản đồ bắt đầu
    private boolean bando1 = true;
    private boolean bando2 = false;
    private boolean bando3 = false;
    // tấn công
    private boolean attack1 = false;
    private boolean attack2 = false;
    // nhân vật
    private boolean meliodas = true;
    private boolean nv2 = false;
    private boolean nv3 = false;
    // biến tgian
    private Timer timer;
    // ảnh menu
    private Image map;
    private Image mouse;
    // ảnh map
    private Image map1;
    private Image map2;
    private Image map3;
    // ảnh nv đứng im
    private Image main;
    // ảnh nv chết
    private Image main0;
    // ảnh nv chạy
    private Image mainr;
    private Image mainr1;
    private Image mainr2;
    private Image mainr3;
    private Image mainr4;
    private Image mainr5;
    private Image mainr6;
    // ảnh skill 1
    private Image main11;
    private Image main12;
    private Image main13;
    // ảnh skill 2
    private Image main21;
    private Image main22;
    private Image main23;
    // ảnh quái vật 1
    private Image monster;
    private Image monstera;
    private Image monsterb;
    private Image monsterc;
    // ảnh quái vật 2
    private Image monster1;
    private Image monster1a;
    private Image monster1b;
    private Image monster1c;

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        loadcharacter();
        initGame();
    }

    private void loadImages() {

        // menu
        ImageIcon map0 = new ImageIcon("menu/map.jpg");
        map = map0.getImage();

        ImageIcon mouse1 = new ImageIcon("menu/mouse.png");
        mouse = mouse1.getImage();

        // load map
        ImageIcon map4 = new ImageIcon("map/map1.png");
        map1 = map4.getImage();

        ImageIcon map5 = new ImageIcon("map/map2.jpg");
        map2 = map5.getImage();

        ImageIcon map6 = new ImageIcon("map/map2.jpg");
        map3 = map6.getImage();

        // quái vật
        ImageIcon qv = new ImageIcon("monster/");
        monster = qv.getImage();

        ImageIcon qv1 = new ImageIcon("monster/");
        monster1 = qv1.getImage();

    }

    private void loadcharacter() {
        if (meliodas) {
            // load nhân vật
            ImageIcon main1 = new ImageIcon("attack/attack1.png");
            main = main1.getImage();

            ImageIcon main0a = new ImageIcon("character/character3.png");
            main0 = main0a.getImage();

            // load tấn công
            ImageIcon at1a = new ImageIcon("attack/attack1.png");
            main11 = at1a.getImage();

            ImageIcon at1b = new ImageIcon("attack/attack1a.png");
            main12 = at1b.getImage();

            ImageIcon at1c = new ImageIcon("attack/attack1b.png");
            main13 = at1c.getImage();

            ImageIcon at2a = new ImageIcon("attack/attack2.png");
            main21 = at2a.getImage();

            ImageIcon at2b = new ImageIcon("attack/attack2a.png");
            main22 = at2b.getImage();

            ImageIcon at2c = new ImageIcon("attack/attack2b.png");
            main23 = at2c.getImage();

            // chạy
            ImageIcon mainra = new ImageIcon("run/run.png");
            mainr = mainra.getImage();

            ImageIcon mainrb = new ImageIcon("run/run1a.png");
            mainr1 = mainrb.getImage();

            ImageIcon mainrc = new ImageIcon("run/run1b.png");
            mainr2 = mainrc.getImage();

            ImageIcon mainrd = new ImageIcon("run/run1c.png");
            mainr3 = mainrd.getImage();

            ImageIcon mainre = new ImageIcon("run/run1d.png");
            mainr4 = mainre.getImage();

            ImageIcon mainrf = new ImageIcon("run/run1e.png");
            mainr5 = mainrf.getImage();

            ImageIcon mainrg = new ImageIcon("run/run1f.png");
            mainr6 = mainrg.getImage();
        }
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
            if (character) {
                g.drawImage(map, 0, 0, this);
                g.drawImage(mouse, 220, ymn, this);
                Character(g);
            }
            if (menu) {
                g.drawImage(map, 0, 0, this);
                g.drawImage(mouse, 220, ymn, this);
                menu(g);
            }
            if (start) {
                // bản đồ
                if (bando1) {
                    g.drawImage(map1, 0, 0, this);
                }
                if (bando2) {
                    g.drawImage(map2, 0, 0, this);
                }
                if (bando3) {
                    g.drawImage(map3, 0, 0, this);
                }
                // quái vật
                for (int z = 0; z < 5; z++) {
                    if ((z % 2) == 0) {
                        g.drawImage(monster, monster_x[z], monster_y[z], this);
                    } else {
                        g.drawImage(monster1, monster_x[z], monster_y[z], this);
                    }
                }
                // nhân vật
                if (meliodas) {
                    if (attack1) {
                        at1 += 1;
                        // if (at1 > 0 && at1 < 11) {
                        // g.drawImage(main11, x, y, this);
                        // }
                        if (at1 > 0 && at1 < 16) {
                            g.drawImage(main12, x - 10, y, this);
                        }
                        if (at1 > 15 && at1 < 31) {
                            if (at1 == 30) {
                                at1 = 0;
                                attack1 = false;
                            }
                            g.drawImage(main13, x - 12, y - 20, this);
                        }
                    } else if (attack2) {
                        at2 += 1;
                        if (at2 > 0 && at2 < 11) {
                            g.drawImage(main21, x - 15, y - 30, this);
                        }
                        // if (at2 > 10 && at2 < 21) {
                        // g.drawImage(main22, x - 5, y - 10, this);
                        // }
                        if (at2 > 10 && at2 < 21) {
                            if (at2 == 20) {
                                at2 = 0;
                                attack2 = false;
                            }
                            g.drawImage(main23, x - 10, y - 30, this);
                        }
                    } else {
                        g.drawImage(main, x, y, this);
                    }
                }
                if (nv2) {
                    if (attack1) {
                        at1 += 1;
                        // if (at1 > 0 && at1 < 11) {
                        // g.drawImage(main11, x, y, this);
                        // }
                        if (at1 > 0 && at1 < 16) {
                            g.drawImage(main12, x - 10, y, this);
                        }
                        if (at1 > 15 && at1 < 31) {
                            if (at1 == 30) {
                                at1 = 0;
                                attack1 = false;
                            }
                            g.drawImage(main13, x - 12, y - 20, this);
                        }
                    } else if (attack2) {
                        at2 += 1;
                        if (at2 > 0 && at2 < 11) {
                            g.drawImage(main21, x - 15, y - 30, this);
                        }
                        // if (at2 > 10 && at2 < 21) {
                        // g.drawImage(main22, x - 5, y - 10, this);
                        // }
                        if (at2 > 10 && at2 < 21) {
                            if (at2 == 20) {
                                at2 = 0;
                                attack2 = false;
                            }
                            g.drawImage(main23, x - 10, y - 30, this);
                        }
                    } else {
                        g.drawImage(main, x, y, this);
                    }
                }
            }
            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }
    }

    // hiện chữ menu
    private void menu(Graphics g) {
        String st = "Bắt đầu";
        String lv = "Cấp độ";
        String crt = "Nhân vật";
        String ex = "Thoát";
        Font small = new Font("Helvetica", Font.BOLD, 20);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(st, 250, 200);
        g.drawString(lv, 250, 250);
        g.drawString(crt, 250, 300);
        g.drawString(ex, 250, 350);
    }

    // hiện chữ trong level
    private void level(Graphics g) {
        // dễ
        String ez = "Dễ";
        Font big = new Font("Helvetica", Font.BOLD, 20);
        if (lvl == 1) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.white);
        }
        g.setFont(big);
        g.drawString(ez, 250, 210);
        // khó
        String hard = "Khó";
        Font small = new Font("Helvetica", Font.BOLD, 20);
        if (lvl == 2) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.white);
        }
        g.setFont(small);
        g.drawString(hard, 250, 260);
        // địa ngục
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

    // hiện chữ trong crt
    private void Character(Graphics g) {
        // meliodas
        String me = "Meliodas";
        Font meli = new Font("Helvetica", Font.BOLD, 20);
        if (meliodas) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.white);
        }
        g.setFont(meli);
        g.drawString(me, 250, 210);
        // nv2
        String nv2a = "nv2";
        Font nv2b = new Font("Helvetica", Font.BOLD, 20);
        if (nv2) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.white);
        }
        g.setFont(nv2b);
        g.drawString(nv2a, 250, 260);
        // nv3
        String nv3a = "nv3";
        Font nv3b = new Font("Helvetica", Font.BOLD, 20);
        if (nv3) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.white);
        }
        g.setFont(nv3b);
        g.drawString(nv3a, 250, 310);
    }

    // hiện chữ lúc thua
    private void gameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    // check vị trí quái
    private void checkMonster() {

    }

    // di chuyển quái
    private void moveMonster() {

    }

    // check vị trí nhân vật
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

    // đặt quái
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

    // vòng lặp điều kiện
    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkMonster();
            checkCollision();
            moveMonster();
        }

        repaint();
    }

    // các nút thao tác
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            // trong bắt đầu
            if (level) {
                // di chuyển mũi tên
                if (key == KeyEvent.VK_UP && ymn > 200) {
                    ymn -= 50;
                }
                if (key == KeyEvent.VK_DOWN && ymn < 250) {
                    ymn += 50;
                }
                // các nút thao tác
                if (key == KeyEvent.VK_LEFT) {
                    menu = true;
                    level = false;
                    ymn = 180;
                }
                if (key == KeyEvent.VK_ENTER && ymn == 190) {
                    lvl = 1;
                }
                if (key == KeyEvent.VK_ENTER && ymn == 240) {
                    lvl = 2;
                }
                if (key == KeyEvent.VK_ENTER && ymn == 290) {
                    lvl = 10;
                }
            }
            // trong nhân vật
            if (character) {
                // di chuyển mũi tên
                if (key == KeyEvent.VK_UP && ymn > 200) {
                    ymn -= 50;
                }
                if (key == KeyEvent.VK_DOWN && ymn < 250) {
                    ymn += 50;
                }
                // các nút thao tác
                if (key == KeyEvent.VK_LEFT) {
                    menu = true;
                    character = false;
                    ymn = 180;
                }
                if (key == KeyEvent.VK_ENTER && ymn == 190) {
                    meliodas = true;
                    nv2 = false;
                    nv3 = false;
                }
                if (key == KeyEvent.VK_ENTER && ymn == 240) {
                    meliodas = false;
                    nv2 = true;
                    nv3 = false;
                }
                if (key == KeyEvent.VK_ENTER && ymn == 290) {
                    meliodas = false;
                    nv2 = false;
                    nv3 = true;
                }
            }
            // trong menu
            if (menu) {
                // di chuyển mũi tên
                if (key == KeyEvent.VK_UP && ymn > 200) {
                    ymn -= 50;
                }
                if (key == KeyEvent.VK_DOWN && ymn < 300) {
                    ymn += 50;
                }
                // các nút thao tác
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
                    menu = false;
                    character = true;
                    ymn = 190;
                }
                if (key == KeyEvent.VK_RIGHT && ymn == 330) {
                    System.exit(0);
                }
            }
            // vào game
            if (start && menu == false) {
                // tấn công
                if (key == KeyEvent.VK_T) {
                    attack1 = true;
                }
                if (key == KeyEvent.VK_R) {
                    attack2 = true;
                }
                // thoát game
                if (key == KeyEvent.VK_E) {
                    System.exit(0);
                }
                // ra ngoài menu
                if (key == KeyEvent.VK_ESCAPE) {
                    menu = true;
                    start = false;
                }
                // di chuyển giữa các map
                if (key == KeyEvent.VK_LEFT && x == 0 && y == 0 && bando1) {
                    bando1 = false;
                    bando2 = true;
                    x = B_WIDTH - 20 + DOT_SIZE;
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
                    x = B_WIDTH - 20 + DOT_SIZE;
                    locateMonster();
                }
                // di chuyển nhân vật
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