package dataaccess;

import dataaccess.data.UserData;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDataAccess {
    private List<User> users = new ArrayList<>();

    public UserDataAccess() {
        UserData userData = new UserData();
        users = userData.getUsers();
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUserByUserName(String userName) {
        return users.stream().filter(user -> userName.equals(user.getUsername()))
                .findAny()
                .orElse(null);
    }

}
