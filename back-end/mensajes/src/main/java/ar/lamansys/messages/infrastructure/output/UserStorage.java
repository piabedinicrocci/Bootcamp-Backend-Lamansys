package ar.lamansys.messages.infrastructure.output;

public interface UserStorage {

    void save(
            String userId
    );

    boolean exists(
            String userId
    );

    void deleteAll();

}
