package ar.lamansys.messages.application;

import ar.lamansys.messages.application.exception.UserSessionNotExists;
import ar.lamansys.messages.infrastructure.output.UserSessionStorage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetUserSession {
    private final UserSessionStorage userSessionStorage;

    public String run() throws UserSessionNotExists {
        var user = userSessionStorage.get();
        if (user.isEmpty()) {
            throw new UserSessionNotExists();
        }
        return user;
    }
}
