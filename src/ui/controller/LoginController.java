package ui.controller;

import business.ControllerInterface;
import business.SystemController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ui.DashboardBaseView;
import ui.util.UIUtils;
import ui.Navigator;

import javax.security.auth.login.LoginException;


public class LoginController {
    @FXML
    private TextField txtUser;
    @FXML private PasswordField txtPassword;

    @FXML private Button btnLogin;
    @FXML private Button btnExit;

    @FXML protected void onBtnLoginClicked(ActionEvent e) {
        try {
            ControllerInterface c = new SystemController();
            c.login(txtUser.getText().trim(), txtPassword.getText().trim());
            Navigator.get().show(DashboardBaseView.class.toString());
            txtUser.setText("");
            txtPassword.setText("");
        } catch (LoginException ex) {
            UIUtils.showAlertDialog(Alert.AlertType.ERROR, "Error", "Invalid credentials",
                    "Please input correct user id and password.");
        }
    }

    @FXML protected void onBtnExitClicked(ActionEvent e) {
        Platform.exit();
    }
}
