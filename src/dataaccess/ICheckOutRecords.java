package dataaccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CheckOutRecord;
import model.Member;

public interface ICheckOutRecords {

	   
	    public ObservableList<CheckOutRecord> getCheckOutRecords();

	    public ObservableList<Member> getListOfMemberCheckOut();

	    public ObservableList<CheckOutRecord> getListOfBookCheckOutByMember(Member member);


}
