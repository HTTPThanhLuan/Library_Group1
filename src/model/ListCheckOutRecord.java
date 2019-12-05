package model;


import dataaccess.Library;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ListCheckOutRecord {


    private static List<CheckOutRecord> list = new ArrayList<CheckOutRecord>();

    private static ObservableList<CheckOutRecord> obList = FXCollections.observableArrayList();

    //Constructor
    public ListCheckOutRecord() {

    }

    private static List<Member> listMember() {

        return null;
    }

    public static ObservableList<Member> getListOfMemberCheckOutByMember(Member member) {

        ObservableList<Member> members = FXCollections.observableArrayList();


        for (CheckOutRecord checkOutRecord : obList) {
            if (checkOutRecord.getObjectMember().getFirstName() == member.getFirstName())
                members.add(checkOutRecord.getObjectMember());
        }
        return members;
    }

    public static ObservableList<CheckOutRecord> getCheckOutRecords() {

        return FXCollections.observableArrayList(Library.getInstance().getCheckoutRecords());

    }

    public static ObservableList<Member> getListOfMemberCheckOut() {

        ObservableList<Member> members = FXCollections.observableArrayList();

        for (CheckOutRecord checkOutRecord : obList) {
            if (!members.contains(checkOutRecord.getObjectMember()))
                members.add(checkOutRecord.getObjectMember());
        }
        return members;
    }

    public static ObservableList<CheckOutRecord> getListOfBookCheckOutByMember(Member member) {

        ObservableList<CheckOutRecord> books = FXCollections.observableArrayList();

        for (CheckOutRecord checkOutRecord : obList) {
            if (checkOutRecord.getObjectMember().getFirstName().equals(member.getFirstName()))
                books.add(checkOutRecord);
        }
        return books;
    }

    //static initialization blocks
//	 static {
//		 
//		 //Get listbook
//	    Main main=new Main();
//		Library.getInstance().getCheckoutRecords();
//		 
//	 }

    public static Member createRandomMember() {

        Random rand = new Random();
        Member mem = listMember().get(rand.nextInt(listMember().size()));
        return mem;
    }


    //GetList
    public List<CheckOutRecord> getList() {

        return list;
    }


    //Get list of CheckOutRecord
    public ObservableList<CheckOutRecord> getObserList() {

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


	 
