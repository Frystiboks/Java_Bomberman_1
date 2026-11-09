package MenuMVC.MainMenuMVC;

import javafx.scene.Scene;
import javafx.scene.layout.Region;

public class MainMenuController {
    
    private MainMenuViewBuilder view;

    public MainMenuController(Runnable setMenu, Runnable setChat, Scene scene){
        MainMenuModel model = new MainMenuModel();
        this.view = new MainMenuViewBuilder(setMenu, setChat ,scene);
    }
    /**
     * Builds the view of the main menu and returns it
     * @return the main menu region.
     */
    public Region getView() {
        return view.build();
    }
}
