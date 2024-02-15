package entities;

import utils.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Player extends Entity {
    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 15; // divides FPS to get sprites per second
    private Constants.PlayerActions playerAction = Constants.PlayerActions.ATTACK_1;
    private boolean moving = false, attacking = false;
    private boolean left, right, up, down;
    private final float speed = 2.0f;

    public Player(float x, float y) {
        super(x, y);
        this.loadAnimations();
    }

    public void update() {
        this.updatePosition();
        this.updateAnimationTick();
        this.setAnimation();

    }

    public void render(Graphics g) {
        g.drawImage(this.animations[this.playerAction.ordinal()][this.animationIndex], (int) this.x, (int) this.y, 256, 160, null);
    }

    private void loadAnimations() {
        InputStream inputStream = this.getClass().getResourceAsStream("/player_sprites.png");
        try {
            BufferedImage img = ImageIO.read(inputStream);

            this.animations = new BufferedImage[9][6];

            for (int i = 0; i < this.animations.length; i++) {
                for (int j = 0; j < this.animations[i].length; j++) {
                    this.animations[i][j] = img.getSubimage(j * 64, i * 40, 64, 40);
                }
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }
    }

    private void updatePosition() {
        this.moving = false;

        if (left && !right) {
            this.x -= this.speed;
            this.moving = true;
        } else if (right && !left) {
            this.x += this.speed;
            this.moving = true;
        }

        if (up && !down) {
            this.y -= this.speed;
            this.moving = true;
        } else if (down && !up) {
            this.y += this.speed;
            this.moving = true;
        }
    }

    private void setAnimation() {
        Constants.PlayerActions currentAnimation = this.playerAction;
        // store current ongoing animation

        if (this.moving) {
            this.playerAction = Constants.PlayerActions.RUNNING;
        } else {
            this.playerAction = Constants.PlayerActions.IDLE;
        }

        if (this.attacking) {
            this.playerAction = Constants.PlayerActions.ATTACK_1;
        }

        // if the animation need to change (to another animation) in the middle of the current animation
        // reset the animation tick and index so that the new animation starts from 0 index
        if (currentAnimation != this.playerAction) {
            this.resetAnimationTick();
        }
    }

    private void resetAnimationTick() {
        this.animationTick = 0;
        this.animationIndex = 0;
    }

    private void updateAnimationTick() {
        this.animationTick++; // increases every frame of the 120 fps
        if (this.animationTick >= this.animationSpeed) {
            // when animation speed limit has been reached, resulting in 12 sprite updates pe second
            // update animation index
            this.animationTick = 0;
            this.animationIndex++;
            if (this.animationIndex >= Constants.PlayerActions.getSpriteAmount(this.playerAction)){
                this.animationIndex = 0; // reset index at end of idle animation array length
                this.attacking = false; // reset attack at end of attack animation. I.e. one attack per click
            }


        }
    }

    public void setPlayerAction(Constants.PlayerActions playerAction) {
        this.playerAction = playerAction;
    }

    public void resetDirectionBooleans() {
        this.right = false;
        this.left = false;
        this.up = false;
        this.down = false;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

}
