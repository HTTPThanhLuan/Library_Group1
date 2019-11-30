package model;

import java.time.LocalDate;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.DateUtil;

//inner class
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
			    
			    public Member getObjectMember() {
			    	return member;
			    }
			    
			    public Book getObjectBook() {
			    	return book;
			    }
				
		}
