package ar.lamansys.messages;

import java.util.function.Supplier;

import ar.lamansys.messages.application.FetchUserChat;
import ar.lamansys.messages.application.ListContacts;
import ar.lamansys.messages.application.SendUserMessage;
import ar.lamansys.messages.infrastructure.output.MessageStorage;
import ar.lamansys.messages.infrastructure.output.impl.MessageStorageListImpl;

public class MessageAppConfiguration {
    public static MessageApp newBean() {
        Supplier<String> userSession = () -> "buyer";
        MessageStorage repository = new MessageStorageListImpl();
        return new MessageApp(
                new SendUserMessage(userSession, repository),
                new FetchUserChat(userSession, repository),
                new ListContacts(userSession, repository)
        );
    }
}

