package MenuMVC.MainMenuMVC;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class MainMenuViewBuilder {
    private Button play = new Button("Play");
    private Button settings = new Button("Settings");
    private Button chat = new Button("Chat");
    private Button exit = new Button("Exit");
    private Scene scene;

    private final Runnable setSettings;
    private final Runnable setChat;
    
    public MainMenuViewBuilder(Runnable setSettings, Runnable setChat, Scene scene){
        this.setSettings = setSettings;
        this.setChat = setChat;
        this.scene = scene;
    }

    public Region build() {
        //create Vbox and set spacing to 10px
        VBox menuBox = new VBox(10);
        menuBox.setAlignment(Pos.CENTER); // Center buttons horizontally and vertically

        play.setOnAction(_ -> scene.setRoot(new Group()));
        settings.setOnAction(_ -> setSettings.run());
        chat.setOnAction(_ -> setChat.run());
        exit.setOnAction(_ -> Platform.exit());

        menuBox.getChildren().addAll(play, settings, chat, exit);

        return menuBox;
    }
}
