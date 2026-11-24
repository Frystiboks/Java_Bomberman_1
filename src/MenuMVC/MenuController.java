package MenuMVC;

import MenuMVC.Game.Canvas.CanvasController;
import MenuMVC.ChatMVC.ChatController;
import MenuMVC.MainMenuMVC.MainMenuController;
import MenuMVC.SettingsMVC.SettingsController;
import javafx.scene.Scene;
import javafx.scene.layout.Region;

public class MenuController {
    
    private MenuViewBuilder view;
    private MenuModel menuModel;
    private SettingsController settings;
    private MainMenuController mainMenu;
    private ChatController chat;
    private Runnable getMainMenu;
    //private CanvasController canvas;
    
    /**
     * Initializes the menu controller
     * @param scene used for dummy purposes for the play button
     */
    public MenuController(Scene scene){
        this.menuModel = new MenuModel();
        
        //this.canvas = new CanvasController(
          //  () -> view.setMenu(mainMenu.getView()));
        this.mainMenu = new MainMenuController(
            () -> view.setMenu(settings.getView()),
            () -> view.setMenu(chat.getView()),
            () -> view.setMenu(menuModel.game.getCanvasView()),
            () -> menuModel.game.setUpGame());
        this.view = new MenuViewBuilder(menuModel, mainMenu.getView());
        this.settings = new SettingsController(
            this::setBg,
            () -> view.setMenu(mainMenu.getView()));
        this.chat = new ChatController(
            () -> view.setMenu(mainMenu.getView()));
        
        menuModel.game.setMainMenu(() -> view.setMenu(mainMenu.getView()));

    }
    
    /**
     * Builds the view of the overal background region and returns it
     * @return the background region.
     */
    public Region getView(){
        return view.build();
    };

    /**
     * sets the background image by selecting it from a list of backgrounds.
     * @param n integer from 0 to 2
     */
    public void setBg(int n){
        if (n <= 2 && n >= 0 ){
        view.setBg(menuModel.bg[n]);
        }
    }
}
