package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private Game game;


    public GamePanel(Game game) {
        this.game = game;
        this.mouseInputs = new MouseInputs(this);
        this.setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public Game getGame() {
        return this.game;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.game.render(g);
    }

    public void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        this.setPreferredSize(size);
    }

}
