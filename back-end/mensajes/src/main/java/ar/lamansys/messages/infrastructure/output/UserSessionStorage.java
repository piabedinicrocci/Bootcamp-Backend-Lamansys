package ar.lamansys.messages.infrastructure.output;

public interface UserSessionStorage {
    String get();
    void set(String userId);
}
