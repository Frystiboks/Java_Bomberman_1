package MenuMVC.Game.Entities;

import java.util.ArrayList;

import MenuMVC.Game.Board.BoardController;
import MenuMVC.Game.Utilities.Dimension;
import MenuMVC.Game.Utilities.PlayerControls;
import MenuMVC.Game.Utilities.Position;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

public class Player extends Entity{

    // implement change position
    String displayName;
    private double speed = 75;
    private int bombs;
    private BoardController board;
    private IntegerProperty health;
    private int bombSize;
    private double invincibilityTimer = 0;
    private static final double INVINCIBILITY_DURATION = 1.5;
    public PlayerControls controls = new PlayerControls();

    public Player(Position position, String displayName, BoardController board, int imageSelector){
        if (imageSelector == 0) {
            super.image = new Image("MenuMVC/Game/Canvas/assets/players/blue_down_0.png");
        }
        else{
            super.image = new Image("MenuMVC/Game/Canvas/assets/players/red_down_0.png");
        }
        super.dimension = new Dimension(position, 24, 24);
        this.displayName = displayName;
        this.board = board;
        this.bombs = 2;
        this.health = new SimpleIntegerProperty(1);
        this.bombSize = 2;
    }

    @Override
    public Boolean isOccupying(Entity entity) {
        return false;
    }

    @Override
    public void interact(Entity entity) {
    }


    public double[] getSpeed() {
        double[] data = {speed, controls.getVelX(), controls.getVelY()};
        return data;
    }

    public Dimension getNextPosition(double dt){
        Position nextPosition = new Position(getDimension().getX()+controls.getVelX()*speed*dt, getDimension().getY()+controls.getVelY()*speed*dt);
        return new Dimension(nextPosition, getDimension().getWidth(), getDimension().getHeight());
    }

    public void setPosition(double dt){
        getDimension().setPosition(controls.getVelX()*speed*dt, controls.getVelY()*speed*dt);
    }

    public synchronized void setBomb(){
        if (bombs >= 1) {
            board.getEntitiesToAdd().add(new Bomb(new Position(
            super.getDimension().getX(),
            super.getDimension().getY()),
            board,
            this::incrementBombs, bombSize));   

            bombs += -1;
        }
    }

    public synchronized void incrementBombs(){
        bombs += 1;
    }

    public void incrementHealth(){
        health.set(getHealth()+1);
    }

    public int getHealth(){
        return health.get();
    }

    public IntegerProperty healthProperty() {
        return health;
    }

    public void damage(){
        if (invincibilityTimer > 0) {
            return;
        }
        health.set(getHealth()-1);
        System.out.println(health);
        invincibilityTimer = INVINCIBILITY_DURATION;
    }

    public boolean isDead() {
        return getHealth() <= 0;
    }

    public void updateInvincibility(double dt) {
        if (invincibilityTimer > 0) {
            invincibilityTimer -= dt;

            if (invincibilityTimer < 0) {
                invincibilityTimer = 0;
            }
        }
    }

    public void incrementSpeed(){
        speed += 20;
    }

    public void incrementBombSize(){
        bombSize += 1;
    }
    
}
