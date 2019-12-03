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
	
    

	
	 private static List<CheckOutRecord> list=new ArrayList<CheckOutRecord>();
	 
	 private static ObservableList<CheckOutRecord> obList= FXCollections.observableArrayList(); 
	 
	 private static List<Member> listMember () {
		 
		 MemberController member=new MemberController();
			List<Member> listMember =member.getMembers();
			
			return listMember;
	 }
	 
	 
	 public static ObservableList<Member> getListOfMemberCheckOutByMember(Member member)
	 {
		
		 ObservableList<Member> members= FXCollections.observableArrayList(); 
		 
		 for (CheckOutRecord checkOutRecord : obList) {
			if(checkOutRecord.getObjectMember().getFirstName() == member.getFirstName())
				members.add(checkOutRecord.getObjectMember());
		}
		 return members;
	 }
	 
	 public static ObservableList<Member> getListOfMemberCheckOut()
	 {
		
		 ObservableList<Member> members= FXCollections.observableArrayList(); 
		 
		 for (CheckOutRecord checkOutRecord : obList) {
			 if(!members.contains(checkOutRecord.getObjectMember()))
		      	members.add(checkOutRecord.getObjectMember());
		}
		 return members;
	 }
	 
	 public static ObservableList<CheckOutRecord> getListOfBookCheckOutByMember(Member member)
	 {
		
		 ObservableList<CheckOutRecord> books= FXCollections.observableArrayList(); 
		 
		 for (CheckOutRecord checkOutRecord : obList) {
			 if(checkOutRecord.getObjectMember().getFirstName().equals(member.getFirstName()))
			    books.add(checkOutRecord);
		}
		 return books;
	 }
	 
	 //Constructor
	 public ListCheckOutRecord() {
			 
	 }
	 
	 //static initialization blocks
	 static {
		 
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
	 
	 public static Member createRandomMember() {
		  
		    Random rand = new Random();
		    Member mem= listMember().get(rand.nextInt(listMember().size()));
		    return mem;
		}
	 
	 
	
	 

     //GetList
	 public List<CheckOutRecord> getList(){
		 
		 return list;
	 }
	 
	 
	 //Get list of CheckOutRecord 
     public ObservableList<CheckOutRecord> getObserList(){
		 
		 return obList;
	 }
	 
	 
	 //CheckOut
	 public boolean checkOutBook(Book book, Member member, Date dueDate) {
		 try {
			 
			  CheckOutRecord checkOutRecord = new CheckOutRecord(book, member, dueDate);
		      list.add(checkOutRecord);
		      obList.add(checkOutRecord);
		
		 } catch (Exception e) {
			 
			 throw e;
			// TODO: handle exception
		}
	    
		 
		   return true;
	 }
}


	 
