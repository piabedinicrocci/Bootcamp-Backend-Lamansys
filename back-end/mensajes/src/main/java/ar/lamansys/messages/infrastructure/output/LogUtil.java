package ar.lamansys.messages.infrastructure.output;

import java.util.List;
import java.util.stream.Collectors;

import ar.lamansys.messages.domain.ChatMessageBo;

public class LogUtil {
    private LogUtil() { }
    public static void logResult(String sessionUserId, String contactId, List<ChatMessageBo> chat) {
        System.out.println(String.format(
                "📬 Mensajes entre @%s <> @%s: %s",
                sessionUserId,
                contactId,
                chat.stream()
                        .map(ChatMessageBo::toString)
                        .collect(Collectors.joining(" | "))
        ));
    }

    public static void logResult(String sessionUserId, List<String> contacts) {
        System.out.println(String.format(
                "🪪 Contactos de @%s: %s",
                sessionUserId,
                String.join(", ", contacts)
        ));
    }

}
