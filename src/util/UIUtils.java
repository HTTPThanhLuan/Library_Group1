package util;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Optional;

public final class UIUtils {

    public static Parent lookupParent(String fxmlTemplate) {
        Parent root = null;

        try {
            root = FXMLLoader.load(UIUtils.class.getResource(fxmlTemplate));
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            // UI can't load...so it's a critical bug, should be fixed. So, exit immediately.
            Platform.exit();
        }

        return root;
    }

    public static Parent lookupParent(FXMLLoader loader) {
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            Platform.exit();
        }

        return root;
    }


    public static void showAlertDialog(AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.show();
    }

    public static Optional<ButtonType> getConfirmationDiablog(String title, String header, String content) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        Optional<ButtonType> result = alert.showAndWait();
        return result;
    }


}
