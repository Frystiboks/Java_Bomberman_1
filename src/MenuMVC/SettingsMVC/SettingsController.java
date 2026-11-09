package MenuMVC.SettingsMVC;

import java.util.function.IntConsumer;

import javafx.scene.layout.Region;

public class SettingsController {
    private SettingsViewBuilder view;
    private SettingsModel model;

    public SettingsController(IntConsumer setBg, Runnable setMenu){
        this.view = new SettingsViewBuilder(setBg, setMenu);
        
    }

    public Region getView(){
        return view.build();
    }
}
