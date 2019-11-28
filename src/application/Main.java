package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Book;
import view.BookEditDialogController;
import view.BookOverviewController;


public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	private ObservableList<Book> data = FXCollections.observableArrayList();

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

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public ObservableList<Book> getData() {
		return data;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Library Application - Group 1");
		initRootLayout();
		showBookOverview();
	}

	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showBookOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/BookOverview.fxml"));
			AnchorPane view = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(view);

			BookOverviewController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens a dialog to edit details for the specified person. If the user
	 * clicks OK, the changes are saved into the provided person object and true
	 * is returned.
	 *
	 * @param person
	 *            the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
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
			dialogStage.initOwner(primaryStage);
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

	public static void main(String[] args) {
		launch(args);
	}
}
