package home;

import controller.BookOverviewController;
import controller.RecordsCheckoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.UIUtils;

import java.io.IOException;

public class Main extends Application {

    public static final Main INSTANCE = new Main();
    Stage primaryStage;
    private AnchorPane rootLayout;
    private double xOffset = 0;
    private double yOffset = 0;

    public static Main getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        showLogin();
    }

    public Stage getStage() {
        return primaryStage;
    }


    public void showMemberView() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            Stage anotherStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../view/PersonOverview.fxml"));
            Scene scene = new Scene(root, 850, 450);

            scene.getStylesheets().add(getClass().getResource("../view/style.css").toExternalForm());
            anotherStage.setScene(scene);
            anotherStage.show();
        } catch (IOException e) {

        }
    }


    // Add book screen
    public void showBookOverview() {
        try {
            // Load book overview.
            Stage bookStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/BookOverview.fxml"));
            AnchorPane view = (AnchorPane) loader.load();
            bookStage.setScene(new Scene(view));
            BookOverviewController controller = loader.getController();
            bookStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Show checkout book record
    public void showCheckOutBookRecord() {
        try {
            // Load book overview.
            Stage bookStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/RecordsCheckout.fxml"));
            Pane view = (Pane) loader.load();
            bookStage.setScene(new Scene(view));

            RecordsCheckoutController controller = loader.getController();
            controller.setMainApp(this);
            //bookStage.setMaximized(true);// making window fullscreen
            bookStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLogin() throws IOException{
        VBox topContainer = new VBox();
        Stage loginStage = new Stage();
        Parent loginRoot = UIUtils.lookupParent("/view/LoginView.fxml");
        loginRoot.getStylesheets().add(getClass().getResource("/resource/styles/login_view.css").toExternalForm());
        loginRoot.setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });

        loginRoot.setOnMouseDragged(e -> {
            loginStage.setX(e.getScreenX() - xOffset);
            loginStage.setY(e.getScreenY() - yOffset);
        });

        loginRoot.setOnMouseDragged(e -> {
            loginStage.setX(e.getScreenX() - xOffset);
            loginStage.setY(e.getScreenY() - yOffset);
        });

        topContainer.getChildren().add(loginRoot);
        loginStage.setScene(new Scene(topContainer));
        loginStage.setResizable(false);
        loginStage.initStyle(StageStyle.UNDECORATED);
        loginStage.centerOnScreen();
        loginStage.showAndWait();

        Stage primaryStage = new Stage();
        rootLayout = FXMLLoader.load(getClass().getResource("../view/MainPageView.fxml"));
        Scene scene = new Scene(rootLayout, 850, 450);

        scene.getStylesheets().add(getClass().getResource("../resource/styles/style.css").toExternalForm());
        primaryStage.setScene(scene);
        this.primaryStage = primaryStage;
        primaryStage.show();

    }

    public void showBookCheckoutScreen() {
        try {
            System.out.println("showScreen");
            Stage bookCheckoutStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/checkout/BookCheckout.fxml"));
            Pane view = (Pane) loader.load();
            Scene scene = new Scene(view, 850, 650);

            scene.getStylesheets().add(getClass().getResource("../view/checkout/BookCheckout.css").toExternalForm());
            bookCheckoutStage.setScene(scene);
            bookCheckoutStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
