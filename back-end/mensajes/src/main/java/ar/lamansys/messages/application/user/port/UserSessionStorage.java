package ar.lamansys.messages.application.user.port;

public interface UserSessionStorage {
    String get();
    void set(String userId);
}
