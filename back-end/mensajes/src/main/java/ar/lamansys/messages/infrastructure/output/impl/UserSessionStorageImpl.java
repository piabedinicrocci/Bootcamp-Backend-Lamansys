package ar.lamansys.messages.infrastructure.output.impl;

import ar.lamansys.messages.application.user.port.UserSessionStorage;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Repository
public class UserSessionStorageImpl implements UserSessionStorage {
    private String user = "";
    private String filePath = "session.txt";

    public UserSessionStorageImpl() {
        loadFromFile();
    }
    private void saveToFile() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            writer.write(this.user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        if (Files.exists(Paths.get(filePath))) {
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
                this.user = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String get() {
        return this.user;
    }

    @Override
    public void set(String user) {
        this.user = user;
        saveToFile();
    }
}
