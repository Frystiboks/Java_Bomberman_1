package MenuMVC.MainMenuMVC;

import javafx.scene.Scene;
import javafx.scene.layout.Region;

public class MainMenuController {
    
    private MainMenuViewBuilder view;

    /**
     * initializes the controller
     * @param setSettings used to set the settings view
     * @param setChat used to set the chat view
     * @param scene used to set a dummy view when play button is pressed
     */
    public MainMenuController(Runnable setSettings, Runnable setChat, Scene scene){
        MainMenuModel model = new MainMenuModel();
        this.view = new MainMenuViewBuilder(setSettings, setChat ,scene);
    }
    /**
     * Builds the view of the main menu and returns it
     * @return the main menu region.
     */
    public Region getView() {
        return view.build();
    }
}
