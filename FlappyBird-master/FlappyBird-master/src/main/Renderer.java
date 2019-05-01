package main;

import java.awt.*;

import javax.swing.JPanel;

public class Renderer extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Main.game.draw(g);
    }
}
