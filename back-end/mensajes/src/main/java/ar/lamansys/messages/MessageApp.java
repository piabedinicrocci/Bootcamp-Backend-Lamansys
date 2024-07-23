package ar.lamansys.messages;

import java.util.List;

import ar.lamansys.messages.application.AddUser;
import ar.lamansys.messages.application.ClearData;
import ar.lamansys.messages.application.FetchUserChat;
import ar.lamansys.messages.application.GetUserSession;
import ar.lamansys.messages.application.ListContacts;
import ar.lamansys.messages.application.SendUserMessage;
import ar.lamansys.messages.application.SetUserSession;
import ar.lamansys.messages.application.exception.UserExistsException;
import ar.lamansys.messages.application.exception.UserNotExistsException;
import ar.lamansys.messages.application.exception.UserSessionNotExists;
import ar.lamansys.messages.domain.ChatMessageBo;
import ar.lamansys.messages.domain.NewMessageBo;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@AllArgsConstructor
@SpringBootApplication
public class MessageApp  {

    private final AddUser addUser;
    private final ClearData clearData;
    private final FetchUserChat fetchUserChat;
    private final GetUserSession getUserSession;
    private final ListContacts listContacts;
    private final SendUserMessage sendUserMessage;
    private final SetUserSession setUserSession;

    public static void main(String[] args) {
        SpringApplication.run(MessageApp.class, args);
    }

    public void sendUserMessage(NewMessageBo newMessage) throws UserNotExistsException, UserSessionNotExists {
        sendUserMessage.run(newMessage);
    }

    public List<ChatMessageBo> fetchUserChat(String contactId) throws UserSessionNotExists, UserNotExistsException {
        return fetchUserChat.run(contactId);
    }

    public List<String> listContacts() throws UserSessionNotExists {
        return listContacts.run();
    }

    public void addUser(String userId) throws UserExistsException {
        addUser.run(userId);
    }

    public void clearData() {
        clearData.run();
    }

    public String getUserSession() throws UserSessionNotExists {
        return getUserSession.run();
    }

    public void setUserSession(String userId) throws UserNotExistsException {
        setUserSession.run(userId);
    }

}
