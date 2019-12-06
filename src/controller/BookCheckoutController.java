package controller;


import dataaccess.Library;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Book;
import model.Member;

import java.net.URL;
import java.util.ResourceBundle;

public class BookCheckoutController extends LeftNavControler implements Initializable {

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
    private ComboBox<Member> cboMember;

    @FXML
    private ComboBox<Book> cboBook;

    @FXML
    private Button checkoutBtn;
    private Book book;
    private Member member;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cboBook.setItems(FXCollections.observableArrayList(Library.getInstance().getBooks().values()));
        cboMember.setItems(FXCollections.observableArrayList(Library.getInstance().getMembers().values()));
        setConverter();
    }

    private void setConverter() {
        cboBook.setConverter(new StringConverter<Book>() {
            @Override
            public String toString(Book book) {
                return book.getTitle() + ", " + book.getAuthor();
            }

            @Override
            public Book fromString(String s) {
                return null;
            }
        });

        cboMember.setConverter(new StringConverter<Member>() {
            @Override
            public String toString(Member member) {
                return member.getFirstName() + ", " + member.getLastName();
            }

            @Override
            public Member fromString(String s) {
                return null;
            }
        });
    }

    public void handleSearchUserAction(ActionEvent event) {
        member = cboMember.getValue();
        if (member == null) {
            userNotFoundLabel.setText("Warning: User is required");
        } else {
            userNotFoundLabel.setText("");
        }
        showMemberDetails(member);
    }

    public void handleSearchBookAction(ActionEvent event) {
        book = cboBook.getValue();
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
            Stage stage = (Stage) checkoutBtn.getScene().getWindow();
            stage.close();
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
