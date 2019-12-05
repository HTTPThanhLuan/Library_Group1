package model;

import java.util.UUID;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookCopy {
	private final StringProperty id;
	private Book book;
	private final StringProperty status;

	public BookCopy(Book book) {
		this.book = book;
		this.id = new SimpleStringProperty(UUID.randomUUID().toString());
		this.status = new SimpleStringProperty("Available");
	}
	public String getId() {
		return id.get();
	}
	public Book getBook() {
		return book;
	}
	public String getStatus() {
		return status.get();
	}
}
