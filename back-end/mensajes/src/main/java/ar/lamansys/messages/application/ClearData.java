package ar.lamansys.messages.application;

import ar.lamansys.messages.application.exception.UserSessionNotExists;
import ar.lamansys.messages.application.message.port.MessageStorage;
import ar.lamansys.messages.application.user.port.UserSessionStorage;
import ar.lamansys.messages.application.user.port.UserStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ClearData {

    private final MessageStorage messageStorage;
    private final UserStorage userStorage;
    private final UserSessionStorage userSessionStorage;

    public void run(){
        messageStorage.deleteAll();
        userStorage.deleteAll();
        userSessionStorage.set("");
    }

    @AllArgsConstructor
    @Service
    public static class GetUserSession {
        private final UserSessionStorage userSessionStorage;

        public String run() throws UserSessionNotExists {
            var user = userSessionStorage.get();
            if (user.isEmpty()) {
                throw new UserSessionNotExists();
            }
            return user;
        }
    }
}
