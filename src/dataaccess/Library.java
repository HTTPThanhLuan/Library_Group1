package dataaccess;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javafx.beans.property.StringProperty;
import model.Book;
import model.CheckOutRecord;
import model.Member;
import model.User;
import util.DateUtil;

public class Library {
	
	private Map<String, Book> books;
	private Map<String, Integer> availableBooks;
	private Map<String, Member> members;
	private Map<String, User> users;
	private List<CheckOutRecord> checkoutBooks;
	private static Library libInstance;
	public Library() {
		books = new HashMap<>();
		availableBooks = new HashMap<>();
		members = new HashMap<>();
		users = new HashMap<>();
		initializeBooks();
		initializeMembers();
		checkoutBooks = new ArrayList<>();
	}
	
	public Map<String, Book> getBooks() {
		return books;
	}
	
	public Map<String, Integer> getAvailableBooks() {
		return availableBooks;
		
	}
	
	public Map<String, Member> getMembers() {
		return members;
	}
	
	public Map<String, User> getUsers() {
		return users;
	}
	
	public  List<CheckOutRecord> getCheckoutRecords() {
		return checkoutBooks;
	}
	
	public static Library getInstance() {
		if (libInstance == null) libInstance = new Library();
		return libInstance;
	}
	
	
	private void initializeCheckoutRecords() {
		
		for (Book book : books.values()) {
			
			
			    Date dueDate = new Date();
				CheckOutRecord checkOutRecord = new CheckOutRecord(book, createRandomMember(), dueDate);
				
				
				checkoutBooks.add(checkOutRecord);
				
			
			
		}
	}
	
	private void initializeBooks() {
		Book b = new Book("1", "Beginning Java Objects", "Jacquie Barker", "Apress", "978-1-4302-0036-9","/resource/images/007149216X.jpg");
		updateBookAndQuantity(b, 100);
		b = new Book("2", "Core Java Volume I Fundamentals", "Cay S. Horstmann", "Prentice Hall", "978-0135166307","/resource/images/007149328X.jpg");
		updateBookAndQuantity(b, 100);
		b = new Book("3", "The Supreme Awakening", "Craig Pearson", "Maharishi University of Management Press", "978-0923569525","/resource/images/007222438X.jpg");
		updateBookAndQuantity(b, 90);
		b = new Book("4", "Mastering JavaFX 10", "Sergey Grinev", "Packt", "9781788293822","/resource/images/007222942X.jpg");
		updateBookAndQuantity(b, 80);
		b = new Book("5", "Introduction to Algorithms", "Thomas H. Cormen", "The MIT Press", "978-0262033848","/resource/images/012372533X.jpg");
		updateBookAndQuantity(b, 100);
		b = new Book("6","Consciousness and the Quantum", "Robert Oates, Jr", "Maharishi University of Management Press", "Not available","/resource/images/012387582X.jpg");
		updateBookAndQuantity(b, 100);
		b = new Book("7", "Transcendental Meditation", "Maharishi Mahesh Yogi", "Maharishi University of Management Press", "Not available","/resource/images/007149216X.jpg");
		updateBookAndQuantity(b, 100);
		b = new Book("8","HTML&CSS: Design and Build Web Sites", "Jon Duckett", "Wiley", "978-1118008188","/resource/images/007149216X.jpg");
		updateBookAndQuantity(b, 100);
		b = new Book("9","The Healthy Brain Solution for Women", "Nancy Lonsdorf MD", "Independently published", "978-1792896774","/resource/images/007149216X.jpg");
		updateBookAndQuantity(b, 100);
	}
	
	public void addBook(Book b) {
		books.put(b.getId(), b);
	}
	
	public void updateBookQuantity(Book b, int quantity) {
		if (availableBooks.containsKey(b.getId())) {
			int remaining = availableBooks.get(b.getId());
			availableBooks.put(b.getId(), remaining + quantity);
		} else {
			availableBooks.put(b.getId(), quantity);
		}
		
	}
	
	public void updateCheckoutBook(Book book, Member member) {
		Date today = new Date();
		checkoutBooks.add(new CheckOutRecord(book, member, today));
	}
	
	private void initializeUsers() {
		
	}
	
	private void initializeMembers() {
		Member m = new Member("1", "Member1", "LastName");
		members.put(m.getId(), m);
		m = new Member("2", "Member2", "LastName");
		members.put(m.getId(), m);
		m = new Member("3", "Member3", "LastName");
		members.put(m.getId(), m);
		m = new Member("4", "Member4", "LastName");
		members.put(m.getId(), m);
		m = new Member("5", "Member5", "LastName");
		members.put(m.getId(), m);
		m = new Member("6", "Member6", "LastName");
		members.put(m.getId(), m);
		m = new Member("7", "Member7", "LastName");
		members.put(m.getId(), m);
		m = new Member("8", "Member8", "LastName");
		members.put(m.getId(), m);
		m = new Member("9", "Member9", "LastName");
		members.put(m.getId(), m);
		m = new Member("10", "Member10", "LastName");
		members.put(m.getId(), m);
		
	}
	private void updateBookAndQuantity(Book b, int quantity) {
		addBook(b);
		updateBookQuantity(b, quantity);
	}
	
	private Member createRandomMember() {

        Random rand = new Random();

        Member mem= members.get(rand.nextInt(members.size()));

        return mem;

    }

}
