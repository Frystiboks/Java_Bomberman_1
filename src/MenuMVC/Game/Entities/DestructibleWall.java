package MenuMVC.Game.Entities;

import java.util.ArrayList;

import MenuMVC.Game.GameModel;
import MenuMVC.Game.Utilities.Dimension;
import MenuMVC.Game.Utilities.Position;

public class DestructibleWall extends Entity{
    public DestructibleWall(Position position) {
        super.image = GameModel.crate;
        super.dimension = new Dimension(position, 32, 32);
    }

    @Override
    public Boolean isOccupying(Entity entity) {
        return true;
    }

    @Override
    public void interact(Entity entity) {
    }

    @Override 
    public boolean blocksExplosion() {
        return true;
    }

    public void dropPowerUp(ArrayList<Entity> entitiesToAdd, ArrayList<Entity> entitiesToRemove) {
        if (Math.random() > 0.5) {
            return;
        }

        Position position = new Position(dimension.getX(), dimension.getY());
        PowerUp powerUp;

        int random = (int) (Math.random() * 4);

        switch (random) {
            case 0 -> powerUp = new MaxBombsPowerUp(position, entitiesToRemove);
            case 1 -> powerUp = new MaxSpeedPowerUp(position, entitiesToRemove);
            case 2 -> powerUp = new MaxBombStrengthPowerUp(position, entitiesToRemove);
            default -> powerUp = new HealthPowerUp(position, entitiesToRemove);
        }

        entitiesToAdd.add(powerUp);
    }
}
