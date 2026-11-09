package MenuMVC;

import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class MenuViewBuilder {
    private Region startRegion;
    private MenuModel model;
    /**
     * Initializes the MenuViewBuilder Class
     * @param model the model containing all data fields
     * @param startRegion the region used to create the first view.
     */
    public MenuViewBuilder(MenuModel model, Region startRegion){
        this.model = model;
        this.startRegion = startRegion;

    }
    /**
     * Builds the root view
     * @return a border pane as a region
     */
    public Region build() {
        BorderPane bp = new BorderPane();
        bp.backgroundProperty().bind(model.background);
        StackPane stackPane = new StackPane();

        model.menu.addListener((obs, oldMenu, newMenu) -> {
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
    /**
     * sets the menu region to the region in the parameter
     * @param region that replaces the menu
     */
    public void setMenu(Region region){
        model.menu.set(region);
    }
    /**
     * sets the background
     * @param bgImg a background image
     */
    public void setBg(BackgroundImage bgImg){
        model.background.set(new Background(bgImg));
    }
}
