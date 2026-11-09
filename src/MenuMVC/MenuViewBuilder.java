package MenuMVC;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class MenuViewBuilder {
    private ImageView bgImgView = new ImageView();
    private final ObjectProperty<Region> menu = new SimpleObjectProperty<>();
    private Region startRegion;

    public MenuViewBuilder(Image bgImg, Scene scene, Region startRegion){
        this.bgImgView.setImage(bgImg);

        this.startRegion = startRegion;

        // Bind size to scene width/height
        bgImgView.fitWidthProperty().bind(scene.widthProperty());
        bgImgView.fitHeightProperty().bind(scene.heightProperty());
    }
    
    public Region build() {
        BorderPane bp = new BorderPane();
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(bgImgView);

        menu.addListener((obs, oldMenu, newMenu) -> {
            if (oldMenu != null) {
                stackPane.getChildren().remove(oldMenu);
            }
            if (newMenu != null) {
                stackPane.getChildren().add(newMenu);
            }
        });

        setMenu(startRegion);
        bp.setCenter(stackPane);

        return bp;
    }

    public void setMenu(Region region){
        menu.set(region);
    }

    public void setBg(Image bgImg){
        bgImgView.setImage(bgImg);
    }
}
