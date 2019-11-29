package home;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Member;
import view.MemberEditDialogController;

public class Main extends Application {
	Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
    	 this.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("../view/ListMember.fxml"));
        Scene scene = new Scene(root,850, 450);
        
        scene.getStylesheets().add(getClass().getResource("../view/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    public Stage getStage() {return stage;}
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
			dialogStage.initOwner(stage);
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
}
