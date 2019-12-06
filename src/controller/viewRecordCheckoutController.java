package controller;

import dataaccess.CheckOutRecordDB;
import dataaccess.ICheckOutRecords;
import javafx.fxml.FXML;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.CheckOutRecord;
import model.Member;

public class viewRecordCheckoutController {
	
	private ICheckOutRecords checkOutRecords;
	
	  @FXML
	    TableView<CheckOutRecord> tableRecord;
	    
	    @FXML
	    TableView<Member> tblMemberCheckOut;

	    @FXML
	    TableColumn tbcTitle;
	    
	    @FXML
	    TableColumn tbcDueDate;
	    
	    @FXML
	    TableColumn tbcLibrarian;
	    
	    @FXML
	    Labeled lbName;
	    
	    
		private Stage dialogStage;
	    
	    @FXML
	    TableColumn tbcImage;
	    
	    private boolean okClicked = false;
	    
	    @FXML
		private void initialize() {
	   
	    	checkOutRecords = new CheckOutRecordDB();
	    }
	    
	    public void showDialog(Member member) {

	    	lbName.setText(member.getFirstName() +" "+ member.getLastName());
	    	
	    	
			// Setup column of table book checkout==============================================
			tbcTitle.setCellValueFactory(
		 	            new PropertyValueFactory<CheckOutRecord,String>("titleBook")
		 	        );
			
			tbcLibrarian.setCellValueFactory(
	 	            new PropertyValueFactory<CheckOutRecord,String>("memberName")
	 	        );
			
			tbcDueDate.setCellValueFactory(
	 	            new PropertyValueFactory<CheckOutRecord,String>("dueDate")
	 	        );
	      
			tbcImage.setCellValueFactory(
	 	            new PropertyValueFactory<CheckOutRecord,String>("image")
	 	        );
			
			
			
			 tableRecord.setItems(checkOutRecords.getListOfBookCheckOutByMember(member));
	    }
	    
	    /**
		 * Sets the stage of this dialog.
		 *
		 * @param dialogStage
		 */
		public void setDialogStage(Stage dialogStage) {
			this.dialogStage = dialogStage;
		}
		
		public boolean isOkClicked() {
			return okClicked;
		}
}
