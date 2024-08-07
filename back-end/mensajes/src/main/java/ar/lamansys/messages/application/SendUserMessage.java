package ar.lamansys.messages.application;

import java.util.function.Supplier;

import ar.lamansys.messages.domain.MessageMapper;
import ar.lamansys.messages.domain.NewMessageBo;
import ar.lamansys.messages.infrastructure.output.MessageStorage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SendUserMessage {
    private final Supplier<String> userSessionStorage;
    private final MessageStorage messageStorage;

    public void run(NewMessageBo newMessage) {
        var message = MessageMapper.buildMessage(
                userSessionStorage.get(),
                newMessage
        );
        messageStorage.save(message);
    }

}
