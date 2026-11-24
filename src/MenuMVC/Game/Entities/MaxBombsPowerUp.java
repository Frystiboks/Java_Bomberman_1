package MenuMVC.Game.Entities;

import java.util.ArrayList;

import MenuMVC.Game.GameModel;
import MenuMVC.Game.Utilities.Position;

public class MaxBombsPowerUp extends PowerUp {

    public MaxBombsPowerUp(Position position, ArrayList<Entity> entitiesToRemove) {
        super(position, entitiesToRemove);
        super.image = GameModel.capacity;
    }

    @Override
    public void apply(Player player) {
        player.incrementBombs();
    }
}
