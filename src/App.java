import MenuMVC.MenuController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;


public class App extends Application{
    public static void main(String[] args) throws Exception {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Give the scene some root, width and height
        // Perhaps create a class that builds the root for you
        int width = 500;
        int height = 500;


        Scene scene = new Scene(new Group(), width, height);
        MenuController menuController = new MenuController(scene);
        Region ROOT = menuController.getView();

        scene.setRoot(ROOT);
        // Put a title on the window
        stage.setTitle("My Addition Calculator");
        stage.setScene(scene);
        stage.show();
        
    }
}
