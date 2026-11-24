package MenuMVC.Game;

import java.util.ArrayList;

import MenuMVC.Game.Board.BoardController;
import MenuMVC.Game.Board.BoardGenerator;
import MenuMVC.Game.Canvas.CanvasController;
import MenuMVC.Game.Entities.Entity;
import MenuMVC.Game.Entities.Player;
import MenuMVC.Game.Entities.TimedEntity;
import MenuMVC.Game.Utilities.Position;
import javafx.scene.image.Image;

public class GameModel {
    
    public BoardController board;
    private BoardGenerator boardGenerator;

    public static Image wall = new Image("MenuMVC/Game/Canvas/assets/terrain/wall.png");
    public static Image player1 = new Image("MenuMVC/Game/Canvas/assets/players/blue_down_0.png");
    public static Image player2 = new Image("MenuMVC/Game/Canvas/assets/players/blue_down_0.png");
    public static Image grass = new Image("MenuMVC/Game/Canvas/assets/terrain/grass.png");
    public static Image crate = new Image("MenuMVC/Game/Canvas/assets/terrain/crate.png");
    public static Image bomb = new Image("MenuMVC\\Game\\Canvas\\assets\\bombs\\bomb_0.png");
    public static Image explosion = new Image("MenuMVC\\Game\\Canvas\\assets\\explosions\\explosion_3.png");

    public static Image capacity = new Image("MenuMVC\\Game\\Canvas\\assets\\powerups\\capacity.png");
    public static Image life = new Image("MenuMVC\\Game\\Canvas\\assets\\powerups\\life.png");
    public static Image range = new Image("MenuMVC\\Game\\Canvas\\assets\\powerups\\range.png");
    public static Image speed = new Image("MenuMVC\\Game\\Canvas\\assets\\powerups\\speed.png");

    private ArrayList<Entity> entitiesToAdd = new ArrayList<>();
    private ArrayList<Entity> entitiesToRemove = new ArrayList<>();
    public ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<TimedEntity> timedEntities = new ArrayList<>();

    private ArrayList<Player> players = new ArrayList<>();

    public GameModel(){
        this.board = new BoardController(entitiesToRemove, entitiesToAdd);

        boardGenerator = new BoardGenerator(this.board);
        boardGenerator.generate(entities, players);

        players.add(new Player(boardGenerator.getPlayer1SpawnPosition(), "player1", board, 0));
        players.add(new Player(boardGenerator.getPlayer2SpawnPosition(), "player2", board, 1));

        for (Entity player : players) {
            entities.add(player);
        }
    }

    public ArrayList<Entity> getEntitiesToAdd() {
        return entitiesToAdd;
    }

    public ArrayList<Entity> getEntitiesToRemove() {
        return entitiesToRemove;
    }

    public ArrayList<TimedEntity> getTimedEntities() {
        return timedEntities;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
