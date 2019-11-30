package view;


import java.io.File;
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
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import model.Book;

import model.ListCheckOutRecord;
import model.ListCheckOutRecord.CheckOutRecord;
import model.Member;


public class RecordsCheckoutController extends Application implements Initializable{

	 // The table and columns
    @FXML
    TableView<CheckOutRecord> tableRecord;

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
    ChoiceBox cboChoice;
    
    
    
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
		// TODO Auto-generated method stub
		
		
		
		
		  // Set up the table data
		MemberController member=new MemberController();
		List<Member> listMember =member.getMembers();
		    ListCheckOutRecord data=new ListCheckOutRecord();
	

	 
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
		
       // tableRecord.setItems(data.getObserList());
        
        FilteredList<CheckOutRecord> flPerson = new FilteredList(data.getObserList(), p -> true);//Pass the data to a filtered list
        tableRecord.setItems(flPerson);//Set the table's items using the filtered list
       // tableRecord.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
        
        tableRecord.autosize();
        
        
        
        cboChoice.getItems().add("All");
        cboChoice.getItems().add("Title");
        cboChoice.getItems().add("Member");
        
        
        
        
       // TextField textField = new TextField();
        txtFilter.setPromptText("Search here!");
        txtFilter.setOnKeyReleased(keyEvent ->
        {
        	
        	  switch ((String)cboChoice.getValue())//Switch on choiceBox value
              {
                  case "All":
                  {
                      flPerson.setPredicate(p -> p.getMember().toLowerCase().contains(txtFilter.getText().toLowerCase().trim()) || 
                    		  p.getTitle().toLowerCase().contains(txtFilter.getText().toLowerCase().trim())
                    		  );//filter table by first name
                      break;
                  }
                  case "Title":
                  {
                		 flPerson.setPredicate(p -> p.getTitle().toLowerCase().contains(txtFilter.getText().toLowerCase().trim()));//filter table by first name
                      break;
                  }
                  case "Member":
                  {
                      flPerson.setPredicate( p -> p.getMember().toLowerCase().contains(txtFilter.getText().toLowerCase().trim()));//filter table by first name
                      break;
                  }
              }
        
       
        
        });
        
        
       
        
        
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
	public Robot createRobot() {
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
