package ar.lamansys;

import ar.lamansys.messages.MessageApp;
import ar.lamansys.messages.MessageAppConfiguration;
import ar.lamansys.messages.application.exception.UserExistsException;
import ar.lamansys.messages.application.exception.UserNotExistsException;
import ar.lamansys.messages.application.exception.UserSessionNotExists;
import ar.lamansys.messages.domain.NewMessageBo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    private MessageApp messageApp;

    @BeforeEach
    void setUp() throws UserNotExistsException, UserExistsException, UserSessionNotExists {
        messageApp = MessageAppConfiguration.getBean();
        messageApp.clearData();
        messageApp.addUser("buyer");
        messageApp.addUser("seller1");
        messageApp.addUser("seller2");
        messageApp.addUser("seller3");
        messageApp.setUserSession("buyer");
        messageApp.sendUserMessage(new NewMessageBo("seller1", "precio?"));
        messageApp.sendUserMessage(new NewMessageBo("seller1", "sigue disponible?"));
        messageApp.sendUserMessage(new NewMessageBo("seller2", "precio"));
        messageApp.sendUserMessage(new NewMessageBo("seller3", "precio!"));
    }

    @AfterEach
    void clean(){
        messageApp.clearData();
    }

    @Test
    void listContacts_ok() throws UserSessionNotExists {
        assertEquals(3, messageApp.listContacts().size(), "La cantidad de contactos no es la esperada");
    }

    @Test
    void fetchUserChat_ok() throws UserSessionNotExists, UserNotExistsException {
        assertEquals(2, messageApp.fetchUserChat("seller1").size(), "La cantidad de mensajes con seller1 no es la esperada");
        assertEquals(1, messageApp.fetchUserChat("seller2").size(), "La cantidad de mensajes con seller2 no es la esperada");
        assertEquals(1, messageApp.fetchUserChat("seller3").size(), "La cantidad de mensajes con seller3 no es la esperada");
    }



}