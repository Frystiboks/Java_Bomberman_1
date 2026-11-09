package MenuMVC.ChatMVC;

import javafx.scene.layout.Region;

public class ChatController {
    private ChatModel model;
    private ChatViewBuilder view;
    /**
     * Initializes the controller
     * @param setMenu sets the desired view for the back button
     */
    public ChatController(Runnable setMenu){
        model = new ChatModel();
        view = new ChatViewBuilder(setMenu, model.messages, model.chatUsers);
    }

    /**
     * Builds the view of the chat menu and returns it
     * @return the chat region.
     */
    public Region getView(){
        return view.build();
    }
}
