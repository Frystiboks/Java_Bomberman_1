package MenuMVC;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;

public class MenuModel {
    public Image[] bgImg = {new Image("/MenuMVC/bgimg/bg1.png"), 
                            new Image("/MenuMVC/bgimg/bg2.png"), 
                            new Image("/MenuMVC/bgimg/bg3.png")};
    

    private final ObjectProperty<Region> menu = new SimpleObjectProperty<>();
}
