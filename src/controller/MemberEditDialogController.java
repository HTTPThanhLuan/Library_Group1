package controller;

import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Member;
import model.Role;


public class MemberEditDialogController {

	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField streetField;
	@FXML
	private TextField postalCodeField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField email;

	@FXML
	private CheckBox admin;

	private Stage dialogStage;
	private Member member;
	private boolean okClicked = false;

	
	@FXML
	private void initialize() {
	}

	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	
	public void setMember(Member member) {
		this.member = member;

		firstNameField.setText(member.getFirstName());
		lastNameField.setText(member.getLastName());
		streetField.setText(member.getStreet());
		postalCodeField.setText(member.getPostalCode());
		cityField.setText(member.getCity());
		email.setText(member.getEmail());
	
		
		
	}

	
	/**
	 * Returns true if the user clicked OK, false otherwise.
	 *
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		
		if (isInputValid()) {
			System.out.println(firstNameField.getText());
			member.setFirstName(firstNameField.getText());
			member.setLastName(lastNameField.getText());
			member.setStreet(streetField.getText());
			member.setPostalCode(postalCodeField.getText());
			member.setCity(cityField.getText());
			member.setEmail(email.getText());
			
				
			okClicked = true;
			dialogStage.close();
		}
	}

	
	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	/**
	 * Validates the user input in the text fields.
	 *
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
			errorMessage += "No valid first name!\n";
		}
		if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
			errorMessage += "No valid last name!\n";
		}
		if (streetField.getText() == null || streetField.getText().length() == 0) {
			errorMessage += "No valid street!\n";
		}

		if (cityField.getText() == null || cityField.getText().length() == 0) {
			errorMessage += "No valid city!\n";
		}
		if (email.getText() == null || email.getText().length() == 0) {
			errorMessage += "No email !\n";
		} else {
			String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
					+ "A-Z]{2,7}$";

			Pattern pat = Pattern.compile(emailRegex);
			String emailaddress = email.getText().toString();

			if (!pat.matcher(emailaddress).matches()) {
				errorMessage += "Invalid email format !\n";
			}
			/**if(addNew) {
			MemberController membercontroller= new MemberController();
			ObservableList<Member> data;
			data=membercontroller.getMembers();
			for(Member member:data) {
				if(member.getEmail().equals(emailaddress))
				{
					errorMessage += "This email already exit  !\n";
				}
			}
			}**/
			
		}
		if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
			errorMessage += "No valid postal code!\n";
		} else {

			try {
				Integer.parseInt(postalCodeField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid postal code (must be an integer)!\n";
			}
		}
		

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}
	}	
}
