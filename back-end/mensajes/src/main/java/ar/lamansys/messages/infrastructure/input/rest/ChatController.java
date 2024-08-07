package ar.lamansys.messages.infrastructure.input.rest;

import ar.lamansys.messages.application.FetchUserChat;
import ar.lamansys.messages.application.exception.UserNotExistsException;
import ar.lamansys.messages.application.exception.UserSessionNotExists;
import ar.lamansys.messages.domain.ChatMessageBo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final FetchUserChat fetchUserChat;

    @GetMapping("/{contactId}")
    public List<ChatMessageBo> fetchUserChat(@PathVariable String contactId) throws UserSessionNotExists, UserNotExistsException {
        return fetchUserChat.run(contactId);
    }
}
