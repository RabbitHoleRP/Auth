package br.com.rabbithole.auth.data.storage;

import br.com.rabbithole.auth.entities.LoginProcessEntity;

import java.util.HashMap;

public class LoginProcessStorage {
    private final HashMap<String, LoginProcessEntity> storage = new HashMap<>();

    private HashMap<String, LoginProcessEntity> getStorage() {
        return storage;
    }

    public void addItemToStorage(String nick, LoginProcessEntity loginProcess) {
        getStorage().put(nick, loginProcess);
    }

    public void updateItemStoraged(String nick, LoginProcessEntity loginProcess) {
        getStorage().put(nick, loginProcess);
    }

    public boolean hasItemInStorage(String nick) {
        return getStorage().containsKey(nick);
    }

    public void removeItemFromStorage(String nick) {
        getStorage().remove(nick);
    }

    public LoginProcessEntity getInformation(String nick) {
        return getStorage().get(nick);
    }
}
