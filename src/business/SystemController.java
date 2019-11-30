package business;

import dataaccess.UserDataAccess;
import model.Role;
import model.User;
import ui.LibBaseView;

import javax.security.auth.login.LoginException;

public class SystemController implements ControllerInterface {
    public static Role currentRole = null;

    public static boolean isAdmin() {
        return (currentRole == Role.ADMIN);
    }

    public static boolean isLibrarian() {
        return (currentRole == Role.LIBRARIAN);
    }

    public static boolean isSuperAdmin() {
        return (currentRole == Role.BOTH);
    }

    public static boolean canAccess(LibBaseView libBaseView) {
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
