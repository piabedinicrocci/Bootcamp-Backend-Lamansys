package ar.lamansys.messages.application;

import ar.lamansys.messages.application.exception.UserNotExistsException;
import ar.lamansys.messages.infrastructure.output.UserStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AssertUserExists {
    private UserStorage userStorage;

    public void run(String userId) throws UserNotExistsException {
        if ( ! userStorage.exists(userId) ) {
            throw new UserNotExistsException(userId);
        }
    }
}
