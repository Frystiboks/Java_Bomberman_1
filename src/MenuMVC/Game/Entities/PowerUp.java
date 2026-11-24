package MenuMVC.Game.Entities;

import java.util.ArrayList;

import MenuMVC.Game.Utilities.Dimension;
import MenuMVC.Game.Utilities.Position;

public abstract class PowerUp extends Entity {
    private Boolean isActive;
    protected ArrayList<Entity> entitiesToRemove;

    public PowerUp(Position position, ArrayList<Entity> entitiesToRemove) {
        super.dimension = new Dimension(position, 32, 32);
        this.entitiesToRemove = entitiesToRemove;
        isActive = true;
    }

    public abstract void apply(Player player);

    @Override
    public Boolean isOccupying(Entity entity) {
        return false;
    }

    @Override
    public void interact(Entity entity) {
        if (entity instanceof Player player && isActive) {
            isActive = false;
            apply(player);
            entitiesToRemove.add(this);
        }
    }

}

