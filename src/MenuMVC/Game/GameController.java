package MenuMVC.Game;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import MenuMVC.Game.Canvas.CanvasController;
import MenuMVC.Game.Entities.Entity;
import MenuMVC.Game.Entities.Player;
import MenuMVC.Game.Entities.TimedEntity;
import MenuMVC.Game.Utilities.Dimension;
import MenuMVC.Game.Utilities.GameResult;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Region;

public class GameController {
    private AnimationTimer gameLoop;
    private GameModel model;
    private CanvasController canvas;
    private boolean gameOver = false;
    private GameResult gameResult;
    private ExecutorService gameExecutor;


    public GameController(){
        canvas = new CanvasController();
        initializeGameState();
    }

    public void initializeGameState(){
        model = new GameModel();
        gameOver = false;
        gameResult = null;
        gameExecutor = Executors.newSingleThreadExecutor();
    }

    public void setUpGame(){
        if (gameLoop != null) {
            gameLoop.stop();
        }
        initializeGameState();

        
        
        canvas.drawCanvas(model.entities, model.getPlayers());

        gameLoop = new AnimationTimer() {
            long lastTime = -1;
            @Override
            public void handle(long currentTime) {
                if (lastTime == -1) {
                    lastTime = currentTime;
                }

                double dt = (currentTime-lastTime)/1000000000.0;
                lastTime = currentTime;
                for (Player player : model.getPlayers()) {
                    if (!collisionDetection(player, dt)) {
                        update(player, dt);
                    }
                    player.updateInvincibility(dt);
                }
                

                processEntityInteractions();

                processTimedEntity(dt);
                
                // concurrency create a endgame check that returns a gameresult.
                Future<GameResult> resultFuture = gameExecutor.submit(() -> {
                    return checkGameOver();
                });

                processEntityChanges();

                canvas.drawCanvas(model.entities, model.getPlayers());


                try {
                    GameResult result = resultFuture.get();

                    if (result != null) {
                        endGame(result);
                        canvas.drawGameOver(result);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
        }
    };

        gameLoop.start();
        
    };

    private Boolean collisionDetection(Player player, double dt){
        for (Entity entity2 : model.entities) {
           if (entity2.isInside(player.getNextPosition(dt)) && entity2 != player){
                if (entity2.isOccupying(player)) {
                    if (!isMovingAway(player, entity2, dt)) {
                        return true;   
                    }
                }
           }
        }
        return false;
    }

    private boolean isMovingAway(Player player, Entity entity, double dt) {
        Dimension current = player.getDimension();
        Dimension next = player.getNextPosition(dt);
        Dimension obstacle = entity.getDimension();

        double currentDistanceX =
            current.getX() + current.getWidth() / 2 -
            (obstacle.getX() + obstacle.getWidth() / 2);

        double currentDistanceY =
            current.getY() + current.getHeight() / 2 -
            (obstacle.getY() + obstacle.getHeight() / 2);

        double nextDistanceX =
            next.getX() + next.getWidth() / 2 -
            (obstacle.getX() + obstacle.getWidth() / 2);

        double nextDistanceY =
            next.getY() + next.getHeight() / 2 -
            (obstacle.getY() + obstacle.getHeight() / 2);

        double currentDistance = Math.sqrt(
            currentDistanceX * currentDistanceX +
            currentDistanceY * currentDistanceY
        );

        double nextDistance = Math.sqrt(
            nextDistanceX * nextDistanceX +
            nextDistanceY * nextDistanceY
        );
        return nextDistance > currentDistance;
    }

    private void processEntityChanges() {

        // Remove entities
        for (Entity entity : model.getEntitiesToRemove()) {
            model.board.removeEntity(entity);
            model.entities.remove(entity);
            model.getTimedEntities().remove(entity);
        }

        model.getEntitiesToRemove().clear();

        // Add entities
        for (Entity entity : model.getEntitiesToAdd()) {
            model.entities.add(entity);
            if (entity instanceof TimedEntity) {
                model.getTimedEntities().add((TimedEntity)entity);
            }
        }

        model.getEntitiesToAdd().clear();
    }

    private void processTimedEntity(double dt) {

        // Remove entities
        for (TimedEntity entity : model.getTimedEntities()) {
            entity.tick(dt);
            if (entity.isExpired()) {
                entity.activate(model.getEntitiesToRemove(), model.getEntitiesToAdd());
            }
        }
    }

    private void processEntityInteractions() {
        for (int i = 0; i < model.entities.size(); i++) {
            Entity entity1 = model.entities.get(i);

            for (int j = i + 1; j < model.entities.size(); j++) {
                Entity entity2 = model.entities.get(j);

                if (entity1.isInside(entity2.getDimension())) {
                    entity1.interact(entity2);
                    entity2.interact(entity1);
                }
            }
        }
    }

    private void update(Player player, double dt){
        player.setPosition(dt);
    }

    private GameResult checkGameOver() {
        Player player1 = model.getPlayers().get(0);
        Player player2 = model.getPlayers().get(1);

        if (player1.isDead() && player2.isDead()) {
            return GameResult.DRAW;
        }

        if (player1.isDead()) {
            return GameResult.PLAYER_TWO_WINS;
        }

        if (player2.isDead()) {
            return GameResult.PLAYER_ONE_WINS;
        }

        return null;
    }

    private void endGame(GameResult result) {
        if (gameOver) {
            return;
        }

        gameOver = true;
        gameResult = result;

        gameLoop.stop();

        //canvas.drawGameOver(result);
    }

    public Region getCanvasView() {
        return canvas.getView(model.getPlayers().get(0), model.getPlayers().get(1), this::stopGame);
    }

    public void setMainMenu(Runnable setMainMenu){
        canvas.setMainMenu(setMainMenu);
    };

    public void stopGame() {
        if (gameLoop != null) {
            gameLoop.stop();
            gameLoop = null;
        }
        //shut off executor when ending the game or backing to main menu
        gameExecutor.shutdownNow();
    }


}
