package MenuMVC.Game.Entities;

import java.util.ArrayList;

import MenuMVC.Game.GameModel;
import MenuMVC.Game.Utilities.Position;

public class MaxSpeedPowerUp extends PowerUp {

    public MaxSpeedPowerUp(Position position, ArrayList<Entity> entitiesToRemove) {
        super(position, entitiesToRemove);
        super.image = GameModel.speed;
    }

    @Override
    public void apply(Player player) {
        player.incrementSpeed();
    }
}
