package MenuMVC.Game.Entities;

import MenuMVC.Game.GameModel;
import MenuMVC.Game.Utilities.Dimension;
import MenuMVC.Game.Utilities.Position;

public class EmptyTile extends Entity{
    public EmptyTile(Position position, String displayName) {
        super.image = GameModel.grass;
        super.dimension = new Dimension(position, 32, 32);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Boolean isOccupying(Entity entity) {
        return false;
    }

    @Override
    public void interact(Entity entity) {
        
    }
}
