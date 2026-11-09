package MenuMVC.ChatMVC;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ChatViewBuilder {
    private Runnable setMenu;
    private VBox chatLog;
    private ObservableList<StringProperty> messages;

    public ChatViewBuilder(Runnable setMenu, ObservableList<StringProperty> messages){
        this.setMenu = setMenu;
        this.messages = messages;
    }

    public Region build() {
        BorderPane bp = new BorderPane();
        VBox chatContainer = new VBox(10);
        VBox userList = new VBox(200);
        userList.setStyle("-fx-background-color: white;");
        userList.setPadding(new Insets(10));
        userList.setPrefSize(230, 300);

        chatLog = new VBox(10);
        chatLog.setStyle("-fx-background-color: white;");
        chatLog.setPadding(new Insets(10));
        chatLog.setPrefSize(230, 300);

        for (int i = 0; i < messages.size(); i++) {
            pringMessages(messages.get(i));
            //System.out.println(messages.size());
        }

        HBox userChatContainer = new HBox(10);
        userChatContainer.setAlignment(Pos.CENTER);
        userChatContainer.getChildren().addAll(userList, chatLog);

        TextField input = new TextField();
        Button send = new Button("Send");
        send.setOnAction(_ -> addMessage(new SimpleStringProperty(input.getText())));
        HBox inputBox = new HBox(10, input, send);
        
        chatContainer.getChildren().addAll(userChatContainer, inputBox);
        bp.setCenter(chatContainer);

        Button back = new Button("Back");
        back.setOnAction(_ -> setMenu.run());
        bp.setBottom(back);
        return bp;
    }

private void pringMessages(StringProperty message){
    Label label = new Label();

    label.textProperty().bind(message);

    chatLog.getChildren().add(label);
}

private void addMessage(StringProperty message) {
    Label label = new Label();

    label.textProperty().bind(message);

    chatLog.getChildren().add(label);
    messages.add(message);
}


}
