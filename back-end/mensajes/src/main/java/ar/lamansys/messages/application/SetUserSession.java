package ar.lamansys.messages.application;

import ar.lamansys.messages.application.exception.UserNotExistsException;
import ar.lamansys.messages.infrastructure.output.UserSessionStorage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SetUserSession {
    private final UserSessionStorage userSessionStorage;
    private final AssertUserExists assertUserExists;

    public void run(String userId) throws UserNotExistsException {
        assertUserExists.run(userId);
        userSessionStorage.set(userId);
    }
}
