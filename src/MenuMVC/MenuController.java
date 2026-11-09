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
        this.view = new MenuViewBuilder(menuModel.bgImg[0], scene, mainMenu.getView());
        this.settings = new SettingsController(
            this::setBg,
            () -> view.setMenu(mainMenu.getView()));
        this.chat = new ChatController(
            () -> view.setMenu(mainMenu.getView()));
    }

    public Region getView(){
        return view.build();
    };

    public void setBg(int n){
        if (n <= 2 && n >= 0 ){
        view.setBg(menuModel.bgImg[n]);
        }
    }
}
