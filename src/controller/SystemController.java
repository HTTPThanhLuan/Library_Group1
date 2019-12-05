package controller;

import dataaccess.UserDataAccess;
import model.Role;
import model.User;
import view.LibBaseView;

import javax.security.auth.login.LoginException;

public class SystemController implements ControllerInterface {

    public static final SystemController INSTANCE = new SystemController();

    public static SystemController getInstance() {
        return INSTANCE;
    }


    public Role currentRole = null;

    public boolean isAdmin() {
        return (currentRole == Role.ADMIN);
    }

    public boolean isLibrarian() {
        return (currentRole == Role.LIBRARIAN);
    }

    public boolean isSuperAdmin() {
        return (currentRole == Role.BOTH);
    }

    private SystemController() {

    }

    public boolean canAccess(LibBaseView libBaseView) {
        Role[] allowed = libBaseView.requireAuth();
        if (allowed == null || allowed.length < 1) {
            return true;
        }

        for (Role a : allowed) {
            if (a.equals(currentRole)) {
                return true;
            }
        }

        return false;
    }

    public void login(String userId, String password) throws LoginException {
        UserDataAccess userDataAccess = new UserDataAccess();
        User loginUser = userDataAccess.getUserByUserName(userId);
        if (loginUser == null) {
            throw new LoginException("ID " + userId + " not found");
        }
        if (!password.equals(loginUser.getPassword())) {
            throw new LoginException("Password incorrect");
        }
        currentRole = loginUser.getRole();

    }

    public void logout() {
        currentRole = null;
    }
}
