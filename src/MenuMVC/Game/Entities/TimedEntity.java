package MenuMVC.Game.Entities;

import java.util.ArrayList;

public interface TimedEntity {
    double getTimeRemaining();

    /**
     * Advances this entity's timer by one game turn.
     */
    void tick(double dt);

    /**
     * Tells the game loop whether this entity is ready to activate.
     */
    boolean isExpired();

    /**
     * Runs the timed effect once the entity expires.
     */
    void activate(ArrayList<Entity> entitiesToRemove, ArrayList<Entity> entitiesToAdd);
}
