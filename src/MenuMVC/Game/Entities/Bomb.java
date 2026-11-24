package MenuMVC.Game.Entities;

import java.util.ArrayList;

import MenuMVC.Game.GameModel;
import MenuMVC.Game.Board.BoardController;
import MenuMVC.Game.Utilities.Dimension;
import MenuMVC.Game.Utilities.Position;

public class Bomb extends Entity implements TimedEntity{

    private BoardController board;
    private double timeRemaining;
    private int bombSize;
    private boolean activated = false;
    private final Runnable regenerateBomb;

    public Bomb(Position position, BoardController board, Runnable regenerateBomb, int bombSize) {
        super.image = GameModel.bomb;
        super.dimension = new Dimension(position, 24, 24);
        this.board = board;
        this.timeRemaining = 5.0;
        this.bombSize = bombSize;
        this.regenerateBomb = regenerateBomb;
    }

    @Override
    public double getTimeRemaining() {
        return timeRemaining;
    }

    @Override
    public void tick(double dt) {
        timeRemaining += -dt;
    }

    @Override
    public boolean isExpired() {
        if (timeRemaining <= 0) {
            return true;
        }
        return false;
    }

    @Override
    public void activate(ArrayList<Entity> entitiesToRemove, ArrayList<Entity> entitiesToAdd) {
        // create new explosion and remove the bomb entity
        if (activated) {
            return;
        }
        activated = true;
        entitiesToRemove.add(this);
        regenerateBomb.run();

        Position gridPosition = board.getGridPosition(this);

        int gridX = (int) gridPosition.getX();
        int gridY = (int) gridPosition.getY();

        // Explosion at the bomb's location
        entitiesToAdd.add(
            new Explosion(board.getTilePosition(gridX, gridY))
        );

        // Propagate in all four directions
        createExplosions(entitiesToRemove, entitiesToAdd, gridX, gridY);
    }

    private void createExplosions(ArrayList<Entity> entitiesToRemove, ArrayList<Entity> entitiesToAdd, int gridX, int gridY) {
        int[][] directions = {
            { 0, -1 }, // Up
            { 0,  1 }, // Down
            {-1,  0 }, // Left
            { 1,  0 }  // Right
        };

        for (int[] direction : directions) {
            for (int i = 1; i <= bombSize; i++) {
                int x = gridX + direction[0] * i;
                int y = gridY + direction[1] * i;

                // Check board boundaries
                if (x < 0 || x >= board.getGridSize() ||
                    y < 0 || y >= board.getGridSize()) {
                    break;
                }

                Entity obstacle = board.getEntityAt(x, y);

                // Stop if an obstacle blocks the explosion
                if (obstacle instanceof IndestructibleWall) {
                    break;
                }

                Position position = board.getTilePosition(x, y);

                entitiesToAdd.add(new Explosion(position));

                if (obstacle instanceof DestructibleWall) {
                    ((DestructibleWall)obstacle).dropPowerUp(entitiesToAdd, entitiesToRemove);
                    entitiesToRemove.add(obstacle);
                    break;
                }
            }
        }
    }

    @Override
    public Boolean isOccupying(Entity entity) {
        return true;
    }

    @Override
    public void interact(Entity entity) {
        if (entity instanceof Explosion && !activated) {
            //blow up
            activate(board.getEntitiesToRemove(), board.getEntitiesToAdd());
        }
    }
}
