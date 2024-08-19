package ar.lamansys.messages.application.user.port;

public interface UserStorage {

    void save(
            String userId
    );

    boolean exists(
            String userId
    );

    void deleteAll();

}
