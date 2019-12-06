package home;

import java.io.IOException;

import controller.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Book;
import model.Member;
import util.UIUtils;

public class Main extends Application {

    public static final Main INSTANCE = new Main();

    public static Main getInstance() {
        return INSTANCE;
    }

	Stage primaryStage;
	private AnchorPane rootLayout;
    private double xOffset = 0;
    private double yOffset = 0;
    
    public static void main(String[] args) {
        launch(args);
    }

	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
    @Override
    public void start(Stage stage) throws Exception {
    	 primaryStage = stage;
    	 Stage loginStage = new Stage();
    	 showLogin(loginStage);
    	 rootLayout = FXMLLoader.load(getClass().getResource("../view/MainPageView.fxml"));
         Scene scene = new Scene(rootLayout, 850, 450);
        
         scene.getStylesheets().add(getClass().getResource("../resource/styles/style.css").toExternalForm());
         stage.setScene(scene);
         stage.show();
    }

    public Stage getStage() {
    	return primaryStage;
    }
    public boolean showPersonEditDialog(Member member) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/MemberEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Member");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			MemberEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMember(member);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    }
    
    public void showData() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			Stage anotherStage = new Stage();
		    Parent root = FXMLLoader.load(getClass().getResource("../view/PersonOverview.fxml"));
		    Scene scene = new Scene(root,850, 450);
		        
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
	
	public void showLogin(Stage primaryStage) {
		VBox topContainer = new VBox();
        Parent loginRoot = UIUtils.lookupParent("/view/LoginView.fxml");
        loginRoot.getStylesheets().add(getClass().getResource("/resource/styles/login_view.css").toExternalForm());
        loginRoot.setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });

        loginRoot.setOnMouseDragged(e -> {
            primaryStage.setX(e.getScreenX() - xOffset);
            primaryStage.setY(e.getScreenY() - yOffset);
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
        primaryStage.showAndWait();
  
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
