package ar.lamansys;

import static ar.lamansys.messages.MessageAppConfiguration.newBean;

import ar.lamansys.messages.MessageApp;
import ar.lamansys.messages.domain.NewMessageBo;

/**
 * Mensajes
 *
 */
public class App {

    public static void main( String[] args ) {
        prueba();
    }
    public static void prueba() {
        MessageApp messageApp = newBean();
        loadMessages(messageApp);
        assertContactsSize(messageApp, 3);
        assertChatSize(messageApp, "seller1", 2);
        assertChatSize(messageApp, "seller2", 1);
    }

    public static void loadMessages(MessageApp messageApp) {
        messageApp.sendUserMessage(new NewMessageBo("seller1", "precio?"));
        messageApp.sendUserMessage(new NewMessageBo("seller1", "sigue disponible?"));
        messageApp.sendUserMessage(new NewMessageBo("seller2", "precio"));
        messageApp.sendUserMessage(new NewMessageBo("seller3", "precio!"));
    }

    public static void assertChatSize(MessageApp messageApp, String contactId, int expected) {
        assertListSize(
                messageApp.fetchUserChat(contactId).size(),
                expected,
                String.format("mensajes con @%s", contactId)
        );
    }

    private static void assertContactsSize(MessageApp messageApp, int expected) {
        assertListSize(
                messageApp.listContacts().size(),
                expected,
                "contactos"
        );
    }

    private static void assertListSize(int actual, int expected, String message) {
        if (actual != expected) {
            System.out.println(String.format("🚨 Se encontraron %s pero se esperaban %s %s", actual, expected, message));
        }
    }
}
