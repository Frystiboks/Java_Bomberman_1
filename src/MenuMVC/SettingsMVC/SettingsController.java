package MenuMVC.SettingsMVC;

import java.util.function.IntConsumer;

import javafx.scene.layout.Region;

public class SettingsController {
    private SettingsViewBuilder view;
    private SettingsModel model;
    /**
     * Constructor for the controller
     * @param setBg sets the background image
     * @param setMenu sets the menu region
     */
    public SettingsController(IntConsumer setBg, Runnable setMenu){
        this.view = new SettingsViewBuilder(setBg, setMenu);
        
    }
    /**
     * Builds the view of the settings menu and returns it
     * @return the settings region.
     */
    public Region getView(){
        return view.build();
    }
}
