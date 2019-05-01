package main;

import util.GameUtil;

import java.awt.*;

import static main.Game.*;

public class Bird extends Rectangle {
    private static final int DEFAULT_BIRD_X = 20;
    private static final int DEFAULT_BIRD_Y = 20;
    public int width;
    public int height;
    public int x;
    public int y;


    public Bird() {
        x = MIDDLE_OF_WIDTH;
        y = MIDDLE_OF_HEIGHT;
        width = DEFAULT_BIRD_X;
        height = DEFAULT_BIRD_Y;
    }

    public Bird(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

//    Design Patterns - Mediator Pattern
    public void intersects(Bird bird, Rectangle rectangle){
        GameUtil.intersects(bird, rectangle);
    }
}
