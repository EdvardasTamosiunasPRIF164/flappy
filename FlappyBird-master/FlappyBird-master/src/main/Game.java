package main;

import util.GameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Game implements ActionListener, MouseListener, KeyListener {
    final static int WIDTH = 800;
    final static int HEIGHT = 800;
    final static int GROUND_Y = 120;
    final static int MIDDLE_OF_HEIGHT = HEIGHT / 2;
    final static int MIDDLE_OF_WIDTH = WIDTH / 2;
    private final static int DELAY = 20;
    private final static int GROUND_HEIGHT = 140;
    private final static int MOVEMENT_RANGE = HEIGHT - GROUND_Y;
    private final static int JUMP_WEIGHT = 15;
    private final static int SPEED = 10;
    private final static String TITLE = "Flappy Bird by Tolvinas";
    private final static String FONT_NAME = "Arial";
    private final static int TEXT_X = 250;
    private final static int POINTS_Y = 100;
    private final static int FONT_SIZE = 50;

    private Renderer renderer;
    private Bird bird;

    private UserInput userInput = new UserInput();
    private ArrayList<Rectangle> columns;
    private Column column = new Column();

    private int ticks;
    private int yDrop;
    private int points;

    private boolean isGameOver;
    private boolean isGameStarted;

    public Game() {
        JFrame jframe = new JFrame();
        Timer timer = new Timer(DELAY, this);

        renderer = new Renderer();
        columns = column.getColumns();
        initJFrame(jframe);
        bird = new Bird();
        column.addColumn(true);
        column.addColumn(true);

        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ticks++;
        if (isGameStarted) {
            columnsMovement();
            if (shouldBirdDrop()) {
                yDrop += 2;
            }
            addColumnsAfterStart();
            birdMocvementDrop();
            birdMovementInColumns();
            isGameOver();
        }
        renderer.repaint();
    }

    private void birdMocvementDrop() {
        bird.y += yDrop;
    }

    private void isGameOver() {
        if (isBirdOutOfMap()) {
            isGameOver = true;
        }
    }

    private boolean isBirdOutOfMap() {
        return bird.y > MOVEMENT_RANGE || bird.y < 0;
    }

    private void birdMovementInColumns() {
        for (Rectangle oColumn : columns) {
            if (oColumn.y == 0 &&
                    bird.x + bird.width / 2 > oColumn.x + oColumn.width / 2 - 10 &&
                    bird.x + bird.width / 2 < oColumn.x + oColumn.width / 2 + 10) {
                points++;
            }
            if (GameUtil.intersects(bird, oColumn)) {
                isGameOver = true;
            }
        }
    }

    private void addColumnsAfterStart() {
        for (int i = 0; i < columns.size(); i++) {
            Rectangle tempColumn = columns.get(i);

            if (tempColumn.x + tempColumn.width < 0) {
                columns.remove(tempColumn);

                if (tempColumn.y == 0) {
                    column.addColumn(false);
                }
            }
        }
    }

    private void columnsMovement() {
        for (Rectangle column : columns) {
            column.x -= SPEED;
        }
    }

    private boolean shouldBirdDrop() {
        return ticks % 2 == 0 && yDrop < 15;
    }

    private void paintColumn(Graphics g, Rectangle column) {
        g.setColor(Color.green.darker());
        g.fillRect(column.x, column.y, column.width, column.height);
    }

    private void jump() {
        if (isGameOver) {
            bird = new Bird();
            column.clearColumns();
            yDrop = 0;
            points = 0;

            column.addColumn(true);
            column.addColumn(true);

            isGameOver = false;
        }

        if (!isGameStarted) {
            isGameStarted = true;
        }
        else if (!isGameOver) {
            if (yDrop > 0) {
                yDrop = 0;
            }
            yDrop -= JUMP_WEIGHT;
        }
    }

    void draw(Graphics g) {
        drawSky(g);
        drawGround(g);
        drawBird(g);
        drawColumns(g);
        drawText(g);
    }

    private void initJFrame(JFrame jframe) {
        jframe.add(renderer);
        jframe.setTitle(TITLE);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(WIDTH, HEIGHT);
        jframe.addMouseListener(this);
        jframe.addKeyListener(this);
        jframe.setResizable(false);
        jframe.setVisible(true);
    }

    private void drawColumns(Graphics g) {
        for (Rectangle column : columns) {
            paintColumn(g, column);
        }
    }

    private void drawText(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE));

        if (!isGameStarted) {
            g.drawString("Press space", TEXT_X, MIDDLE_OF_HEIGHT - FONT_SIZE);
        }

        if (isGameOver) {
            g.drawString("Game Over", TEXT_X, MIDDLE_OF_HEIGHT - FONT_SIZE);
        } else {
            g.drawString(String.valueOf(points), MIDDLE_OF_WIDTH, POINTS_Y);
        }
    }

    private void drawBird(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(bird.x, bird.y, bird.width, bird.height);
    }

    private void drawGround(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(0, HEIGHT - GROUND_Y, WIDTH, GROUND_HEIGHT);
    }

    private void drawSky(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

//    public boolean intersects(Bird r, Rectangle c) {
//        int tw = c.width;
//        int th = c.height;
//        int rw = r.width;
//        int rh = r.height;
//        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
//            return false;
//        }
//        int tx = c.x;
//        int ty = c.y;
//        int rx = r.x;
//        int ry = r.y;
//        rw += rx;
//        rh += ry;
//        tw += tx;
//        th += ty;
//        //      overflow || intersect
//        return ((rw < rx || rw > tx) &&
//                (rh < ry || rh > ty) &&
//                (tw < tx || tw > rx) &&
//                (th < ty || th > ry));
//    }

    @Override
    public void keyReleased(KeyEvent e) {
        Command command = userInput.getCommand(e.getKeyChar());
        if (command.equals(Command.JUMP)) {
            jump();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
}
