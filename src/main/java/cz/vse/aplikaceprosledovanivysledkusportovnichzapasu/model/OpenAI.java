package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.model;

import com.lilittlecat.chatgpt.offical.ChatGPT;
import com.lilittlecat.chatgpt.offical.entity.Message;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.UserService;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class OpenAI{

    private static final String API_KEY = "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";
    private static ChatGPT chatGPT = new ChatGPT(API_KEY);
    private static UserService userService = new UserServiceImpl();

    public static String useMessages(User user){
        List<Message> messages = new ArrayList<>();
        messages.add(Message.builder()
                .role("system")
                .content("You are a tool for suggesting teams to follow based on preferences of our users, our users will give you list of their favourite teams and you will just list some other teams the person might want to follow and you'll separate the team names with a ,")
                .build());

        messages.add(Message.builder()
                .role("user")
                .content("leagues = " + userService.getTextOfFavTeams(user))
                .build());
        return chatGPT.ask(messages);
    }
}
