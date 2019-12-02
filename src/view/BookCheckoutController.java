package view;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Book;
import model.Library;
import model.Member;

public class BookCheckoutController extends LeftNavControler implements Initializable{

	@FXML
	private TableView<Book> table;
	@FXML
	private TableColumn<Book, String> titleColumn;

	@FXML
	private Label idLabel;
	@FXML
	private Label titleLabel;
	@FXML
	private Label authorLabel;
	@FXML
	private Label publisherLabel;
	
	@FXML
	private Label firstNameLabel;
	
	@FXML
	private Label lastNameLabel;
	
	@FXML
	private Label emailLabel;
	
	@FXML
	private Label userNotFoundLabel;
	
	@FXML
	private Label bookNotFoundLabel;
	
	@FXML 
	private TextField memberIDInput;
	
	@FXML 
	private TextField bookIDInput;
	
	private Book book;
	private Member member;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Library library = Library.getInstance();
	}
	
	public void handleSearchUserAction(ActionEvent event) {
		member = Library.getInstance().getMembers().values().stream()
				.filter(member -> member.getId().equals(memberIDInput.getText())).findAny().orElse(null);
		if (member == null) {
			userNotFoundLabel.setText("Warning: User is required");
		} else {
			userNotFoundLabel.setText("");
		}
		showMemberDetails(member);
	} 
	
	public void handleSearchBookAction(ActionEvent event) {
		book = Library.getInstance().getBooks().values().stream()
				.filter(book -> book.getId().equals(bookIDInput.getText())).findAny().orElse(null);
		if (book == null) {
			bookNotFoundLabel.setText("Warning: Book is required");
		} else {
			bookNotFoundLabel.setText("");
		}
		showBookDetails(book);
	}
	
	public void handleCheckoutAction(ActionEvent event) {
		if (book != null && member != null) {
			userNotFoundLabel.setText("");
			Library.getInstance().updateBookQuantity(this.book, -1);
			System.out.println("Number of books after checkout: " + getBookQuantity(book));
			Library.getInstance().updateCheckoutBook(book, member);
		} else {
			if (member == null) {
				userNotFoundLabel.setText("Error: User is required");
			}
			if (book == null) {
				bookNotFoundLabel.setText("Error: Book is required");
			}
		}
		
	}
	
	private void showBookDetails(Book book) {
		if (book != null) {
			titleLabel.setText(book.getTitle());
			authorLabel.setText(book.getAuthor());
			publisherLabel.setText(book.getPublisher());
		} else {
			titleLabel.setText("");
			authorLabel.setText("");
			publisherLabel.setText("");
		}
	}
	
	private void showMemberDetails(Member member) {
		if (member != null) {
			firstNameLabel.setText(member.getFirstName());
			lastNameLabel.setText(member.getLastName());
			emailLabel.setText(member.getEmail());
		} else {
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			emailLabel.setText("");
		}
	}
	
	private int getBookQuantity(Book book) {
		return Library.getInstance().getAvailableBooks().get(book.getId());
	}

}
