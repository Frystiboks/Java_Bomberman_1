package MenuMVC.ChatMVC;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ChatModel {
    StringProperty chatLog;
    ListProperty<String> chatUsers = 
        new SimpleListProperty<>(FXCollections.observableArrayList());
    ObservableList<StringProperty> messages = FXCollections.observableArrayList();

    public ChatModel(){
        chatUsers.add("Torkil");
        chatUsers.add("Niels");
        chatUsers.add("Andreas");
        chatUsers.add("Tóra");
    }
}
