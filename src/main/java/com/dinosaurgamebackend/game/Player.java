package com.dinosaurgamebackend.game;

public class Player {
    private int positionX;
    private int positionY;
    private int velocityX;
    private int velocityY;
    private MoveState moveState;
    private JumpState jumpState;
    private PlayerColor playerColor;

    public Player(int positionX, int positionY, int velocityX, int velocityY, PlayerColor playerColor) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.playerColor = playerColor;

        this.moveState = MoveState.IDLE;
        this.jumpState = JumpState.IDLE;
    }

    public void setPosition(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void setVelocityX(int velocityX) {
        if (velocityX == 0) {
            this.moveState = MoveState.IDLE;
        } else if (velocityX < 0) {
            this.moveState = MoveState.MOVING_LEFT;
        } else {
            this.moveState = MoveState.MOVING_RIGHT;
        }

        this.velocityX = velocityX;
    }

    public void setVelocityY(int velocityY) {
        if (velocityY == 0) {
            this.jumpState = JumpState.IDLE;
        } else {
            this.jumpState = JumpState.ACTIVE;
        }

        this.velocityY = velocityY;
    }

    public PlayerColor getPlayerColor() {
        return this.playerColor;
    }
}
