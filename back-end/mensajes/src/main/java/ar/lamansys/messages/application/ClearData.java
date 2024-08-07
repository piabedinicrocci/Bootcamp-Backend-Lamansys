package ar.lamansys.messages.application;

import ar.lamansys.messages.infrastructure.output.MessageStorage;
import ar.lamansys.messages.infrastructure.output.UserSessionStorage;
import ar.lamansys.messages.infrastructure.output.UserStorage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClearData {

    private final MessageStorage messageStorage;
    private final UserStorage userStorage;
    private final UserSessionStorage userSessionStorage;

    public void run(){
        messageStorage.deleteAll();
        userStorage.deleteAll();
        userSessionStorage.set("");
    }

}
