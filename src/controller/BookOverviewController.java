package controller;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import home.Main;
import model.Book;
import model.BookCopy;

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


	// Reference to the main application.
	private Main mainApp;

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

	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;

		// Add observable list data to the table
		table.setItems(mainApp.getData());
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
		if (selectedIndex >= 0) {
			table.getItems().remove(selectedIndex);
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
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
		Book temp = new Book();
		boolean okClicked = mainApp.showBookEditDialog(temp);
		if (okClicked) {
			mainApp.getData().add(temp);
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
			boolean okClicked = mainApp.showBookEditDialog(selected);
			if (okClicked) {
				showDetails(selected);
			}

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Book Selected");
			alert.setContentText("Please select a book in the table.");
			alert.showAndWait();
		}
	}
}
