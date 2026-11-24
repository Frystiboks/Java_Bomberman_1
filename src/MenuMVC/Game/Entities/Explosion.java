package MenuMVC.Game.Entities;

import java.util.ArrayList;

import MenuMVC.Game.GameModel;
import MenuMVC.Game.Utilities.Dimension;
import MenuMVC.Game.Utilities.Position;

public class Explosion extends Entity implements TimedEntity{

    private double timeRemaining;

    public Explosion(Position position) {
        super.image = GameModel.explosion;
        super.dimension = new Dimension(position, 32, 32);
        timeRemaining = 1.0;
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
        entitiesToRemove.add(this);
    }

    @Override
    public Boolean isOccupying(Entity entity) {
        return false;
    }

    @Override
    public void interact(Entity entity) {
        if (entity instanceof Player) {
            ((Player)entity).damage();
        }
    }
    
}
