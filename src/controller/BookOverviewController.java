package controller;


import dataaccess.Library;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import home.Main;
import model.Book;
import model.BookCopy;

import java.io.IOException;

public class BookOverviewController {
	@FXML
	private TableView<Book> table;
	@FXML
	private TableColumn<Book, String> titleColumn;
	
	@FXML
	private TableView<BookCopy> tableBookCopy;
	@FXML
	private TableColumn<BookCopy, String> idColumn;
	@FXML
	private TableColumn<BookCopy, String> statusColumn;

	@FXML
	private Label idLabel;
	@FXML
	private Label titleLabel;
	@FXML
	private Label authorLabel;
	@FXML
	private Label publisherLabel;
	@FXML
	private Label isbnLabel;
	@FXML
	private Label numberOfCopyLabel;

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public BookOverviewController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		titleColumn.setCellValueFactory(new Callback<CellDataFeatures<Book, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Book, String> param) {
				return param.getValue().titleProperty();
			}
		});

		showDetails(null);
		// Listen for selection changes and show details
		table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDetails(newValue));
		loadData();
	}

	private void loadData() {
		ObservableList<Book> books = FXCollections.observableArrayList();
		Library.getInstance().getBooks().forEach((key, value) -> {
			books.add(value);
		});
		table.setItems(books);
	}

	private void showDetails(Book book) {
		if (book != null) {
			// Fill the labels with info from the person object.
			idLabel.setText(book.getId());
			titleLabel.setText(book.getTitle());
			authorLabel.setText(book.getAuthor());
			publisherLabel.setText(book.getPublisher());
			isbnLabel.setText(book.getIsbn());
			numberOfCopyLabel.setText(book.getNumberOfCopy().toString());
			
			idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
			statusColumn.setCellValueFactory(new PropertyValueFactory<>("Status"));
			if (book.getBookCopy() != null) 
				tableBookCopy.setItems(FXCollections.observableArrayList(book.getBookCopy()));
		} else {
			// if null, remove all the text.
			idLabel.setText("");
			titleLabel.setText("");
			authorLabel.setText("");
			publisherLabel.setText("");
			isbnLabel.setText("");
			numberOfCopyLabel.setText("");
		}
	}

	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDelete() {
		int selectedIndex = table.getSelectionModel().getSelectedIndex();
        Book selected = table.getSelectionModel().getSelectedItem();
		if (selectedIndex >= 0) {
			table.getItems().remove(selectedIndex);
			Library.getInstance().getBooks().remove(selected.getId());
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(Main.getInstance().getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Book Selected");
			alert.setContentText("Please select a book in the table.");
			alert.showAndWait();
		}
	}

	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new person.
	 */
	@FXML
	private void handleNew() {
		Book newBook = new Book();
		boolean okClicked = this.showBookEditDialog(newBook);
		if (okClicked) {
			Library.getInstance().getBooks().put(newBook.getId(), newBook);
			loadData();
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
			dialogStage.initOwner(Main.getInstance().getPrimaryStage());
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

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected person.
	 */
	@FXML
	private void handleEdit() {
		Book selected = table.getSelectionModel().getSelectedItem();
		if (selected != null) {
			boolean okClicked = this.showBookEditDialog(selected);
			if (okClicked) {
				showDetails(selected);
			}

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(Main.getInstance().getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Book Selected");
			alert.setContentText("Please select a book in the table.");
			alert.showAndWait();
		}
	}
}
