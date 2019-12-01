package home;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Member;




public class MemberController implements Initializable {
	   @FXML
	    private VBox pnItems = null;
	    @FXML
	    private Button pnlMember;

	    @FXML
	    private Button btnBook;
	    
	    @FXML
	    private Button btnCheckOutRecord;
	    
	    

	    @FXML
	    private Button btnMember;

	    @FXML
	    private Button btnMenus;

	    @FXML
	    private Button btnPackages;

	    @FXML
	    private Button btnBookCheckout;

	    @FXML
	    private Button btnSignout;

	    @FXML
	    private Pane pnlCustomer;

	    @FXML
	    private Pane pnlOrders;

	    @FXML
	    private Pane pnlOverview;

	    @FXML
	    private Pane pnlMenus;

	
		
		

    // The table and columns
    @FXML
    TableView<Member> itemTbl;

    @FXML
    TableColumn itemIdCol;
    @FXML
    TableColumn itemNameCol;
    @FXML
    TableColumn itemQtyCol;
    @FXML
    TableColumn itemPriceCol;

    // The table's data
    ObservableList<Member> data;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set up the table data
        itemIdCol.setCellValueFactory(
            new PropertyValueFactory<Member,String>("firstName")
        );
        itemNameCol.setCellValueFactory(
            new PropertyValueFactory<Member,String>("lastName")
        );
        itemQtyCol.setCellValueFactory(
            new PropertyValueFactory<Member,String>("email")
        );
        itemPriceCol.setCellValueFactory(
            new PropertyValueFactory<Member,String>("street")
        );
       
        data = FXCollections.observableArrayList();
        addData();
        itemTbl.setItems(data);
    }    

    static long nextId = 1;
    
    public void addData() {
		// Add some sample data
 	data.add(new Member("Hans", "Muster","12th Avenue","52240","FairField","mahi1@gmail.com","ADMIN"));
    	data.add(new Member("Ruth", "Mueller","Avenue","52240","FairField","mahi2@gmail.com","ADMIN"));
		data.add(new Member("Heinz", "Kurz","135","52240","FairField","mahi3@gmail.com","ADMIN"));
		data.add(new Member("Cornelia", "Meier","Avenue","52240","FairField","mahi4@gmail.com","ADMIN"));
		data.add(new Member("Werner", "Meyer","Avenue","52240","FairField","mahi5@gmail.com","LIBRARIAN"));
		data.add(new Member("Lydia", "Kunz","Avenue","52240","FairField","mahi6@gmail.com","LIBRARIAN"));
		data.add(new Member("Anna", "Best","Avenue1","52240","FairField","mahi7@gmail.com","LIBRARIAN"));
		data.add(new Member("Stefan", "Meier","Avenue2","52240","FairField","mahi8@gmail.com","BOTH"));
		data.add(new Member("Martin", "Mueller","Avenue3","52240","FairField","mahi9@gmail.com","BOTH"));

    }
    
    // add this function for getting the data list of members
    public ObservableList<Member> getMembers(){
    	
    	   data = FXCollections.observableArrayList();
    	
    	 addData();
    	 
    	 return data;
    }
  
    Main mainm= new Main();
    @FXML
    private void handleButtonAction(ActionEvent event) {
    	Member member = new Member();
		boolean okClicked = mainm.showPersonEditDialog(member);
		if (okClicked) {
			
			data.add(member);
			
		}
    }
    /**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeletePerson() {
		int selectedIndex = itemTbl.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			itemTbl.getItems().remove(selectedIndex);
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainm.getStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}

	

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected person.
	 */
	@FXML
	private void handleEditPerson() {
		Member selectedPerson = itemTbl.getSelectionModel().getSelectedItem();
		if (selectedPerson != null) {
			boolean okClicked = mainm.showPersonEditDialog(selectedPerson);
			if (okClicked) {
				
			}
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainm.getStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Member Selected");
			alert.setContentText("Please select a Member in the table.");

			alert.showAndWait();
		}
	}
	public void handleClicks(ActionEvent actionEvent) throws Exception {
		System.out.println(actionEvent.getSource());
        if (actionEvent.getSource() == btnMember) {
        	mainm.showData();
        	
        }
        if (actionEvent.getSource() == btnBook) {
        	mainm.showBookOverview();
        }
        
        if (actionEvent.getSource() == btnCheckOutRecord) {
        	mainm.showCheckOutBookRecord();
        }
        
        if (actionEvent.getSource() == btnBookCheckout) {
        	mainm.showBookCheckoutScreen();
        }
       
    }
    
}
