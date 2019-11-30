package model;



import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Date;

import home.Main;
import home.MemberController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.DateUtil;

public class ListCheckOutRecord {
	
    // inner class
	 public class CheckOutRecord {
		 
		 
		 private  SimpleStringProperty firstName;
		 
		 
		 private  StringProperty titleBook;
		 private  StringProperty memberName;
		 private  StringProperty returnDate;
		 private ImageView image;
		    
		 
		 
		   private Book book;
		   private Member member;   
		   private Date dueDate;
		   private boolean checkIn;
		   
		    public CheckOutRecord(Book book, Member member, Date dueDate) {
		    	this.book=book;
		    	this.member=member;
		    	this.dueDate=dueDate;
		    	this.checkIn=false;
		    	
		    	this.image=new ImageView( new Image( util.Image.createImageRanDom()));
		    }
		    
			
			
			 public StringProperty titleBookProperty() {
				 return  new SimpleStringProperty(this.book.getTitle());
				}

			 public StringProperty memberNameProperty() {
				 return  new SimpleStringProperty(this.member.getFirstName() + " " + this.member.getLastName());
				}
			 
			 public StringProperty dueDateProperty() {
				 
				LocalDate d= DateUtil.createRandomDate(2019, 2020);
				 
				 return new SimpleStringProperty(d.toString()); 
				}
			 
			
			 
			 
			  public void setImage(ImageView value) {
			        image = value;
			    }

			    public ImageView getImage() {
			        return image;
			    }
		    
			    public String getTitle() {
					return this.book.getTitle();
				}
			    
			    public String getMember() {
					return this.member.getFirstName() + " " + this.member.getLastName();
				}
				
		}
	 

	
	 private List<CheckOutRecord> list=new ArrayList<CheckOutRecord>();
	 
	 private ObservableList<CheckOutRecord> obList= FXCollections.observableArrayList(); 
	 
	 private List<Member> listMember () {
		 
		 MemberController member=new MemberController();
			List<Member> listMember =member.getMembers();
			
			return listMember;
	 }
	 
	 //Constructor
	 public ListCheckOutRecord() {
		 
		 //Get listbook
		 Main main=new Main();
		List<Book> listBook = main.getListBook();
		 
		
		
	
		//Emulating list of checkout record
		
		for (Book book : listBook) {
			
			
			  Date dueDate = new Date();
				CheckOutRecord checkOutRecord = new CheckOutRecord(book, createRandomMember(), dueDate);
				
				
				list.add(checkOutRecord);
				
				obList.add(checkOutRecord);
			
			
		}
		 
		 
		 
	 }
	 
	 public Member createRandomMember() {
		  
		    Random rand = new Random();
		    Member mem= listMember().get(rand.nextInt(listMember().size()));
		    return mem;
		}
	 
	 
	
	 

     //GetList
	 public List<CheckOutRecord> getList(){
		 
		 return list;
	 }
	 
     public ObservableList<CheckOutRecord> getObserList(){
		 
		 return obList;
	 }
	 
	 
	 //CheckOut
	 
	 public boolean checkOutBook(Book book, Member member, Date dueDate) {
		 try {
			 
			  CheckOutRecord checkOutRecord = new CheckOutRecord(book, member, dueDate);
		      list.add(checkOutRecord);
		
		 } catch (Exception e) {
			 
			 throw e;
			// TODO: handle exception
		}
	    
		 
		   return true;
	 }
}
