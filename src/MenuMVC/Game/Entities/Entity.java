package MenuMVC.Game.Entities;

import MenuMVC.Game.Canvas.CanvasController;
import MenuMVC.Game.Utilities.Dimension;
import MenuMVC.Game.Utilities.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Base type for anything that can exist on the board, interact with movement,
 * and render itself onto the canvas.
 */
public abstract class Entity {
    //protected Position position;
    protected Dimension dimension;
    protected Image image;
    //public Color background;

    public Entity() {
    }

    public Image getimage() {
        return image;
    }

    public Dimension getDimension(){
        return dimension;
    }


    /**
     * Tells Board.canMoveTo whether this entity should stop movement into its tile.
     */
    public abstract Boolean isOccupying(Entity entity);

    /**
     * Runs this entity's effect when another entity tries to enter the same tile.
     */
    public abstract void interact(Entity entity);

    /**
     * Checks if an entity is overlapping the current entity
     * @param entity that is moving into this object
     * @return true if the entity is partly inside
     */
    public Boolean isInside(Dimension dimension){
        if (dimension.getX()+dimension.getWidth()> this.dimension.getX() &&
            dimension.getX() < this.dimension.getX() + this.dimension.getWidth()) {
                if (dimension.getY() + dimension.getHeight()> this.dimension.getY() &&
                dimension.getY() < this.dimension.getY() + this.dimension.getHeight()) {
                    return true;
            }
        }
        return false;
    }

    public boolean blocksExplosion() {
        return false;
    }

    public void renderOn(GraphicsContext gc){
        gc.drawImage(image, dimension.getX(), dimension.getY(), dimension.getWidth(), dimension.getHeight());
    }
}
