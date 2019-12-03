package model;

import java.util.UUID;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;




public class Book {
	private ImageView image;
	private final StringProperty id;
	private final StringProperty title;
	private final StringProperty author;
	private final StringProperty publisher;
	private final StringProperty isbn;

	public Book() {
		
		this.id = new SimpleStringProperty(UUID.randomUUID().toString());
		this.title = new SimpleStringProperty(null);
		this.author = new SimpleStringProperty(null);
		this.publisher = new SimpleStringProperty(null);
		this.isbn = new SimpleStringProperty(null);
		this.image=new ImageView("/imagebooks/007149216X.jpg");
	}
	public Book(String title, String author, String publisher, String isbn) {
		this.id = new SimpleStringProperty(UUID.randomUUID().toString());
		this.title = new SimpleStringProperty(title);
		this.author = new SimpleStringProperty(author);
		this.publisher = new SimpleStringProperty(publisher);
		this.isbn = new SimpleStringProperty(isbn);
		this.image=new ImageView("/imagebooks/007149216X.jpg");
	}

	public Book(String id, String title, String author, String publisher, String isbn) {
		this.id = new SimpleStringProperty(id);
		this.title = new SimpleStringProperty(title);
		this.author = new SimpleStringProperty(author);
		this.publisher = new SimpleStringProperty(publisher);
		this.isbn = new SimpleStringProperty(isbn);
	}
	
	public Book(String title, String author) {
		this.id = new SimpleStringProperty(UUID.randomUUID().toString());
		this.title = new SimpleStringProperty(title);
		this.author = new SimpleStringProperty(author);
		this.publisher = new SimpleStringProperty("Not available");
		this.isbn = new SimpleStringProperty("Not available");
		this.image=new ImageView("/imagebooks/007149216X.jpg");
	}
	
	public String getId() {
		return id.get();
	}

	public String getTitle() {
		return title.get();
	}
	
	public StringProperty titleProperty() {
		return title;
	}

	public String getAuthor() {
		return author.get();
	}

	public String getPublisher() {
		return publisher.get();
	}

	public String getIsbn() {
		return isbn.get();
	}

	public void setTitle(String text) {
		title.set(text);		
	}

	public void setAuthor(String text) {
		author.set(text);		
	}

	public void setPublisher(String text) {
		publisher.set(text);		
	}

	public void setIsbn(String text) {
		isbn.set(text);
	}
	
	
	public ImageView imageProperty() {
        return image;
    }
	
	
}