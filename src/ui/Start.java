package ui;


import business.SystemController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.util.UIUtils;

import static ui.Navigator.*;


public class Start extends Application {
    private static Stage primStage = null;
    private static MenuBar mainMenu = null;
    private double xOffset = 0;
    private double yOffset = 0;

    public static void main(String[] args) {
        launch(args);
    }

    private static MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.setId("mainMenu");

        if (SystemController.isSuperAdmin()) {
            menuBar.getMenus().addAll(createSystemMenu(true), createBookMenu(), createMemberMenu());
        } else if (SystemController.isLibrarian()) {
            menuBar.getMenus().addAll(createSystemMenu(true), createBookMenu());
        } else if (SystemController.isAdmin()) {
            menuBar.getMenus().addAll(createSystemMenu(true), createMemberMenu());
        } else {
            menuBar.getMenus().add(createSystemMenu(false));
        }
        return menuBar;
    }

    private static void updateMenuBar() {
        mainMenu = createMenuBar();
        VBox box = (VBox) primStage.getScene().getRoot();
        box.getChildren().remove(0);
        box.getChildren().add(0, mainMenu);
    }

    public static MenuBar updateMenuBar(MenuBar menuBar) {
        menuBar.setId("mainMenu");

        if (SystemController.isSuperAdmin() || SystemController.isLibrarian() || (SystemController.isAdmin())) {
            menuBar.getMenus().addAll(createSystemMenu(true), createBookMenu(), createMemberMenu());
        } else {
            menuBar.getMenus().add(createSystemMenu(false));
        }
        return menuBar;
    }

    private static Menu createSystemMenu(boolean authed) {
        Menu menu = new Menu("System");
        menu.setId("systemMenu");

        MenuItem miExit = new MenuItem("Exit");
        miExit.setOnAction(e -> {
            Platform.exit();
        });

        MenuItem miLogout = new MenuItem("Logout");
        miLogout.setOnAction(e -> {
            get().show(Start.class.toString());
        });

        if (authed) {
            menu.getItems().add(miLogout);
        }
        menu.getItems().addAll(miExit);
        return menu;
    }

    private static Menu createBookMenu() {
        Menu menu = new Menu("Books");
        menu.setId("booksMenu");
        MenuItem bookIds = new MenuItem("All Books");

        // TODO: register All Book Member here
        if (SystemController.isLibrarian() || SystemController.isSuperAdmin()) {
            MenuItem checkoutBook = new MenuItem("Checkout");
            // TODO: register checkout book here
            menu.getItems().addAll(checkoutBook);
        }
        if (SystemController.isAdmin() || SystemController.isSuperAdmin()) {
            MenuItem addBook = new MenuItem("Add New Book");
            // TODO: register new Book here
            MenuItem editBook = new MenuItem("Edit Book");
            // TODO: register add Book here
            menu.getItems().addAll(addBook, editBook);
        }
        menu.getItems().add(bookIds);
        return menu;
    }

    private static Menu createMemberMenu() {
        Menu menu = new Menu("Members");
        menu.setId("membersMenu");

        MenuItem memberIds = new MenuItem("All Members");
        // TODO: register all Member here
        if (SystemController.isLibrarian() || SystemController.isSuperAdmin()) {

            MenuItem searchMember = new MenuItem("Search member");
            // TODO: register search Member here
            menu.getItems().addAll(searchMember);
        }
        if (SystemController.isAdmin() || SystemController.isSuperAdmin()) {
            MenuItem addMember = new MenuItem("Add new member");
            MenuItem editMember = new MenuItem("Edit new member");
            menu.getItems().addAll(addMember, editMember);
        }

        menu.getItems().add(memberIds);
        return menu;
    }

    @Override
    public void start(Stage primaryStage) {
        primStage = primaryStage;
        primaryStage.setTitle("Main Page");

        VBox topContainer = new VBox();
        topContainer.setId("top-container");
        MenuBar mainMenu = new MenuBar();

        topContainer.getChildren().add(0, mainMenu);

        Parent loginRoot = UIUtils.lookupParent("/ui/view/LoginView.fxml");
        loginRoot.getStylesheets().add(getClass().getResource("/ui/styles/login_view.css").toExternalForm());
        loginRoot.setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });

        loginRoot.setOnMouseDragged(e -> {
            primaryStage.setX(e.getScreenX() - xOffset);
            primaryStage.setY(e.getScreenY() - yOffset);
        });

        topContainer.getChildren().add(loginRoot);

        primaryStage.setScene(new Scene(topContainer));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.centerOnScreen();

        // register to navigator
        get().register(Start.class.toString(), primaryStage);

        // register all other routes
        registerRoutes();

        primaryStage.show();
    }

    private void registerRoutes() {
        get().register(DashboardBaseView.class.toString(), DashboardBaseView.INSTANCE);
    }

}
