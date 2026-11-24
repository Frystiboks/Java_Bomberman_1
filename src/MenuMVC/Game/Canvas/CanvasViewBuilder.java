package MenuMVC.Game.Canvas;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

import MenuMVC.Game.Entities.Entity;
import MenuMVC.Game.Entities.Player;
import MenuMVC.Game.Utilities.GameResult;
import MenuMVC.Game.Utilities.PlayerControls;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CanvasViewBuilder {
    private GraphicsContext gc;
    private Canvas canvas;
    private Runnable setMainMenu;


    public CanvasViewBuilder(){


        canvas = new Canvas(512, 512);
        gc = canvas.getGraphicsContext2D();
    }

    public void setMainMenu(Runnable setMainMenu){
        this.setMainMenu = setMainMenu;
    }

    public Region build(Player player1, Player player2, Runnable stopGame){
        BorderPane bp = new BorderPane();
        Button back = new Button("Back to main menu");
        back.setOnAction(_ -> {setMainMenu.run();
                                stopGame.run();});
        //Background bg = new Background(new BackgroundFill(
        //Color.GREEN,
        //CornerRadii.EMPTY,
        //Insets.EMPTY
        //));
        StackPane stackPane = new StackPane();
        Label p1Health = new Label();
        Label p2Health = new Label();

        p1Health.setTextFill(Color.WHITE);
        p2Health.setTextFill(Color.WHITE);
        p1Health.textProperty().bind(
            Bindings.concat("Player 1: ", player1.healthProperty(), " HP")
        );

        p2Health.textProperty().bind(
            Bindings.concat("Player 2: ", player2.healthProperty(), " HP")
        );
        //stackPane.backgroundProperty().bind(new SimpleObjectProperty<Background>(bg));
        
        stackPane.getChildren().add(canvas);
        //canvas.widthProperty().bind(stackPane.widthProperty());
        //canvas.heightProperty().bind(stackPane.heightProperty());
        bp.setCenter(stackPane);
        bp.setLeft(p1Health);
        bp.setRight(p2Health);
        bp.setBottom(back);
        //stackPane.widthProperty().addListener(o -> drawCanvas());
        //stackPane.heightProperty().addListener(o -> drawCanvas());

        bp.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W -> player1.controls.setUp();
                case S -> player1.controls.setDown();
                case A -> player1.controls.setLeft();
                case D -> player1.controls.setRight();
                case I -> player2.controls.setUp();
                case K -> player2.controls.setDown();
                case J -> player2.controls.setLeft();
                case L -> player2.controls.setRight();
                //case  -> System.out.println("Move up");
                default -> {}
            }
        });

        bp.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case W -> player1.controls.releaseUp();
                case S -> player1.controls.releaseDown();
                case A -> player1.controls.releaseLeft();
                case D -> player1.controls.releaseRight();
                case I -> player2.controls.releaseUp();
                case K -> player2.controls.releaseDown();
                case J -> player2.controls.releaseLeft();
                case L -> player2.controls.releaseRight();
                case KeyCode.SPACE -> player1.setBomb();
                case KeyCode.ENTER -> player2.setBomb();
                default -> {}
            }
        });
        
        bp.setFocusTraversable(true);
        bp.requestFocus();
        return bp;
    }


    public void drawCanvas(ArrayList<Entity> entities, ArrayList<Player> players){
        gc.setFill(Color.GREEN);
        gc.fillRect(0, 0, canvas.widthProperty().get(), canvas.widthProperty().get());
        
        for (Entity entity : entities) {
            entity.renderOn(gc);
        }
        for (Player player : players) {
            player.renderOn(gc);
        }
    }

    public void drawGameOver(GameResult result) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.color(0, 0, 0, 0.7));
        gc.fillRect(0, 0, 512, 512);

        gc.setFill(Color.WHITE);
        gc.setFont(new Font(32));

        String message = switch (result) {
            case PLAYER_ONE_WINS -> "Player 1 Wins!";
            case PLAYER_TWO_WINS -> "Player 2 Wins!";
            case DRAW -> "It's a Draw!";
        };

        gc.fillText(message, 150, 250);
    }

    public GraphicsContext getGc(){
        return gc;
    }

    public void setEntity(Entity entity){
        gc.drawImage(entity.getimage(), 20, 20);
    }
}
