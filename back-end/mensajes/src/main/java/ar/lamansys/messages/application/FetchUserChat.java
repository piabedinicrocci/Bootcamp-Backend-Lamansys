package ar.lamansys.messages.application;

import java.util.List;
import java.util.stream.Collectors;

import ar.lamansys.messages.application.exception.UserNotExistsException;
import ar.lamansys.messages.application.exception.UserSessionNotExists;
import ar.lamansys.messages.domain.ChatMessageBo;
import ar.lamansys.messages.domain.MessageMapper;
import ar.lamansys.messages.infrastructure.output.LogUtil;
import ar.lamansys.messages.infrastructure.output.MessageStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FetchUserChat {
    private final GetUserSession getUserSession;
    private final MessageStorage messageStorage;
    private final AssertUserExists assertUserExists;

    public List<ChatMessageBo> run(String contactId) throws UserSessionNotExists, UserNotExistsException {
        String sessionUserId = getUserSession.run();

        assertUserExists.run(contactId);

        List<ChatMessageBo> chat = messageStorage.findBetween(
                    sessionUserId, contactId
                )
                .map(
                    m -> MessageMapper.buildChatMessageBo(sessionUserId, m)
                )
                .collect(Collectors.toList());

        LogUtil.logResult(sessionUserId, contactId, chat);
        return chat;
    }
}
