package ui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import model.Role;
import ui.util.UIUtils;


public class DashboardBaseView extends Stage implements LibBaseView {
    public static final DashboardBaseView INSTANCE = new DashboardBaseView();
    private static final String UI_DASH_BOARD_VIEW = "/ui/view/DashBoardView.fxml";
    private boolean isInit = false;
    private double xOffset = 0;
    private double yOffset = 0;

    private Parent layout;
    private MenuBar menuBar;

    @Override
    public void init() {
        layout = UIUtils.lookupParent(UI_DASH_BOARD_VIEW);
        menuBar = (MenuBar) layout.lookup("#menubar");
        Start.updateMenuBar(menuBar);

        addEventFilter(WindowEvent.WINDOW_SHOWING, e -> {
            menuBar.getMenus().clear();
            Start.updateMenuBar(menuBar);
        });

        layout.getStylesheets().add("/ui/styles/titlebar.css");
        layout.getStylesheets().add(getClass().getResource("/ui/styles/dashboard_view.css").toExternalForm());

        layout.setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });

        layout.setOnMouseDragged(e -> {
            setX(e.getScreenX() - xOffset);
            setY(e.getScreenY() - yOffset);
        });

        setScene(new Scene(layout));
        setResizable(false);
        initStyle(StageStyle.UNDECORATED);
        centerOnScreen();
    }

    @Override
    public boolean isInitialized() {
        return isInit;
    }

    @Override
    public void isInitialized(boolean val) {
        isInit = val;
    }

    @Override
    public void setData(Object... data) {

    }

    @Override
    public Role[] requireAuth() {
        return new Role[]{Role.BOTH, Role.LIBRARIAN, Role.ADMIN};
    }
}
