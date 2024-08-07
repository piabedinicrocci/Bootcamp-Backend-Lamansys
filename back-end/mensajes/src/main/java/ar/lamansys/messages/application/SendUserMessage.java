package ar.lamansys.messages.application;

import ar.lamansys.messages.application.exception.UserNotExistsException;
import ar.lamansys.messages.application.exception.UserSessionNotExists;
import ar.lamansys.messages.domain.MessageMapper;
import ar.lamansys.messages.domain.NewMessageBo;
import ar.lamansys.messages.infrastructure.output.MessageStorage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SendUserMessage {
    private final GetUserSession getUserSession;
    private final MessageStorage messageStorage;
    private final AssertUserExists assertUserExists;

    public void run(NewMessageBo newMessage) throws UserNotExistsException, UserSessionNotExists {
        assertUserExists.run(newMessage.getTargetId());
        var message = MessageMapper.buildMessage(
                getUserSession.run(),
                newMessage
        );
        messageStorage.save(message);
    }

}
