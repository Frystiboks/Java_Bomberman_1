package MenuMVC.SettingsMVC;

import java.util.function.IntConsumer;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class SettingsViewBuilder {
    private ComboBox<String> bgDropdown = new ComboBox<>();
    private ComboBox<String> arenaDropdown = new ComboBox<>();

    private Button back = new Button("Back");

    private final IntConsumer setBg;
    private final Runnable setMenu;
    
    SettingsViewBuilder(IntConsumer setBg, Runnable setMenu){
        this.setBg = setBg;
        this.setMenu = setMenu;
    }

    /**
     * Builds the settings region
     * @return a Region representings the settings
     */
    public Region build() {
        //create Vbox and set spacing to 10px
        VBox menuBox = new VBox(10);
        menuBox.setAlignment(Pos.CENTER); // Center buttons horizontally and vertically

        bgDropdown.getItems().setAll(
            "Set Background 1",
            "Set Background 2",
            "Set Background 3"
        );

        arenaDropdown.getItems().setAll(
            "Set Arena 1",
            "Set Arena 2",
            "Set Arena 3"
        );

        bgDropdown.setPromptText("Select Background");
        arenaDropdown.setPromptText("Select Arena");

        bgDropdown.setOnAction(_ -> {
            int selectedIndex = bgDropdown.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                setBg.accept(selectedIndex);
            }
        });

        back.setOnAction(_ -> setMenu.run());

        menuBox.getChildren().addAll(bgDropdown, arenaDropdown, back);



        return menuBox;
    }
}
