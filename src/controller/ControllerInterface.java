package controller;

import javax.security.auth.login.LoginException;

public interface ControllerInterface {
	public void login(String userId, String password) throws LoginException;
	void logout();
}
