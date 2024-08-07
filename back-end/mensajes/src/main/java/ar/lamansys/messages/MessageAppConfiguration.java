package ar.lamansys.messages;

import ar.lamansys.messages.application.AddUser;
import ar.lamansys.messages.application.AssertUserExists;
import ar.lamansys.messages.application.AssertUserNotExists;
import ar.lamansys.messages.application.ClearData;
import ar.lamansys.messages.application.FetchUserChat;
import ar.lamansys.messages.application.GetUserSession;
import ar.lamansys.messages.application.ListContacts;
import ar.lamansys.messages.application.SendUserMessage;
import ar.lamansys.messages.application.SetUserSession;
import ar.lamansys.messages.infrastructure.output.impl.MessageStorageListImpl;
import ar.lamansys.messages.infrastructure.output.impl.UserSessionStorageImpl;
import ar.lamansys.messages.infrastructure.output.impl.UserStorageSetImpl;

public class MessageAppConfiguration {

    static MessageApp messageAppSingleton = null;

    public static MessageApp getBean() {
        if (messageAppSingleton == null) {
            messageAppSingleton = newBean();
        }
        return messageAppSingleton;
    }

    private static MessageApp newBean() {
        var messageStorage = new MessageStorageListImpl();
        var userSessionStorage = new UserSessionStorageImpl();
        var userStorage =new UserStorageSetImpl();

        var assertUserExists = new AssertUserExists(userStorage);
        var assertUserNotExists = new AssertUserNotExists(userStorage);

        AddUser addUser = new AddUser(userStorage, assertUserNotExists);
        GetUserSession getUserSession = new GetUserSession(userSessionStorage);
        ClearData clearData = new ClearData(messageStorage, userStorage, userSessionStorage);
        FetchUserChat fetchUserChat = new FetchUserChat(getUserSession, messageStorage, assertUserExists);
        ListContacts listContacts = new ListContacts(getUserSession, messageStorage);
        SendUserMessage sendUserMessage = new SendUserMessage(getUserSession, messageStorage, assertUserExists);
        SetUserSession setUserSession = new SetUserSession(userSessionStorage, assertUserExists);

        return new MessageApp(
                addUser,
                clearData,
                fetchUserChat,
                getUserSession,
                listContacts,
                sendUserMessage,
                setUserSession
        );
    }
}

