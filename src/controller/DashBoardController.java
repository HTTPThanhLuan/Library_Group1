package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DashBoardController {

    @FXML
    protected void onBtnCloseClicked(ActionEvent e) {
        Platform.exit();
    }

    public void initialize() {
    }
}
