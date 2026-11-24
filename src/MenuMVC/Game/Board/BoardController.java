package MenuMVC.Game.Board;

import java.util.ArrayList;

import MenuMVC.Game.Entities.Entity;
import MenuMVC.Game.Utilities.Position;

public class BoardController {
    private BoardModel model;

    public BoardController(ArrayList<Entity> entitiesToRemove, ArrayList<Entity> entitiesToAdd){
        this.model = new BoardModel();
        model.entitiesToAdd = entitiesToAdd;
        model.entitiesToRemove = entitiesToRemove;
    }

    public void placeEntity(Entity entity) {
        int gridX = convertPositionToGrid(entity.getDimension().getX());
        int gridY = convertPositionToGrid(entity.getDimension().getY());

        // Ensure indices are within array bounds
        gridX = Math.max(0, Math.min(gridX, model.GRID_SIZE - 1));
        gridY = Math.max(0, Math.min(gridY, model.GRID_SIZE - 1));

        model.getBoardEntities()[gridX][gridY] = entity;
    }

    public void removeEntity(Entity entity) {
        int x = convertPositionToGrid(entity.getDimension().getX());
        int y = convertPositionToGrid(entity.getDimension().getY());

        if (model.getBoardEntities()[x][y] == entity) {
            model.getBoardEntities()[x][y] = null;
        }
    }

    public Position getTileCenter(int gridX, int gridY) {
        double x = gridX * model.TILE_SIZE + model.TILE_SIZE / 2.0;
        double y = gridY * model.TILE_SIZE + model.TILE_SIZE / 2.0;

        return new Position(x, y);
    }

    public Position getCenteredPosition(int gridX, int gridY, int width, int height) {
        Position center = getTileCenter(gridX, gridY);

        double x = center.getX() - width / 2.0;
        double y = center.getY() - height / 2.0;

        return new Position(x, y);
    }

    public Position getGridPosition(Entity entity) {
        int gridX = convertPositionToGrid(entity.getDimension().getX());
        int gridY = convertPositionToGrid(entity.getDimension().getY());

        return new Position(gridX, gridY);
    }
    
    public Position getTilePosition(int gridX, int gridY) {
        double x = gridX * model.TILE_SIZE;
        double y = gridY * model.TILE_SIZE;

        return new Position(x, y);
    }

    public Entity getEntityAt(int gridX, int gridY) {
        if (gridX < 0 || gridX >= model.GRID_SIZE ||
            gridY < 0 || gridY >= model.GRID_SIZE) {
            return null;
        }

        return model.getBoardEntities()[gridX][gridY];
    }

    public int getGridSize(){
        return model.getGRID_SIZE();
    }

    public int convertPositionToGrid(double coordinate){
        return (int) (coordinate * model.GRID_SIZE / model.BOARD_SIZE);
    }

    public ArrayList<Entity> getEntitiesToAdd(){
        return model.entitiesToAdd;
    }

    public ArrayList<Entity> getEntitiesToRemove(){
        return model.entitiesToRemove;
    }

    public int getTILE_SIZE(){
        return model.getTILE_SIZE();
    }
}
