package view;

import model.Role;

public interface LibBaseView {
	void init();
	boolean isInitialized();
	void isInitialized(boolean val);

	void setData(Object... data);
	Role[] requireAuth();
}
