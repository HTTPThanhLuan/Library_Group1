package dataaccess;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CheckOutRecord;
import model.Member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class CheckOutRecordDB extends Library 
implements ICheckOutRecords
{
   public static List<CheckOutRecord> list=	Library.getInstance().getCheckoutRecords();
   

   @Override
    public ObservableList<CheckOutRecord> getCheckOutRecords() {

        return FXCollections.observableArrayList(Library.getInstance().getCheckoutRecords());

    }
    @Override
    public ObservableList<Member> getListOfMemberCheckOut() {

        ObservableList<Member> members = FXCollections.observableArrayList();

        for (CheckOutRecord checkOutRecord : list) {
            if (!members.contains(checkOutRecord.getObjectMember()))
                members.add(checkOutRecord.getObjectMember());
        }
        return members;
    }
    @Override
    public  ObservableList<CheckOutRecord> getListOfBookCheckOutByMember(Member member) {

        ObservableList<CheckOutRecord> checkOutRecords = FXCollections.observableArrayList();

        for (CheckOutRecord checkOutRecord : list) {
            if (checkOutRecord.getObjectMember().getId().equals(member.getId()))
            	checkOutRecords.add(checkOutRecord);
        }
        return checkOutRecords;
    }

	


}


	 
