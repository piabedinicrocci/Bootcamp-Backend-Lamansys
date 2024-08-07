package ar.lamansys.messages;

import java.util.List;

import ar.lamansys.messages.application.FetchUserChat;
import ar.lamansys.messages.application.ListContacts;
import ar.lamansys.messages.application.SendUserMessage;
import ar.lamansys.messages.domain.ChatMessageBo;
import ar.lamansys.messages.domain.NewMessageBo;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MessageApp  {
    private final SendUserMessage sendUserMessage;
    private final FetchUserChat fetchUserChat;
    private final ListContacts listContacts;

    public void sendUserMessage(NewMessageBo newMessage) {
        sendUserMessage.run(newMessage);
    }

    public List<ChatMessageBo> fetchUserChat(String contactId) {
        return fetchUserChat.run(contactId);
    }

    public List<String> listContacts() {
        return listContacts.run();
    }

}
