package controller;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.glass.ui.Application;
import com.sun.glass.ui.CommonDialogs.ExtensionFilter;
import com.sun.glass.ui.CommonDialogs.FileChooserResult;

import dataaccess.Library;

import com.sun.glass.ui.Cursor;
import com.sun.glass.ui.Pixels;
import com.sun.glass.ui.Robot;
import com.sun.glass.ui.Screen;
import com.sun.glass.ui.Size;
import com.sun.glass.ui.Timer;
import com.sun.glass.ui.View;
import com.sun.glass.ui.Window;

import home.Main;
import home.MemberController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Book;

import model.*;



public class RecordsCheckoutController extends Application implements Initializable{

	 // The table and columns
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
    TableColumn tbcImage;
    
    @FXML
    TextField txtFilter;
    
    @FXML
    TextField txtFillterMember;
    
    
    @FXML
    ChoiceBox cboChoice;
    
    @FXML
    ChoiceBox cboFilterMember;
    
    @FXML
    TableColumn colFirstName;
       
    @FXML
    TableColumn colLastName;
    
   
    
    
	// Reference to the main application.
	private Main mainApp;
		
	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;

		// Add observable list data to the table
	//	table.setItems(mainApp.getData());
	}
	


	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		
		// Set up the table data Book Checkout==================================================
//		MemberController member=new MemberController();
//		List<Member> listMember =member.getMembers();
		//ListCheckOutRecord data= Library.getInstance().getCh;
		    
		    
		
		

	 
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
		
		
		// Setup column of table member checkout==============================================
		
		colFirstName.setCellValueFactory(
 	            new PropertyValueFactory<Member,String>("firstName")
 	        );
		
		colLastName.setCellValueFactory(
 	            new PropertyValueFactory<Member,String>("lastName")
 	        );
        
		
		//Filter CheckOut book==============================================================
        FilteredList<CheckOutRecord> flBook = new FilteredList(ListCheckOutRecord.getCheckOutRecords(), p -> true);//Pass the data to a filtered list
        tableRecord.setItems(flBook);//Set the table's items using the filtered list
      
        
        //Filter CheckOut member===========================================================
        FilteredList<Member> flMembers = new FilteredList(ListCheckOutRecord.getListOfMemberCheckOut(), p -> true);//Pass the data to a filtered list
        tblMemberCheckOut.setItems(flMembers);//Set the table's items using the filtered list
        
        
        
        // Combobox=======================================================================
        cboChoice.getItems().add("All");
        cboChoice.getItems().add("Title");
        cboChoice.getItems().add("Member");
        
        
        cboChoice.setValue("All");
        
        cboFilterMember.getItems().add("All");
        cboFilterMember.getItems().add("FirstName");
        cboFilterMember.getItems().add("LastName");
        
        cboFilterMember.setValue("All");
        
        //Search Book Checkout============================================================
     
        txtFilter.setPromptText("Search here!");
        txtFilter.setOnKeyReleased(keyEvent ->
        {
        	
        	  switch ((String)cboChoice.getValue())//Switch on choiceBox value
              {
                  case "All":
                  {
                	  flBook.setPredicate(p -> p.getMember().toLowerCase().contains(txtFilter.getText().toLowerCase().trim()) || 
                    		  p.getTitle().toLowerCase().contains(txtFilter.getText().toLowerCase().trim())
                    		  );//filter table by first name
                      break;
                  }
                  case "Title":
                  {
                	  flBook.setPredicate(p -> p.getTitle().toLowerCase().contains(txtFilter.getText().toLowerCase().trim()));//filter table by first name
                      break;
                  }
                  case "Member":
                  {
                	  flBook.setPredicate( p -> p.getMember().toLowerCase().contains(txtFilter.getText().toLowerCase().trim()));//filter table by first name
                      break;
                  }
              }
        
       
        
        });
        
      //Search Member Checkout============================================================
        
        txtFillterMember.setPromptText("Search here!");
        txtFillterMember.setOnKeyReleased(keyEvent ->
        {
        	
        	  switch ((String)cboFilterMember.getValue())//Switch on choiceBox value
              {
                  case "All":
                  {
                	  flMembers.setPredicate(p -> p.getFirstName().toLowerCase().contains(txtFillterMember.getText().toLowerCase().trim()) || 
                    		  p.getLastName().toLowerCase().contains(txtFillterMember.getText().toLowerCase().trim())
                    		  );//filter table by first name
                      break;
                  }
                  case "FirstName":
                  {
                	  flMembers.setPredicate(p -> p.getFirstName().toLowerCase().contains(txtFillterMember.getText().toLowerCase().trim()));//filter table by first name
                      break;
                  }
                  case "LastName":
                  {
                	  flMembers.setPredicate( p -> p.getLastName().toLowerCase().contains(txtFillterMember.getText().toLowerCase().trim()));//filter table by first name
                      break;
                  }
              }
        
       
        
        });
        
        // doubleclick on table checkout member
        
        tblMemberCheckOut.setRowFactory( tv -> {
            TableRow<Member> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                	Member rowData = row.getItem();
                    System.out.println(rowData.getFirstName());
                    
                    showBookEditDialog(rowData);
                }
            });
            return row ;
        });
       
       
        
        
	}


	
	
	public boolean showBookEditDialog(Member member) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/viewRecordCheckout.fxml"));
			Pane page = (Pane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			//dialogStage.initOwner(stage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			viewRecordCheckoutController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.showRecord(member);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	protected Object _enterNestedEventLoop() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected int _getKeyCodeForChar(char arg0) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	protected void _invokeAndWait(Runnable arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void _invokeLater(Runnable arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void _leaveNestedEventLoop(Object arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected boolean _supportsTransparentWindows() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	protected boolean _supportsUnifiedWindows() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Cursor createCursor(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Cursor createCursor(int arg0, int arg1, Pixels arg2) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Pixels createPixels(int arg0, int arg1, ByteBuffer arg2) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Pixels createPixels(int arg0, int arg1, IntBuffer arg2) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Pixels createPixels(int arg0, int arg1, IntBuffer arg2, float arg3) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Timer createTimer(Runnable arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public View createView() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Window createWindow(long arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Window createWindow(Window arg0, Screen arg1, int arg2) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected void runLoop(Runnable arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected FileChooserResult staticCommonDialogs_showFileChooser(Window arg0, String arg1, String arg2, String arg3,
			int arg4, boolean arg5, ExtensionFilter[] arg6, int arg7) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected File staticCommonDialogs_showFolderChooser(Window arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected Size staticCursor_getBestSize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected void staticCursor_setVisible(boolean arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected int staticPixels_getNativeFormat() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	protected Screen[] staticScreen_getScreens() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected double staticScreen_getVideoRefreshPeriod() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	protected int staticTimer_getMaxPeriod() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	protected int staticTimer_getMinPeriod() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	protected int staticView_getMultiClickMaxX() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	protected int staticView_getMultiClickMaxY() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	protected long staticView_getMultiClickTime() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
