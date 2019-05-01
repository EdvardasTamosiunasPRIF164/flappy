package main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static main.Game.GROUND_Y;
import static main.Game.HEIGHT;
import static main.Game.WIDTH;

public class Column {
    private static final int COLUMN_DISTANCE_WEIGHT = 300;
    private static final int SPACE_BETWEEN_COLUMNS = 300;
    private static final int COLUMN_WIDTH = 80;
    private static final int COLUMN_BASE_HEIGHT = 50;

    private ArrayList<Rectangle> columns = new ArrayList<>();
    private Random rand = new Random();

    public ArrayList<Rectangle> getColumns() {
        return columns;
    }

    public void clearColumns() {
        columns.clear();
    }

    public void addColumn(boolean start) {
        int width = COLUMN_WIDTH;
        int height = COLUMN_BASE_HEIGHT + rand.nextInt(300);

        if (start) {
            addTopAndBotColumnsOnStart(width, height);
        }
        else {
            addTopAndBotColumns(width, height);
        }
    }

    private void addTopAndBotColumns(int width, int height) {
        columns.add(new Rectangle(columns.get(columns.size() - 1).x + 2 * COLUMN_DISTANCE_WEIGHT, HEIGHT - height - GROUND_Y, width, height));
        columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - SPACE_BETWEEN_COLUMNS));
    }

    private void addTopAndBotColumnsOnStart(int width, int height) {
        columns.add(new Rectangle(WIDTH + width + columns.size() * COLUMN_DISTANCE_WEIGHT, HEIGHT - height - GROUND_Y, width, height));
        columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * COLUMN_DISTANCE_WEIGHT, 0, width, HEIGHT - height - SPACE_BETWEEN_COLUMNS));
    }

}
