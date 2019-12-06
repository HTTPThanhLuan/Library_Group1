package controller;


import com.sun.glass.ui.*;
import com.sun.glass.ui.CommonDialogs.ExtensionFilter;
import com.sun.glass.ui.CommonDialogs.FileChooserResult;

import dataaccess.CheckOutRecordDB;
import dataaccess.ICheckOutRecords;
import home.Main;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.CheckOutRecord;
import model.Member;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ResourceBundle;


public class RecordsCheckoutController {

	private ICheckOutRecords checkOutRecords;
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


    @FXML
	private void initialize() {
   

    	checkOutRecords=new CheckOutRecordDB();
    	
        // Setup column of table book checkout==============================================
        tbcTitle.setCellValueFactory(
                new PropertyValueFactory<CheckOutRecord, String>("titleBook")
        );

        tbcLibrarian.setCellValueFactory(
                new PropertyValueFactory<CheckOutRecord, String>("memberName")
        );

        tbcDueDate.setCellValueFactory(
                new PropertyValueFactory<CheckOutRecord, String>("dueDate")
        );

        tbcImage.setCellValueFactory(
                new PropertyValueFactory<CheckOutRecord, String>("image")
        );


        // Setup column of table member checkout==============================================

        colFirstName.setCellValueFactory(
                new PropertyValueFactory<Member, String>("firstName")
        );

        colLastName.setCellValueFactory(
                new PropertyValueFactory<Member, String>("lastName")
        );


        //Filter CheckOut book==============================================================
        FilteredList<CheckOutRecord> flBook = new FilteredList(checkOutRecords.getCheckOutRecords(), p -> true);//Pass the data to a filtered list
        tableRecord.setItems(flBook);//Set the table's items using the filtered list


        //Filter CheckOut member===========================================================
        FilteredList<Member> flMembers = new FilteredList(checkOutRecords.getListOfMemberCheckOut(), p -> true);//Pass the data to a filtered list
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

            switch ((String) cboChoice.getValue())//Switch on choiceBox value
            {
                case "All": {
                    flBook.setPredicate(p -> p.getMember().toLowerCase().contains(txtFilter.getText().toLowerCase().trim()) ||
                            p.getTitle().toLowerCase().contains(txtFilter.getText().toLowerCase().trim())
                    );//filter table by first name
                    break;
                }
                case "Title": {
                    flBook.setPredicate(p -> p.getTitle().toLowerCase().contains(txtFilter.getText().toLowerCase().trim()));//filter table by first name
                    break;
                }
                case "Member": {
                    flBook.setPredicate(p -> p.getMember().toLowerCase().contains(txtFilter.getText().toLowerCase().trim()));//filter table by first name
                    break;
                }
            }


        });

        //Search Member Checkout============================================================

        txtFillterMember.setPromptText("Search here!");
        txtFillterMember.setOnKeyReleased(keyEvent ->
        {

            switch ((String) cboFilterMember.getValue())//Switch on choiceBox value
            {
                case "All": {
                    flMembers.setPredicate(p -> p.getFirstName().toLowerCase().contains(txtFillterMember.getText().toLowerCase().trim()) ||
                            p.getLastName().toLowerCase().contains(txtFillterMember.getText().toLowerCase().trim())
                    );//filter table by first name
                    break;
                }
                case "FirstName": {
                    flMembers.setPredicate(p -> p.getFirstName().toLowerCase().contains(txtFillterMember.getText().toLowerCase().trim()));//filter table by first name
                    break;
                }
                case "LastName": {
                    flMembers.setPredicate(p -> p.getLastName().toLowerCase().contains(txtFillterMember.getText().toLowerCase().trim()));//filter table by first name
                    break;
                }
            }


        });

        // doubleclick on table checkout member

        tblMemberCheckOut.setRowFactory(tv -> {
            TableRow<Member> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Member rowData = row.getItem();
                    System.out.println(rowData.getFirstName());

                    showBookCheckOutDialog(rowData);
                }
            });
            return row;
        });


    }


    public boolean showBookCheckOutDialog(Member member) {
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
                        
            controller.showDialog(member);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
