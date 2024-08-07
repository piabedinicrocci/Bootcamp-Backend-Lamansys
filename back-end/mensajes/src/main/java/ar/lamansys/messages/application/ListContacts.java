package ar.lamansys.messages.application;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import ar.lamansys.messages.domain.MessageStoredBo;
import ar.lamansys.messages.infrastructure.output.LogUtil;
import ar.lamansys.messages.infrastructure.output.MessageStorage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ListContacts {
    private final Supplier<String> userSessionStorage;
    private final MessageStorage messageStorage;

    public List<String> run() {
        String sessionUserId = userSessionStorage.get();

        List<String> contacts = messageStorage.findByContact(
                        sessionUserId
                )
                .map(extractContact(sessionUserId))
                .distinct()
                .collect(Collectors.toList());

        LogUtil.logResult(sessionUserId, contacts);
        return contacts;
    }

    private static Function<MessageStoredBo, String> extractContact(
            String sessionUserId
    ) {
        return m -> sessionUserId.equals(m.getOwnerId()) ?
                m.getTargetId() : m.getOwnerId();
    }
}
