package MenuMVC.MainMenuMVC;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Region;

public class MainMenuController {
    
    private MainMenuViewBuilder view;
    private MainMenuModel model;

    /**
     * initializes the controller
     * @param setSettings used to set the settings view
     * @param setChat used to set the chat view
     * @param scene used to set a dummy view when play button is pressed
     */
    public MainMenuController(Runnable setSettings, Runnable setChat, Runnable setCanvas, Runnable setUpGame){
        model = new MainMenuModel();
        this.view = new MainMenuViewBuilder(setSettings, setChat , setCanvas, setUpGame);
    }
    /**
     * Builds the view of the main menu and returns it
     * @return the main menu region.
     */
    public Region getView() {
        return view.build();
    }
}
