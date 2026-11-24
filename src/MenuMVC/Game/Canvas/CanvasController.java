package MenuMVC.Game.Canvas;

import java.util.ArrayList;
import java.util.function.Consumer;

import MenuMVC.Game.Entities.Entity;
import MenuMVC.Game.Entities.Player;
import MenuMVC.Game.Utilities.GameResult;
import MenuMVC.Game.Utilities.PlayerControls;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Region;

public class CanvasController {
    CanvasViewBuilder view;

    public CanvasController(){
        view = new CanvasViewBuilder();
    }


    public Region getView(Player player1, Player player2, Runnable stopGame){
        return view.build(player1, player2, stopGame);
    }

    public GraphicsContext getGc(){
        return view.getGc();
    }

    public void drawCanvas(ArrayList<Entity> entities, ArrayList<Player> players){
        view.drawCanvas(entities, players);
    };

    public void drawGameOver(GameResult gameResult){
        view.drawGameOver(gameResult);
    }

    public void setEntity(Entity entity){
        //todo
        view.setEntity(entity);

    }

    public void setMainMenu(Runnable setMainMenu){
        view.setMainMenu(setMainMenu);
    }
}
