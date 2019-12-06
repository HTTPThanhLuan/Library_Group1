package controller;

import dataaccess.Library;
import home.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Member;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainPageController implements Initializable {
    static long nextId = 1;
    // The table's data
    ObservableList<Member> data = FXCollections.observableArrayList();
    // The table and columns
    @FXML
    TableView<Member> memberTbl;
    @FXML
    TableColumn firstname;
    @FXML
    TableColumn lastname;
    @FXML
    TableColumn email;
    @FXML
    TableColumn street;
    // The table and columns
    @FXML
    private VBox pnItems = null;
    @FXML
    private Button pnlMember;
    @FXML
    private Button btnBook;
    @FXML
    private Button btnCheckOutRecord;
    @FXML
    private Button btnBookCheckout;
    @FXML
    private Button btnMember;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Label lableRole;
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
    @FXML
    private HBox listButtonsEditPersion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set up the table data
        firstname.setCellValueFactory(
                new PropertyValueFactory<Member, String>("firstName")
        );
        lastname.setCellValueFactory(
                new PropertyValueFactory<Member, String>("lastName")
        );
        email.setCellValueFactory(
                new PropertyValueFactory<Member, String>("email")
        );
        street.setCellValueFactory(
                new PropertyValueFactory<Member, String>("street")
        );
        loadData();
        checkPermission();
    }

    public void checkPermission() {
        if (SystemController.getInstance().isLibrarian()) {
            listButtonsEditPersion.setVisible(false);
            memberTbl.setVisible(false);
            lableRole.setText("Librarian");
            btnMember.setDisable(true);
            btnBook.setDisable(true);
        } else if (SystemController.getInstance().isAdmin() ) {
            lableRole.setText("Administrator");
            btnCheckOutRecord.setDisable(true);
            btnBookCheckout.setDisable(true);
        } else {
            lableRole.setText("Administrator and Librarian");
        }
    }

    public void loadData() {
        Library.getInstance().getMembers().forEach((key, member) -> {
            data.add(member);
        });
        memberTbl.setItems(data);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        Member member = new Member();
        boolean okClicked = showPersonEditDialog(member);
        if (okClicked) {
            data.add(member);
            Library.getInstance().addMember(member);
        }
    }

    public boolean showPersonEditDialog(Member member) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/MemberEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Member");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(Main.getInstance().getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            MemberEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMember(member);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = memberTbl.getSelectionModel().getSelectedIndex();
        Member selectedMember = memberTbl.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            Library.getInstance().getMembers().remove(selectedMember.getId());
            memberTbl.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(Main.getInstance().getStage());
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
        Member selectedPerson = memberTbl.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = showPersonEditDialog(selectedPerson);
            if (okClicked) {

            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(Main.getInstance().getStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Member Selected");
            alert.setContentText("Please select a Member in the table.");

            alert.showAndWait();
        }
    }

    public void handleClicks(ActionEvent actionEvent) throws Exception {
        if (actionEvent.getSource() == btnMember) {
            Main.getInstance().showMemberView();

        }
        if (actionEvent.getSource() == btnBook) {
            Main.getInstance().showBookOverview();
        }

        if (actionEvent.getSource() == btnCheckOutRecord) {
            Main.getInstance().showCheckOutBookRecord();
        }

        if (actionEvent.getSource() == btnBookCheckout) {
            Main.getInstance().showBookCheckoutScreen();
        }

        if (actionEvent.getSource() == btnSignout) {
            Stage stage = (Stage) btnSignout.getScene().getWindow();
            stage.close();
            Main.getInstance().showLogin();
        }

    }


}
