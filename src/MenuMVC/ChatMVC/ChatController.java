package MenuMVC.ChatMVC;

import javafx.scene.layout.Region;

public class ChatController {
    private ChatModel model;
    private ChatViewBuilder view;

    public ChatController(Runnable setMenu){
        model = new ChatModel();
        view = new ChatViewBuilder(setMenu, model.messages);
    }

    public Region getView(){
        return view.build();
    }
}
