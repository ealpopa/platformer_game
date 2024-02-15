package inputs;

import main.GamePanel;
import utils.Constants;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP ->
                    this.gamePanel.getGame().getPlayer().setUp(true);
            case KeyEvent.VK_A, KeyEvent.VK_LEFT ->
                    this.gamePanel.getGame().getPlayer().setLeft(true);
            case KeyEvent.VK_S, KeyEvent.VK_DOWN ->
                    this.gamePanel.getGame().getPlayer().setDown(true);
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT ->
                    this.gamePanel.getGame().getPlayer().setRight(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP ->
                    this.gamePanel.getGame().getPlayer().setUp(false);
            case KeyEvent.VK_A, KeyEvent.VK_LEFT ->
                    this.gamePanel.getGame().getPlayer().setLeft(false);
            case KeyEvent.VK_S, KeyEvent.VK_DOWN ->
                    this.gamePanel.getGame().getPlayer().setDown(false);
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT ->
                this.gamePanel.getGame().getPlayer().setRight(false);
        }
    }
}
