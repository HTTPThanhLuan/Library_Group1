package ui;

import business.SystemController;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Navigator {
    public static final Navigator INSTANCE = new Navigator();

    public static Navigator get() {
        return INSTANCE;
    }

    private Map<String, Stage> routes = new HashMap<>();
    private String currentRouteClz;

    private Navigator() {

    }

    private Navigator(String routeClz, Stage baseWindow) {
        register(routeClz, baseWindow);
        currentRouteClz = routeClz;
    }

    public void register(String routeClz, Stage stage) {
        routes.put(routeClz, stage);
    }

    public void hideAll() {
        for (Stage s: routes.values()) {
            s.hide();
        }
    }

    public void show(String routeClz, Object... data) {
        hideAll();

        Stage s = routes.get(routeClz);
        if (s != null) {
            if (s instanceof LibBaseView) {
                LibBaseView w = (LibBaseView) s;
                if (!w.isInitialized()) {
                    w.init();
                    w.isInitialized(true);
                }
                if (SystemController.canAccess(w)) {
                    w.setData(data);
                    s.show();
                }
                currentRouteClz = routeClz;
            } else if (routeClz.equals(Start.class.toString())) {
                s.show();
            }
        }


    }

    public static interface WindowShowListener {
        void onPreShow(Object data);
        void onPostShow(Object data);
    }



}
