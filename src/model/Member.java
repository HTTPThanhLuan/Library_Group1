package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Member {
	private StringProperty id;
	private  SimpleStringProperty firstName;
	private  SimpleStringProperty lastName;
	private  SimpleStringProperty street;
	private  SimpleStringProperty postalCode;
	private  SimpleStringProperty city;
	private  SimpleStringProperty email;
	private  SimpleStringProperty username;
	private  SimpleStringProperty password;
	private Role role; 
	
	/**
	 * Default constructor.
	 */
	public Member() {
		this(null, null, null, null, null, null,null);
	}

	/**
	 * Constructor with some initial data.
	 *
	 * 
	 * 
	 */
	public Member(String firstName, String lastName,String street,String postalCode,String city,String email,String rolevalue) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);

		
		this.street = new SimpleStringProperty(street);
		this.postalCode = new SimpleStringProperty(postalCode);
		this.city = new SimpleStringProperty(city);
		this.email = new SimpleStringProperty(email);
		this.username = new SimpleStringProperty(email);
		this.password = new SimpleStringProperty("123");
		if(rolevalue=="ADMIN") 
			this.role=Role.ADMIN;
		else if(rolevalue=="LIBRARIAN")
			this.role=Role.LIBRARIAN;
		else if(rolevalue=="BOTH") 
			this.role=Role.BOTH;
	}
	
	public Member(String id, String firstName, String lastName) {
		this.id = new SimpleStringProperty(id);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);

		// Some initial dummy data, just for convenient testing.
		this.street = new SimpleStringProperty("some street");
		this.postalCode = new SimpleStringProperty("1234");
		this.city = new SimpleStringProperty("some city");
		this.email = new SimpleStringProperty("mahlet@gmail.com");
	}
	
	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public StringProperty lastNameProperty() {
		return lastName;
	}

	public String getStreet() {
		return street.get();
	}

	public void setStreet(String street) {
		this.street.set(street);
	}

	public StringProperty streetProperty() {
		return street;
	}

	
	
	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email.set(email);
	}

	public StringProperty emailProperty() {
		return email;
	}
	public String getPostalCode() {
		return postalCode.get();
	}

	public void setPostalCode(String postalCode) {
		this.postalCode.set(postalCode);
	}

	public StringProperty postalCodeProperty() {
		return postalCode;
	}

	public String getCity() {
		return city.get();
	}

	public void setCity(String city) {
		this.city.set(city);
	}

	public StringProperty cityProperty() {
		return city;
	}

	public void setusername(String username) {
		this.username.set(username);
	}

	public StringProperty usernameProperty() {
		return username;
	}
	public void setpassword(String password) {
		this.password.set(password);
	}

	public StringProperty password() {
		return password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getId() {
		return id.get();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Member)
		    return this.id.equals(((Member)obj).id);
		else
			return false;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.id.hashCode();
	}
	
}
