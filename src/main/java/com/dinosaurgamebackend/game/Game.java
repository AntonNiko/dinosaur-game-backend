package com.dinosaurgamebackend.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import com.dinosaurgamebackend.exceptions.GameAlreadyStartedException;
import com.dinosaurgamebackend.exceptions.GameIsNotRunningException;
import com.dinosaurgamebackend.exceptions.PlayerDoesNotExistException;
import com.dinosaurgamebackend.websocketmessage.GameState;

public class Game {
    private final int PLAYER_START_POSITION_X = 250;
    private final int PLAYER_START_POSITION_Y = 500;

    private Queue<PlayerColor> availableColors = new LinkedList<PlayerColor>();
    private boolean gameActive;
    private Map<Integer, Player> currentPlayers;

    public Game() {
        this.gameActive = false;
        this.currentPlayers = new HashMap<Integer, Player>();

        // Add the available colors
        this.availableColors.add(PlayerColor.GREEN);
        this.availableColors.add(PlayerColor.BLUE);
        this.availableColors.add(PlayerColor.RED);
        this.availableColors.add(PlayerColor.YELLOW);
    }

    public void startGame() throws GameAlreadyStartedException {
        if (!this.gameActive) {
            this.gameActive = true;
            return;
        }
        throw new GameAlreadyStartedException();
    }

    public GameState addPlayer(int playerId) {

        // Determine the color to be used by the new player. If no colors remain, then default to green.
        PlayerColor playerColor;
        try {
            playerColor = this.availableColors.remove();
        } catch (NoSuchElementException e) {
            playerColor = PlayerColor.GREEN;
        }

        this.currentPlayers.put(playerId, new Player(
            this.PLAYER_START_POSITION_X,
            this.PLAYER_START_POSITION_Y,
            0, 0,
            playerColor
        ));

        return null;
    }

    public Map<Integer, Player> getPlayers() {
        return this.currentPlayers;
    }

    public void removePlayer(int playerId) throws PlayerDoesNotExistException {
        try {
            Player removedPlayer = this.currentPlayers.remove(playerId);
            this.availableColors.add(removedPlayer.getPlayerColor());
        } catch (NullPointerException e) {
            throw new PlayerDoesNotExistException();
        }
    }

    public void endGame() throws GameIsNotRunningException {
        if (!this.gameActive) {
            throw new GameIsNotRunningException();
        }
        this.gameActive = false;
    }

    public void restartGame() {
        this.currentPlayers.forEach((playerId, player) -> {
            player.setPosition(this.PLAYER_START_POSITION_X, this.PLAYER_START_POSITION_Y);
        });
    }

    public void setPlayerPosition(int playerId, int positionX, int positionY) throws PlayerDoesNotExistException {
        try {
            this.currentPlayers.get(playerId).setPosition(positionX, positionY);
        } catch (NullPointerException e) {
            throw new PlayerDoesNotExistException();
        }
    }

    public void setPlayerVelocityX(int playerId, int velocityX) throws PlayerDoesNotExistException {
        try {
            this.currentPlayers.get(playerId).setVelocityX(velocityX);
        } catch (NullPointerException e) {
            throw new PlayerDoesNotExistException();
        }
    }

    public void setPlayerVelocityY(int playerId, int velocityY) throws PlayerDoesNotExistException {
        try {
            this.currentPlayers.get(playerId).setVelocityY(velocityY);
        } catch (NullPointerException e) {
            throw new PlayerDoesNotExistException();
        }
    }
}
