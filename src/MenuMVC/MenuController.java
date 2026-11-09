package MenuMVC;

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

    public MenuController(Scene scene){
        this.menuModel = new MenuModel();
        this.mainMenu = new MainMenuController(
            () -> view.setMenu(settings.getView()),
            () -> view.setMenu(chat.getView()),
            scene);
        this.view = new MenuViewBuilder(menuModel, mainMenu.getView());
        this.settings = new SettingsController(
            this::setBg,
            () -> view.setMenu(mainMenu.getView()));
        this.chat = new ChatController(
            () -> view.setMenu(mainMenu.getView()));
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
