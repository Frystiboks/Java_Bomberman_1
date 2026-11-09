package MenuMVC.ChatMVC;


import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ChatViewBuilder {
    private Runnable setMenu;
    private VBox chatLog;
    private ObservableList<StringProperty> messages;
    private ListProperty<String> usernames;
    

    public ChatViewBuilder(Runnable setMenu, ObservableList<StringProperty> messages, ListProperty<String> usernames){
        this.setMenu = setMenu;
        this.usernames = usernames;
        this.messages = messages;
    }

    /**
     * Builds the chat menu
     * @return a region containing the chat menu
     */
    public Region build() {
        BorderPane bp = new BorderPane();
        VBox chatContainer = new VBox(10);
        VBox userWrapper = buildUserLog();
        VBox chatWrapper = buildChatLog();
        for (int i = 0; i < messages.size(); i++) {
            setMessages(messages.get(i));
        }

        HBox userChatContainer = new HBox(10);
        userChatContainer.setAlignment(Pos.CENTER);
        userChatContainer.getChildren().addAll(userWrapper, chatWrapper);

        TextField input = new TextField();
        Button send = new Button("Send");
        send.setOnAction(_ -> addMessage(new SimpleStringProperty(input.getText())));
        HBox inputBox = new HBox(10, input, send);
        inputBox.setAlignment(Pos.CENTER);

        chatContainer.getChildren().addAll(userChatContainer, inputBox);
        bp.setCenter(chatContainer);

        Button back = new Button("Back");
        back.setOnAction(_ -> setMenu.run());
        bp.setBottom(back);
        return bp;
    }

private VBox buildChatLog(){
    VBox chatWrapper = new VBox(10);
    ScrollPane chatScrollPane = new ScrollPane();
    chatLog = new VBox(10);
    chatLog.setStyle("-fx-background-color: white;");
    chatLog.setPadding(new Insets(10));
    chatLog.setPrefSize(230, 300);
    chatScrollPane.setContent(chatLog);

    Label chatLabel = new Label("Chat Log");
    chatLabel.setStyle(
            "-fx-font-family: 'Arial';" +
            "-fx-font-size: 20px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;"
        );
    chatWrapper.getChildren().addAll(chatLabel, chatScrollPane);
    return chatWrapper;
}

private VBox buildUserLog(){
    VBox userWrapper = new VBox(10);
    ListView<String> userListView = new ListView<>();
    userListView.setItems(usernames.get());
    VBox userList = new VBox(200);
    userList.setStyle("-fx-background-color: white;");
    userList.setPadding(new Insets(10));
    userList.setPrefSize(230, 300);
    userList.getChildren().add(userListView);

    Label userLabel = new Label("User List");
    userLabel.setStyle(
        "-fx-font-family: 'Arial';" +
        "-fx-font-size: 20px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: white;"
        );

    userWrapper.getChildren().addAll(userLabel, userList);
    return userWrapper;
}

private void setMessages(StringProperty message){
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
