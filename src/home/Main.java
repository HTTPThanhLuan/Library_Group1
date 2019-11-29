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
import model.Book;
import model.Member;
import view.BookEditDialogController;
import view.BookOverviewController;
import view.MemberEditDialogController;

public class Main extends Application {
	private ObservableList<Book> data = FXCollections.observableArrayList();
	Stage stage;
	private AnchorPane rootLayout;
    public static void main(String[] args) {
        launch(args);
    }
	public ObservableList<Book> getData() {
		return data;
	}
	public Stage getPrimaryStage() {
		return stage;
	}
	public Main() {
		// Add some sample data
		data.add(new Book("Beginning Java Objects", "Jacquie Barker", "Apress", "978-1-4302-0036-9"));
		data.add(new Book("Core Java Volume I – Fundamentals", "Cay S. Horstmann", "Prentice Hall", "978-0135166307"));
		data.add(new Book("The Supreme Awakening", "Craig Pearson", "Maharishi University of Management Press", "978-0923569525"));
		data.add(new Book("Mastering JavaFX 10", "Sergey Grinev", "Packt", "9781788293822"));
		data.add(new Book("Introduction to Algorithms", "Thomas H. Cormen", "The MIT Press", "978-0262033848"));
		data.add(new Book("Consciousness and the Quantum", "Robert Oates, Jr", "Maharishi University of Management Press", "Not available"));
		data.add(new Book("Transcendental Meditation", "Maharishi Mahesh Yogi", "Maharishi University of Management Press", "Not available"));
		data.add(new Book("HTML&CSS: Design and Build Web Sites", "Jon Duckett", "Wiley", "978-1118008188"));
		data.add(new Book("The Healthy Brain Solution for Women", "Nancy Lonsdorf MD", "Independently published", "978-1792896774"));
	}
	
	
    @Override
    public void start(Stage stage) throws Exception {
    	 this.stage = stage;
    	 rootLayout = FXMLLoader.load(getClass().getResource("../view/ListMember.fxml"));
         Scene scene = new Scene(rootLayout, 850, 450);
        
         scene.getStylesheets().add(getClass().getResource("../view/style.css").toExternalForm());
         stage.setScene(scene);
         stage.show();
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
			controller.setMainApp(this);
			bookStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean showBookEditDialog(Book book) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/BookEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Book");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(stage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			BookEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setBook(book);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
