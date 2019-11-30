package view;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import javafx.fxml.FXML;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.CheckOutRecord;
import model.ListCheckOutRecord;
import model.Member;

public class viewRecordCheckoutController {
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
	    
	    public void showRecord(Member member) {

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
			
			
			
			 tableRecord.setItems(ListCheckOutRecord.getListOfBookCheckOutByMember(member));
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
