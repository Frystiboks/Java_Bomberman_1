package MenuMVC.Game.Entities;

import java.util.ArrayList;

import MenuMVC.Game.GameModel;
import MenuMVC.Game.Utilities.Position;

public class HealthPowerUp extends PowerUp {

    public HealthPowerUp(Position position, ArrayList<Entity> entitiesToRemove) {
        super(position, entitiesToRemove);
        super.image = GameModel.life;
    }

    @Override
    public void apply(Player player) {
        player.incrementHealth();
    }
}
