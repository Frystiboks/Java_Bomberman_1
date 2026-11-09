package MenuMVC.ChatMVC;

import javafx.beans.property.ListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ChatModel {
    StringProperty chatLog;
    ListProperty<String> chatUsers;
    ObservableList<StringProperty> messages = FXCollections.observableArrayList();

}
