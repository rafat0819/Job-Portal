package UI;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import Base.bs_DBConnect;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;

public class ui_forgetPasswordForm extends Stage{
	private String Username;
	private String newPassword;
	Alert alert;
	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	public ui_forgetPasswordForm(Stage parentStage){
		BorderPane border = new BorderPane();
		
		
		HBox header = new HBox(2);
		header.setPadding(new Insets(10,10,10,10));
		header.setStyle("-fx-border-color: #000000; -fx-border: 2px;");
		header.setMaxSize(200, 100);
		
		Text txt_CodeTitle = new Text("During registration you had answered a question to reset your password !");
		txt_CodeTitle.setStyle("-fx-font: 11 Georgia;");
		header.getChildren().add(txt_CodeTitle);
			
		GridPane gridpanel = new GridPane();
		gridpanel.setPadding(new Insets(10,10,10,10));
		gridpanel.setHgap(10);
		gridpanel.setVgap(8);
		
		Text txt_Username = new Text("Enter your Username : ");
		GridPane.setConstraints(txt_Username, 0, 0);
		
		TextField txtf_Username = new TextField();
		GridPane.setConstraints(txtf_Username, 0, 1);
		
		Text txt_code = new Text("What is your favourite animal ? ");
		GridPane.setConstraints(txt_code, 1, 0);
		
		TextField txtf_code = new TextField();
		GridPane.setConstraints(txtf_code, 1, 1);
		
		Button btnSubmit = new Button("Submit");
		btnSubmit.setMinSize(100, 20);
		GridPane.setConstraints(btnSubmit, 0, 2);
		
		// Button Action
		btnSubmit.setOnAction(e -> {
			if(CheckCode(txtf_Username.getText().toString(), txtf_code.getText().toString())){
				this.Username = txtf_Username.getText().toString();
				PasswordPrompt();
			}else{
				alert = new Alert(AlertType.WARNING);
    			alert.setTitle("Information");
    			alert.setHeaderText("Failed !");
    			alert.setContentText("You Have Entered Wrong Information - Please try again !");

    			alert.showAndWait();
			}
		});
		
		gridpanel.getChildren().addAll(txt_Username, txtf_Username, txt_code, txtf_code, btnSubmit);
		
		border.setTop(header);
		border.setCenter(gridpanel);
		
		Scene scene = new Scene(border);
		this.setScene(scene);
		this.initStyle(StageStyle.UTILITY);
		this.initModality(Modality.WINDOW_MODAL);
		this.initOwner(parentStage);
		this.setTitle("Forget Password");
		this.centerOnScreen();
		this.setResizable(false);
	}
	
	private void PasswordPrompt(){
		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Reset Password");
		dialog.setHeaderText("Enter your new Password below !");


		// Set the button types.
		ButtonType submitButtonType = new ButtonType("Submit", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(submitButtonType, ButtonType.CANCEL);

		// Create the txtf_newPass and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField txtf_newPass = new TextField();
		txtf_newPass.setPromptText("New Password");
		PasswordField password = new PasswordField();
		password.setPromptText("Re-Type Password");
		Text info = new Text("Wow");
		info.setVisible(false);
		
		Label lblNewPass = new Label("New Password:");
		lblNewPass.setMinSize(20, 10);
		
		Label lblConPass = new Label("Re-Type Password:");
		lblNewPass.setMinSize(100, 10);
		
		grid.add(lblNewPass, 0, 0);
		grid.add(txtf_newPass, 4, 0);
		grid.add(lblConPass, 0, 1);
		grid.add(password, 4, 1);
		grid.add(info, 4, 2);
		
	
		Node submitButton = dialog.getDialogPane().lookupButton(submitButtonType);
		submitButton.setDisable(true);
		
		password.textProperty().addListener((observable, oldValue, newValue) -> {
			if(txtf_newPass.getText().trim().equals("") || password.getText().trim().equals("")){
				info.setText("Fields cannot be empty !");
				info.setVisible(true);
				submitButton.setDisable(true);
        	}else{
        		if (txtf_newPass.getText().toString().equals(password.getText().toString())) {
    		    	info.setText("Matched Successfully !");
    				info.setVisible(true);
    				submitButton.setDisable(false);
    			 }else{
    	    		info.setText("Both field are not matched !");
    				info.setVisible(true);
    				submitButton.setDisable(true);
    			 }
        	}	 
		});
		
		dialog.getDialogPane().setContent(grid);
		
		
		
		// Request focus on the txtf_newPass field by default.
		Platform.runLater(() -> txtf_newPass.requestFocus());
		
		
		
		// Convert the result to a txtf_newPass-password-pair when the submit button is clicked.		
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == submitButtonType) {
		    	return new Pair<>(txtf_newPass.getText(), password.getText());
		    }
		    return null;
		});
		
		Optional<Pair<String, String>> result = dialog.showAndWait();
		result.ifPresent(Password -> {
		    this.newPassword = Password.getKey();
		    UpdatePassword(this.Username.toString(), this.newPassword.toString());
		});			
	}
	
	private boolean CheckCode(String USERNAME, String CODE){
		bs_DBConnect mysqlConnect = new bs_DBConnect();
    	PreparedStatement usrStatement = null;
    	ResultSet rs = null;
    	boolean result = false;
		try{
			
			String usrSQL = "SELECT COUNT(*) AS Count FROM `jp_users` WHERE `usr_username` = ? AND `usr_qna` = ?";
			
			usrStatement = mysqlConnect.connect().prepareStatement(usrSQL);
			
			usrStatement.setString(1, USERNAME.toString());
			usrStatement.setString(2, CODE.toString());
			
			rs = usrStatement.executeQuery();
			
			while(rs.next()){
				if(rs.getInt("Count") > 0){
					result = true;
				}else{
					result = false;
				}
			}
			return result;
		}catch(SQLException ERROR){
			ERROR.printStackTrace();	
		}finally {
			if(rs != null){
				try 
				{
					rs.close();
				} 
				catch (SQLException sqlEx) 
				{
					rs = null;
				}
			}
			if(usrStatement != null){
				try 
				{
					usrStatement.close();
				} 
				catch (SQLException sqlEx) 
				{
					usrStatement = null;
				}
			}		
			mysqlConnect.disconnect();
		}
		return result;
	}
	
	private boolean UpdatePassword(String USERNAME, String NEWPASS){
		bs_DBConnect mysqlConnect = new bs_DBConnect();
    	PreparedStatement usrStatement = null;
    	ResultSet rs = null;
    	boolean result = false;
		try{
			
			String usrSQL = "UPDATE `jp_users` SET `usr_password` = ? WHERE `usr_username` = ?";
			
			usrStatement = mysqlConnect.connect().prepareStatement(usrSQL);
			
			usrStatement.setString(1, NEWPASS.toString());
			usrStatement.setString(2, USERNAME.toString());
			
			usrStatement.executeUpdate();
			
			result = true;
		}catch(SQLException ERROR){
			result = false;
			ERROR.printStackTrace();	
		}finally {
			if(rs != null){
				try 
				{
					rs.close();
				} 
				catch (SQLException sqlEx) 
				{
					rs = null;
				}
			}
			if(usrStatement != null){
				try 
				{
					usrStatement.close();
				} 
				catch (SQLException sqlEx) 
				{
					usrStatement = null;
				}
			}		
			mysqlConnect.disconnect();
		}
		return result;
	}
}

