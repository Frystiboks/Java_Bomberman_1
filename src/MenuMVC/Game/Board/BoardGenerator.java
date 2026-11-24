package MenuMVC.Game.Board;

import java.util.ArrayList;

import MenuMVC.Game.Entities.DestructibleWall;
import MenuMVC.Game.Entities.Entity;
import MenuMVC.Game.Entities.IndestructibleWall;
import MenuMVC.Game.Entities.Player;
import MenuMVC.Game.Utilities.Position;

public class BoardGenerator {

    private final int GRID_SIZE;
    private final int TILE_SIZE;

    private BoardController board;

    public BoardGenerator(BoardController board) {
        this.board = board;
        GRID_SIZE = board.getGridSize();
        TILE_SIZE = board.getTILE_SIZE();

    }

    public void generate(ArrayList<Entity> entities, ArrayList<Player> players) {

        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {

                Position position = new Position(
                    x * TILE_SIZE,
                    y * TILE_SIZE
                );

                // Outer perimeter
                if (isPerimeter(x, y)) {
                    addIndestructibleWall(entities, position);
                }

                // Interior checkerboard
                else if (isCheckerboardWall(x, y)) {
                    addIndestructibleWall(entities, position);
                }

                // Destructible walls
                else if (shouldPlaceCrate(x, y)) {
                    if (!isSpawnArea(x, y)) {
                        addDestructibleWall(entities, position);
                    }
                }
            }
        }
    }

    private boolean isPerimeter(int x, int y) {
        return x == 0 || x == GRID_SIZE - 1 ||
               y == 0 || y == GRID_SIZE - 1;
    }

    private boolean isCheckerboardWall(int x, int y) {
        return x % 2 == 0 && y % 2 == 0;
    }

    private boolean shouldPlaceCrate(int x, int y) {
        return (x + y) % 2 == 1;
    }

    private boolean isSpawnArea(int x, int y) {

        // Player 1: Bottom-left
        boolean player1Area =
            x <= 2 && y >= GRID_SIZE - 4;

        // Player 2: Top-right
        boolean player2Area =
            x >= GRID_SIZE - 3 && y <= 2;

        return player1Area || player2Area;
    }

    private void addIndestructibleWall(
            ArrayList<Entity> entities,
            Position position
    ) {
        IndestructibleWall wall =
            new IndestructibleWall(position);

        entities.add(wall);
        board.placeEntity(wall);
    }

    private void addDestructibleWall(
            ArrayList<Entity> entities,
            Position position
    ) {
        DestructibleWall wall =
            new DestructibleWall(position);

        entities.add(wall);
        board.placeEntity(wall);
    }

    public Position getPlayer1SpawnPosition() {
        return new Position(TILE_SIZE, (GRID_SIZE - 2) * TILE_SIZE);
    }

    public Position getPlayer2SpawnPosition() {
        return new Position((GRID_SIZE - 2) * TILE_SIZE, TILE_SIZE);
    }
}
