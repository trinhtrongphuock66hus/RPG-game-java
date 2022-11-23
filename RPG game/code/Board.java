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
    private final int DOT_SIZE = 4;
    // tốc độ quái
    private final int DELAY = 30;
    // vị trí nv
    private int x, y;
    // biến đếm run
    private int x1, y1;
    private int x2 = 0;
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
    // đếm chạy
    private int r = 0;
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
    // di chuyển
    private boolean up = true;
    private boolean down = true;
    private boolean right1 = true;
    private boolean left1 = true;
    // tấn công
    private boolean attack1 = false;
    private boolean attack2 = false;
    // nhân vật
    private boolean meliodas = true;
    private boolean escanor = false;
    private boolean diana = false;
    // nhân vật chạy
    private boolean run = false;
    private boolean left = false;
    private boolean right = false;
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
        // meliodas
        if (meliodas) {
            // load nhân vật
            if (right) {
                ImageIcon main1 = new ImageIcon("attack/attack1.png");
                main = main1.getImage();

                ImageIcon main0a = new ImageIcon("character/character3a.png");
                main0 = main0a.getImage();
            }
            if (left) {
                ImageIcon main1 = new ImageIcon("attack/attack-1.png");
                main = main1.getImage();

                ImageIcon main0a = new ImageIcon("character/character-3a.png");
                main0 = main0a.getImage();
            }
            // load tấn công
            if (right) {
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
            }
            if (left) {
                ImageIcon at1a = new ImageIcon("attack/attack-1.png");
                main11 = at1a.getImage();

                ImageIcon at1b = new ImageIcon("attack/attack-1a.png");
                main12 = at1b.getImage();

                ImageIcon at1c = new ImageIcon("attack/attack-1b.png");
                main13 = at1c.getImage();

                ImageIcon at2a = new ImageIcon("attack/attack-2.png");
                main21 = at2a.getImage();

                ImageIcon at2b = new ImageIcon("attack/attack-2a.png");
                main22 = at2b.getImage();

                ImageIcon at2c = new ImageIcon("attack/attack-2b.png");
                main23 = at2c.getImage();
            }
            // chạy
            if (right) {
                ImageIcon mainra = new ImageIcon("run/run.png");
                mainr = mainra.getImage();

                ImageIcon mainrb = new ImageIcon("run/run1a.png");
                mainr1 = mainrb.getImage();

                ImageIcon mainrc = new ImageIcon("run/run1b.png");
                mainr2 = mainrc.getImage();
            }
            if (left) {
                ImageIcon mainra = new ImageIcon("run/run-.png");
                mainr = mainra.getImage();

                ImageIcon mainrb = new ImageIcon("run/run-1a.png");
                mainr1 = mainrb.getImage();

                ImageIcon mainrc = new ImageIcon("run/run-1b.png");
                mainr2 = mainrc.getImage();
            }
        }
        // escanor
        if (escanor) {
            // load nhân vật
            if (right) {
                ImageIcon main1 = new ImageIcon("attack/attack3.png");
                main = main1.getImage();

                ImageIcon main0a = new ImageIcon("character/character1a.png");
                main0 = main0a.getImage();
            }
            if (left) {
                ImageIcon main1 = new ImageIcon("attack/attack-3.png");
                main = main1.getImage();

                ImageIcon main0a = new ImageIcon("character/character-1a.png");
                main0 = main0a.getImage();
            }
            // load tấn công
            if (right) {
                ImageIcon at1a = new ImageIcon("attack/attack3.png");
                main11 = at1a.getImage();

                ImageIcon at1b = new ImageIcon("attack/attack3a.png");
                main12 = at1b.getImage();

                ImageIcon at1c = new ImageIcon("attack/attack3b.png");
                main13 = at1c.getImage();

                ImageIcon at2a = new ImageIcon("attack/attack4.png");
                main21 = at2a.getImage();

                ImageIcon at2b = new ImageIcon("attack/attack4a.png");
                main22 = at2b.getImage();

                ImageIcon at2c = new ImageIcon("attack/attack4b.png");
                main23 = at2c.getImage();
            }
            if (left) {
                ImageIcon at1a = new ImageIcon("attack/attack-3.png");
                main11 = at1a.getImage();

                ImageIcon at1b = new ImageIcon("attack/attack-3a.png");
                main12 = at1b.getImage();

                ImageIcon at1c = new ImageIcon("attack/attack-3b.png");
                main13 = at1c.getImage();

                ImageIcon at2a = new ImageIcon("attack/attack-4.png");
                main21 = at2a.getImage();

                ImageIcon at2b = new ImageIcon("attack/attack-4a.png");
                main22 = at2b.getImage();

                ImageIcon at2c = new ImageIcon("attack/attack-4b.png");
                main23 = at2c.getImage();
            }
            // chạy
            if (right) {
                ImageIcon mainra = new ImageIcon("run/run2a.png");
                mainr = mainra.getImage();

                ImageIcon mainrb = new ImageIcon("run/run2b.png");
                mainr1 = mainrb.getImage();

                ImageIcon mainrc = new ImageIcon("run/run2c.png");
                mainr2 = mainrc.getImage();
            }
            if (left) {
                ImageIcon mainra = new ImageIcon("run/run-2a.png");
                mainr = mainra.getImage();

                ImageIcon mainrb = new ImageIcon("run/run-2b.png");
                mainr1 = mainrb.getImage();

                ImageIcon mainrc = new ImageIcon("run/run-2c.png");
                mainr2 = mainrc.getImage();
            }
        }
        // diana
        if (diana) {
            // load nhân vật
            if (right) {
                ImageIcon main1 = new ImageIcon("attack/attack6.png");
                main = main1.getImage();

                ImageIcon main0a = new ImageIcon("character/character2a.png");
                main0 = main0a.getImage();
            }
            if (left) {
                ImageIcon main1 = new ImageIcon("attack/attack-6.png");
                main = main1.getImage();

                ImageIcon main0a = new ImageIcon("character/character-2a.png");
                main0 = main0a.getImage();
            }
            // load tấn công
            if (right) {
                ImageIcon at1a = new ImageIcon("attack/attack5.png");
                main11 = at1a.getImage();

                ImageIcon at1b = new ImageIcon("attack/attack5a.png");
                main12 = at1b.getImage();

                ImageIcon at1c = new ImageIcon("attack/attack5b.png");
                main13 = at1c.getImage();

                ImageIcon at2a = new ImageIcon("attack/attack6.png");
                main21 = at2a.getImage();

                ImageIcon at2b = new ImageIcon("attack/attack6a.png");
                main22 = at2b.getImage();

                ImageIcon at2c = new ImageIcon("attack/attack6b.png");
                main23 = at2c.getImage();
            }
            if (left) {
                ImageIcon at1a = new ImageIcon("attack/attack-5.png");
                main11 = at1a.getImage();

                ImageIcon at1b = new ImageIcon("attack/attack-5a.png");
                main12 = at1b.getImage();

                ImageIcon at1c = new ImageIcon("attack/attack-5b.png");
                main13 = at1c.getImage();

                ImageIcon at2a = new ImageIcon("attack/attack-6.png");
                main21 = at2a.getImage();

                ImageIcon at2b = new ImageIcon("attack/attack-6a.png");
                main22 = at2b.getImage();

                ImageIcon at2c = new ImageIcon("attack/attack-6b.png");
                main23 = at2c.getImage();
            }
            // chạy
            if (right) {
                ImageIcon mainra = new ImageIcon("run/run3a.png");
                mainr = mainra.getImage();

                ImageIcon mainrb = new ImageIcon("run/run3b.png");
                mainr1 = mainrb.getImage();

                ImageIcon mainrc = new ImageIcon("run/run3c.png");
                mainr2 = mainrc.getImage();

            }
            if (left) {
                ImageIcon mainra = new ImageIcon("run/run-3a.png");
                mainr = mainra.getImage();

                ImageIcon mainrb = new ImageIcon("run/run-3b.png");
                mainr1 = mainrb.getImage();

                ImageIcon mainrc = new ImageIcon("run/run-3c.png");
                mainr2 = mainrc.getImage();
            }
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

    // tấn công meliodas
    private void attackcrt1(Graphics g) {
        if (attack1) {
            at1 += 2;
            if (right) {
                if (at1 > 0 && at1 < 16) {
                    g.drawImage(main12, x - 10, y, this);
                }
            }
            if (left) {
                if (at1 > 0 && at1 < 16) {
                    g.drawImage(main12, x, y, this);
                }
            }
            if (at1 > 15) {
                if (at1 >= 30) {
                    at1 = 0;
                    attack1 = false;
                }
                g.drawImage(main13, x - 12, y - 20, this);
            }
        } else if (attack2) {
            at2 += 2;
            if (right) {
                if (at2 > 0 && at2 < 16) {
                    g.drawImage(main21, x - 15, y - 30, this);
                }
                if (at2 > 15) {
                    if (at2 >= 30) {
                        at2 = 0;
                        attack2 = false;
                    }
                    g.drawImage(main23, x - 10, y - 30, this);
                }
            }
            if (left) {
                if (at2 > 0 && at2 < 16) {
                    g.drawImage(main21, x - 5, y - 30, this);
                }
                if (at2 > 15) {
                    if (at2 >= 30) {
                        at2 = 0;
                        attack2 = false;
                    }
                    g.drawImage(main23, x - 25, y - 30, this);
                }
            }
        } else {
            if (!run) {
                g.drawImage(main, x, y, this);
            }
        }
    }

    // tấn công escanor
    private void attackcrt2(Graphics g) {
        if (attack1) {
            at1 += 2;
            if (right) {
                if (at1 > 0 && at1 < 16) {
                    g.drawImage(main12, x - 10, y + 1, this);
                }
                if (at1 > 15) {
                    if (at1 >= 30) {
                        at1 = 0;
                        attack1 = false;
                    }
                    g.drawImage(main13, x - 10, y - 20, this);
                }
            }
            if (left) {
                if (at1 > 0 && at1 < 16) {
                    g.drawImage(main12, x - 10, y + 1, this);
                }
                if (at1 > 15) {
                    if (at1 >= 30) {
                        at1 = 0;
                        attack1 = false;
                    }
                    g.drawImage(main13, x - 20, y - 20, this);
                }
            }

            // } else if (attack2) {
            // at2 += 2;
            // if (right) {
            // if (at2 > 0 && at2 < 16) {
            // g.drawImage(main21, x - 13, y - 11, this);
            // }
            // if (at2 > 15 && at2 < 31) {
            // g.drawImage(main22, x - 13, y - 17, this);
            // }
            // if (at2 > 30) {
            // if (at2 >= 45) {
            // at2 = 0;
            // attack2 = false;
            // }
            // g.drawImage(main23, x - 4, y - 20, this);
            // }
            // }
            // if (left) {
            // if (at2 > 0 && at2 < 16) {
            // g.drawImage(main21, x - 5, y - 11, this);
            // }
            // if (at2 > 15 && at2 < 31) {
            // g.drawImage(main22, x - 14, y - 17, this);
            // }
            // if (at2 > 30) {
            // if (at2 >= 45) {
            // at2 = 0;
            // attack2 = false;
            // }
            // g.drawImage(main23, x - 1, y - 20, this);
            // }
            // }
        } else {
            if (!run) {
                g.drawImage(main, x - 4, y - 4, this);
            }
        }
    }

    // tấn công diana
    private void attackcrt3(Graphics g) {
        if (attack1) {
            at1 += 2;
            if (right) {
                if (at1 > 0 && at1 < 16) {
                    g.drawImage(main11, x - 10, y - 4, this);
                }
                if (at1 > 15 && at1 < 31) {
                    g.drawImage(main12, x + 2, y, this);
                }
                if (at1 > 30) {
                    if (at1 >= 45) {
                        at1 = 0;
                        attack1 = false;
                    }
                    g.drawImage(main13, x + 1, y - 11, this);
                }
            }
            if (left) {
                if (at1 > 0 && at1 < 16) {
                    g.drawImage(main11, x - 2, y - 4, this);
                }
                if (at1 > 15 && at1 < 31) {
                    g.drawImage(main12, x - 18, y, this);
                }
                if (at1 > 30) {
                    if (at1 >= 45) {
                        at1 = 0;
                        attack1 = false;
                    }
                    g.drawImage(main13, x - 19, y - 11, this);
                }
            }

        } else if (attack2) {
            at2 += 2;
            if (right) {
                if (at2 > 0 && at2 < 16) {
                    g.drawImage(main22, x - 5, y - 4, this);
                }
                if (at2 > 15) {
                    if (at2 >= 30) {
                        at2 = 0;
                        attack2 = false;
                    }
                    g.drawImage(main23, x - 1, y - 8, this);
                }
            }
            if (left) {
                if (at2 > 0 && at2 < 16) {
                    g.drawImage(main22, x - 5, y - 4, this);
                }
                if (at2 > 15) {
                    if (at2 >= 30) {
                        at2 = 0;
                        attack2 = false;
                    }
                    g.drawImage(main23, x - 13, y - 8, this);
                }
            }
        } else {
            if (!run) {
                g.drawImage(main, x, y, this);
            }
        }
    }

    private void Characterrun(Graphics g) {
        if (meliodas) {
            if (r > 0 && r < 10) {
                g.drawImage(mainr, x, y - 2, this);
            }
            if (r > 9 && r < 20) {
                g.drawImage(mainr1, x, y - 2, this);
            }
            if (r > 19) {
                if (r > 30) {
                    r = 0;
                }
                g.drawImage(mainr2, x, y - 2, this);
            }
        }
        if (escanor) {
            if (r > 0 && r < 10) {
                g.drawImage(mainr, x, y - 2, this);
            }
            if (r > 9 && r < 20) {
                g.drawImage(mainr1, x, y - 2, this);
            }
            if (r > 19) {
                if (r > 30) {
                    r = 0;
                }
                g.drawImage(mainr2, x, y - 2, this);
            }
        }
        if (diana) {
            if (r > 0 && r < 10) {
                g.drawImage(mainr, x, y - 2, this);
            }
            if (r > 9 && r < 20) {
                g.drawImage(mainr1, x, y - 2, this);
            }
            if (r > 19) {
                if (r > 30) {
                    r = 0;
                }
                g.drawImage(mainr2, x, y - 2, this);
            }
        }
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
                // chạy
                if (run) {
                    x1 = x;
                    y1 = y;
                    r += 5;
                    Characterrun(g);
                }
                // nhân vật
                if (meliodas) {
                    attackcrt1(g);
                }
                if (escanor) {
                    attackcrt2(g);
                }
                if (diana) {
                    attackcrt3(g);
                }
                if (x == x1 && y == y1) {
                    x2 += 1;
                    if (x2 > 10) {
                        x2 = 0;
                        run = false;
                    }
                }
            }
            Toolkit.getDefaultToolkit().sync();

        } else {
            // chết
            if (bando1) {
                g.drawImage(map1, 0, 0, this);
            }
            if (bando2) {
                g.drawImage(map2, 0, 0, this);
            }
            if (bando3) {
                g.drawImage(map3, 0, 0, this);
            }
            if (right) {
                g.drawImage(main0, x - 5, y + 10, this);
            }
            if (left) {
                g.drawImage(main0, x - 15, y + 10, this);
            }
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
        String nv2a = "Escanor";
        Font nv2b = new Font("Helvetica", Font.BOLD, 20);
        if (escanor) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.white);
        }
        g.setFont(nv2b);
        g.drawString(nv2a, 250, 260);
        // nv3
        String nv3a = "Diana";
        Font nv3b = new Font("Helvetica", Font.BOLD, 20);
        if (diana) {
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
                    escanor = false;
                    diana = false;
                }
                if (key == KeyEvent.VK_ENTER && ymn == 240) {
                    meliodas = false;
                    escanor = true;
                    diana = false;
                }
                if (key == KeyEvent.VK_ENTER && ymn == 290) {
                    meliodas = false;
                    escanor = false;
                    diana = true;
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
                    x = 36 - DOT_SIZE;
                    y = 532;
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
                // lấy vị trí
                if (key == KeyEvent.VK_P) {
                    System.out.println(x);
                    System.out.println(y);
                }
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
                // chết
                if (key == KeyEvent.VK_U) {
                    inGame = false;
                }
                // hồi sinh
                if (key == KeyEvent.VK_I) {
                    inGame = true;
                }
                // ra ngoài menu
                if (key == KeyEvent.VK_ESCAPE) {
                    menu = true;
                    start = false;
                }
                // di chuyển giữa các map
                if (key == KeyEvent.VK_LEFT && x == 0 && y == 412 && bando1) {
                    bando1 = false;
                    bando2 = true;
                    x = B_WIDTH - 20 + DOT_SIZE;
                    locateMonster();
                }

                if (key == KeyEvent.VK_RIGHT && x == B_WIDTH - 20 && y == 412 && bando2) {
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
                else if (key == KeyEvent.VK_LEFT && left1) {
                    x -= DOT_SIZE;
                    run = true;
                    left = true;
                    right = false;
                    loadcharacter();
                }

                else if (key == KeyEvent.VK_RIGHT && right1) {
                    x += DOT_SIZE;
                    run = true;
                    left = false;
                    right = true;
                    loadcharacter();
                }

                else if (key == KeyEvent.VK_UP && up) {
                    y -= DOT_SIZE;
                    run = true;
                }

                else if (key == KeyEvent.VK_DOWN && down) {
                    y += DOT_SIZE;
                    run = true;
                }
            }
        }
    }
}