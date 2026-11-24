package MenuMVC.Game.Entities;

import MenuMVC.Game.GameModel;
import MenuMVC.Game.Utilities.Dimension;
import MenuMVC.Game.Utilities.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class IndestructibleWall extends Entity{



    public IndestructibleWall(Position position) {
        super.image = GameModel.wall;
        super.dimension = new Dimension(position, 32, 32);
        //TODO Auto-generated constructor stub
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

    
    
}
