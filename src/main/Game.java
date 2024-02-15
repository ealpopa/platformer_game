package main;

import entities.Player;

import java.awt.*;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Player player;

    public Game() {
        this.initClasses();
        this.gamePanel = new GamePanel(this);
        this.gameWindow = new GameWindow(gamePanel);
        this.gamePanel.requestFocus();


        startGameLoop();
    }

    private void initClasses() {
        this.player = new Player(200, 200);
    }

    private void startGameLoop() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    public void update() {
        // update player position
        // update background
        this.player.update();
    }

    public void render(Graphics g) {
        this.player.render(g);

    }

    public Player getPlayer() {
        return this.player;
    }

    public void windowFocusLost() {
        this.player.resetDirectionBooleans();
    }

    @Override
    public void run() {

        double frameDuration = 1_000_000_000f / this.FPS_SET; // nanoseconds for precision
        double updateDuration = 1_000_000_000f / this.UPS_SET;

        long currentTimestamp;
        long previousTimestamp = System.nanoTime();
        long lastCheckTimestamp = System.currentTimeMillis();

        int frames = 0;
        int updates = 0;

        double updatesDelta = 0;
        double framesDelta = 0;

        while (true) {
            currentTimestamp = System.nanoTime();

            updatesDelta += (currentTimestamp - previousTimestamp) / updateDuration;
            framesDelta += (currentTimestamp - previousTimestamp) / frameDuration;

            previousTimestamp = currentTimestamp;

            if (updatesDelta >= 1) {
                this.update();
                updates++;
                updatesDelta--;
            }

            if (framesDelta >= 1) {
                this.gamePanel.repaint();
                frames++;
                framesDelta--;
            }

            if (System.currentTimeMillis() - lastCheckTimestamp >= 1000) { // milliseconds
                lastCheckTimestamp = System.currentTimeMillis();
                System.out.println("frames: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
}
