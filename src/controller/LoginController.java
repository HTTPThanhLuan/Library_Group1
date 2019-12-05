package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.UIUtils;
import javafx.stage.Stage;


import javax.security.auth.login.LoginException;


public class LoginController {
    @FXML
    private TextField txtUser;
    @FXML private PasswordField txtPassword;

    @FXML private Button btnLogin;
    @FXML private Button btnExit;

    @FXML protected void onBtnLoginClicked(ActionEvent e) {
        try {
            SystemController.getInstance().login(txtUser.getText().trim(), txtPassword.getText().trim());
            txtUser.setText("");
            txtPassword.setText("");
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.close();
        } catch (LoginException ex) {
            UIUtils.showAlertDialog(Alert.AlertType.ERROR, "Error", "Invalid credentials",
                    "Please input correct user id and password.");
        }
    }

    @FXML protected void onBtnExitClicked(ActionEvent e) {
        Platform.exit();
    }
}
