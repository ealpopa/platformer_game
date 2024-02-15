package utils;

public class Constants {

    public static enum Directions {
        IDLE, LEFT, UP, RIGHT, DOWN
    }

    public static enum PlayerActions {
        IDLE, RUNNING, JUMPING, FALLING, GROUND, HIT, ATTACK_1, ATTACK_JUMP_1, ATTACK_JUMP_2;

        public static int getSpriteAmount(PlayerActions playerAction) {
            return switch (playerAction) {
                case RUNNING -> 6;
                case IDLE -> 5;
                case HIT -> 4;
                case JUMPING, ATTACK_1, ATTACK_JUMP_1, ATTACK_JUMP_2 -> 3;
                case GROUND -> 2;
                case FALLING -> 1;
            };
        }
    }
}
