package MenuMVC.Game.Board;

import java.util.ArrayList;

import MenuMVC.Game.Entities.Entity;

public class BoardModel {
    public final int GRID_SIZE = 16;
    public final int TILE_SIZE = 32;
    public final int BOARD_SIZE = GRID_SIZE * TILE_SIZE;
    private Entity[][] boardEntities = new Entity[GRID_SIZE][GRID_SIZE]; 
    public ArrayList<Entity> entitiesToRemove;
    public ArrayList<Entity> entitiesToAdd;

    public Entity[][] getBoardEntities() {
        return boardEntities;
    }

    public int getGRID_SIZE() {
        return GRID_SIZE;
    }

    public int getTILE_SIZE() {
        return TILE_SIZE;
    }
}
