package MenuMVC.Game.Entities;

import java.util.ArrayList;

import MenuMVC.Game.GameModel;
import MenuMVC.Game.Utilities.Position;

public class MaxBombStrengthPowerUp extends PowerUp {

    public MaxBombStrengthPowerUp(Position position, ArrayList<Entity> entitiesToRemove) {
        super(position, entitiesToRemove);
        super.image = GameModel.range;
    }

    @Override
    public void apply(Player player) {
        player.incrementBombSize();
    }
}
