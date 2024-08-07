package ar.lamansys.messages.application;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import ar.lamansys.messages.domain.ChatMessageBo;
import ar.lamansys.messages.domain.MessageMapper;
import ar.lamansys.messages.infrastructure.output.LogUtil;
import ar.lamansys.messages.infrastructure.output.MessageStorage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FetchUserChat {
    private final Supplier<String> userSessionStorage;
    private final MessageStorage messageStorage;

    public List<ChatMessageBo> run(String contactId) {
        String sessionUserId = userSessionStorage.get();

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
