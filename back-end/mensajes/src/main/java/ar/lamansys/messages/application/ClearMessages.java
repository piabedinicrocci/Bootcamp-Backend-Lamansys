package ar.lamansys.messages.application;

import ar.lamansys.messages.infrastructure.output.MessageStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ClearMessages {

    private MessageStorage messageStorage;

    public void run() {
        messageStorage.deleteAll();
    }
}
