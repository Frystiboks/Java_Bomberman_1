package MenuMVC;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Region;

public class MenuModel {
    private Image[] bgImg = {new Image("/MenuMVC/bgimg/bg1.png"), 
                            new Image("/MenuMVC/bgimg/bg2.png"), 
                            new Image("/MenuMVC/bgimg/bg3.png")};
    
    public BackgroundImage[] bg = {new BackgroundImage(bgImg[0],
                                    BackgroundRepeat.NO_REPEAT,
                                    BackgroundRepeat.NO_REPEAT, 
                                    BackgroundPosition.DEFAULT, 
                                    new BackgroundSize(
                                        100, 100,
                                        true, true,
                                        false, true
                                    )), 
                                new BackgroundImage(bgImg[1],
                                    BackgroundRepeat.NO_REPEAT,
                                    BackgroundRepeat.NO_REPEAT, 
                                    BackgroundPosition.DEFAULT, 
                                    new BackgroundSize(
                                        100, 100,
                                        true, true,
                                        false, true
                                    )),
                                new BackgroundImage(bgImg[2],
                                    BackgroundRepeat.NO_REPEAT,
                                    BackgroundRepeat.NO_REPEAT, 
                                    BackgroundPosition.DEFAULT, 
                                    new BackgroundSize(
                                        100, 100,
                                        true, true,
                                        false, true
                                    )),};

    public ObjectProperty<Background> background =
        new SimpleObjectProperty<>(new Background(bg[0]));


    public final ObjectProperty<Region> menu = new SimpleObjectProperty<>();
}
