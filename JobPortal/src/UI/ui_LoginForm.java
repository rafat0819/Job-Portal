package UI;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Base.bs_DBConnect;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ui_LoginForm extends Application{
	private  int LoggedUserID;                                                                                                                                                                                                                                                                                                                                                                                     
	
	Stage primaryStage;
	Alert alert;
	/**
	 * @return the loggedUserID
	 */
	public int getLoggedUserID() {
		return LoggedUserID;
	}
	
	public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception{
    	primaryStage = stage;
    	primaryStage.setTitle("Login Form");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        Text txt_LoginTitle=new Text("LOGIN");
        txt_LoginTitle.setStyle("-fx-font: 40 Georgia;");
        txt_LoginTitle.setFill(Color.WHITE);



        HBox header=new HBox();
        header.setPadding(new Insets(10,10,10,10));
        header.setStyle("-fx-background-color: #34495E; -fx-background-radius: 10,4,3,5;-fx-background-insets: 0,1,2,0;");
        header.getChildren().addAll(txt_LoginTitle);
        header.setAlignment(Pos.BASELINE_CENTER);
        GridPane.setConstraints(header, 0, 0);

        VBox tt=new VBox(2);
        tt.setPadding(new Insets(10, 10, 10, 10));
        tt.setSpacing(10);
        Label lbl_Username = new Label("USER NAME");
        lbl_Username.setStyle("-fx-font: 18 arial;");
        lbl_Username.setTextFill(Color.BLACK);
        TextField txt_Username = new TextField();
        txt_Username.setPromptText("username");
        txt_Username.setStyle("-fx-font: 24 arial; -fx-base: white;");

        Label lbl_Password = new Label("PASSWORD");
        lbl_Password.setStyle("-fx-font: 18 arial;");
        lbl_Password.setTextFill(Color.BLACK);
        PasswordField txt_Password = new PasswordField();
        txt_Password.setPromptText("password");
        txt_Password.setStyle("-fx-font: 24 arial; -fx-base: white;");

        HBox l=new HBox(3);
        
        Button btn_Login = new Button("LOGIN");
        btn_Login.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
        btn_Login.setTextFill(Color.WHITE);
        btn_Login.addEventHandler(MouseEvent.MOUSE_PRESSED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btn_Login.setStyle("-fx-font: 25 Georgia; -fx-background-color: #89A9C9; -fx-background-radius: 10,4,3,5; -fx-background-insets: 2,1,2,0;");
			        }
			});
        
			//Removing the shadow when the mouse cursor is off
        btn_Login.addEventHandler(MouseEvent.MOUSE_EXITED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btn_Login.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
			        }
			});
        btn_Login.addEventHandler(MouseEvent.MOUSE_ENTERED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btn_Login.setStyle("-fx-font: 25 Georgia; -fx-background-color: #89A9C9; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
			        }
			});
        
        // Action on Login Button
        btn_Login.setOnAction(e -> {
			try {
				if(this.LoginProcess(txt_Username.getText().toString(), txt_Password.getText().toString())){
					UpdateStatus();
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information");
					alert.setHeaderText("Success !");
					alert.setContentText("Successfully Logged In !");
					
					alert.showAndWait();
					
					try {
						primaryStage.close();
						new ui_Dashboard().Display(this.getLoggedUserID());
					} catch (Exception ERROR) {
						ERROR.printStackTrace();
					}
				}else{
					alert = new Alert(AlertType.WARNING);
					alert.setTitle("Information");
					alert.setHeaderText("Failed !");
					alert.setContentText("Incorrect Credentials, Please try again !");

					alert.showAndWait();
					
					txt_Username.clear();
					txt_Password.clear();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        });
        
        Button btn_SignUp = new Button("SIGN UP");
        btn_SignUp.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
        btn_SignUp.setTextFill(Color.WHITE);
        btn_SignUp.addEventHandler(MouseEvent.MOUSE_PRESSED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btn_SignUp.setStyle("-fx-font: 25 Georgia; -fx-background-color: #89A9C9; -fx-background-radius: 10,4,3,5; -fx-background-insets: 2,1,2,0;");
			        }
			});
			//Removing the shadow when the mouse cursor is off
        btn_SignUp.addEventHandler(MouseEvent.MOUSE_EXITED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btn_SignUp.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
			        }
			});
        btn_SignUp.addEventHandler(MouseEvent.MOUSE_ENTERED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btn_SignUp.setStyle("-fx-font: 25 Georgia; -fx-background-color: #89A9C9; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
			        }
			});
        
        
        btn_SignUp.setOnAction(e -> {
        	try{
        		SignUpRedirectForm().show();
        	}catch(Exception ERROR){
        		ERROR.printStackTrace();
        	}
        });
        
        Button btn_FPassword = new Button("Forgot password?");
        btn_FPassword.setStyle("-fx-font: 14 Georgia; -fx-background-color: #757575; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
        btn_FPassword.setTextFill(Color.WHITE);
        btn_FPassword.addEventHandler(MouseEvent.MOUSE_PRESSED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btn_FPassword.setStyle("-fx-font: 14 Georgia; -fx-background-color: #D2D6D5; -fx-background-radius: 10,4,3,5; -fx-background-insets: 2,1,2,0;");
			        }
			});
			//Removing the shadow when the mouse cursor is off
        btn_FPassword.addEventHandler(MouseEvent.MOUSE_EXITED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btn_FPassword.setStyle("-fx-font: 14 Georgia; -fx-background-color: #757575; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
			        }
			});
        btn_FPassword.addEventHandler(MouseEvent.MOUSE_ENTERED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btn_FPassword.setStyle("-fx-font: 14 Georgia; -fx-background-color: #D2D6D5; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
			        }
			});
        
        btn_FPassword.setOnAction(e -> {
        	try{
                new ui_forgetPasswordForm(primaryStage).show();
        		//new ui_forgetPasswordForm();
        	}catch(Exception ERROR){
        		ERROR.printStackTrace();
        	}
        });
        
        
        tt.setStyle("-fx-background-color: #ECF0F1;-fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
        l.getChildren().addAll(btn_Login, btn_SignUp);
        tt.getChildren().addAll(lbl_Username, txt_Username, lbl_Password, txt_Password, l, btn_FPassword);
        GridPane.setConstraints(tt, 0, 1);

        grid.setStyle("-fx-background: #2C3E50;");


        grid.getChildren().addAll(header, tt);




        Scene scene = new Scene(grid, 300, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    

    
    private boolean LoginProcess(String USERNAME, String PASSWORD) throws SQLException{
    	bs_DBConnect mysqlConnect = new bs_DBConnect();
    	PreparedStatement usrStatement = null;
    	ResultSet rs = null;
    	boolean result = false;
		try{
			
			String usrSQL = "SELECT `usr_id` AS userID FROM `jp_users` WHERE `usr_username` = ? AND `usr_password` = ?";
			
			usrStatement = mysqlConnect.connect().prepareStatement(usrSQL);
			
			usrStatement.setString(1, USERNAME.toString());
			usrStatement.setString(2, PASSWORD.toString());
			
			rs = usrStatement.executeQuery();
			
			if(rs.next()){
				this.LoggedUserID = rs.getInt("userID");
				result = true;
			}else{
				result = false;
			}
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
    
    private void UpdateStatus(){
    	bs_DBConnect mysqlConnect = new bs_DBConnect();
    	PreparedStatement Statement = null;
    	ResultSet rs = null;
		try{
			
			String usrSQL = "UPDATE `jp_users` SET `usr_status` = ? WHERE `usr_id` = ?";
			
			Statement = mysqlConnect.connect().prepareStatement(usrSQL);
			
			Statement.setBoolean(1, true);;
			Statement.setInt(2, this.LoggedUserID);
			
			Statement.executeUpdate();
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
			if(Statement != null){
				try 
				{
					Statement.close();
				} 
				catch (SQLException sqlEx) 
				{
					Statement = null;
				}
			}		
			mysqlConnect.disconnect();
		}
    }
    
    private Stage SignUpRedirectForm(){
    	Stage window;
        window = primaryStage;
        window.setTitle("Choose Sign Up Options");
        window.setOnCloseRequest(e -> {
        	CloseMe(window);
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(20);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);


        HBox header=new HBox();
        header.setPadding(new Insets(10,10,10,10));
        header.setStyle("-fx-background-color: #34495E; -fx-background-radius: 10,4,3,5;-fx-background-insets: 0,1,2,0;");
        header.setAlignment(Pos.TOP_CENTER);
        GridPane.setConstraints(header, 0, 0);

        Text Label_header=new Text("SIGN UP AS?");
        Label_header.setStyle("-fx-font: 40 Georgia;");
        Label_header.setFill(Color.WHITE);
        header.getChildren().addAll(Label_header);

        VBox all=new VBox(50);
        all.setPadding(new Insets(10,10,10,10));

        VBox v0=new VBox(10);
        v0.setAlignment(Pos.CENTER);
        Button Button_EMPSIGN=new Button("Employer");
        Button_EMPSIGN.setMaxWidth(500);
        Button_EMPSIGN.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
        Button_EMPSIGN.setTextFill(Color.WHITE);
        Button_EMPSIGN.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        Button_EMPSIGN.setStyle("-fx-font: 25 Georgia; -fx-background-color: #89A9C9; -fx-background-radius: 10,4,3,5; -fx-background-insets: 2,1,2,0;");
                    }
                });
        //Removing the shadow when the mouse cursor is off
        Button_EMPSIGN.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        Button_EMPSIGN.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
                    }
                });
        Button_EMPSIGN.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        Button_EMPSIGN.setStyle("-fx-font: 25 Georgia; -fx-background-color: #89A9C9; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
                    }
                });
        
        Button_EMPSIGN.setOnAction(e -> {
        	try{
                new ui_RegisterForm().Display("Employer");
        		window.close();
        	}catch(Exception ERROR){
        		ERROR.printStackTrace();
        	}
        });
        
        
        Button Button_APLSIGN=new Button("Applicant");
        Button_APLSIGN.setMaxWidth(500);
        Button_APLSIGN.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
        Button_APLSIGN.setTextFill(Color.WHITE);
        Button_APLSIGN.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        Button_APLSIGN.setStyle("-fx-font: 25 Georgia; -fx-background-color: #89A9C9; -fx-background-radius: 10,4,3,5; -fx-background-insets: 2,1,2,0;");
                    }
                });
        //Removing the shadow when the mouse cursor is off
        Button_APLSIGN.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        Button_APLSIGN.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
                    }
                });
        Button_APLSIGN.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        Button_APLSIGN.setStyle("-fx-font: 25 Georgia; -fx-background-color: #89A9C9; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
                    }
                });
        
        
        Button_APLSIGN.setOnAction(e -> {
        	try{
                new ui_RegisterForm().Display("Applicant");
        		window.close();
        	}catch(Exception ERROR){
        		ERROR.printStackTrace();
        	}
        });
        
        
        v0.getChildren().addAll(Button_EMPSIGN, Button_APLSIGN);


        all.setStyle("-fx-background-color: #ECF0F1;-fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");


        VBox v1=new VBox(5);
        Button Button_Back = new Button("<<BACK");
        Button_Back.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
        Button_Back.setAlignment(Pos.CENTER);
        Button_Back.setTextFill(Color.WHITE);
        Button_Back.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        Button_Back.setStyle("-fx-font: 25 Georgia; -fx-background-color: #89A9C9; -fx-background-radius: 10,4,3,5; -fx-background-insets: 2,1,2,0;");
                    }
                });
        //Removing the shadow when the mouse cursor is off
        Button_Back.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        Button_Back.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
                    }
                });
        Button_Back.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        Button_Back.setStyle("-fx-font: 25 Georgia; -fx-background-color: #89A9C9; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
                    }
                });

        Button_Back.setOnAction(e -> {
        	CloseMe(window);	
        });
        
        
        Button_Back.setMinWidth(200);
        v1.getChildren().addAll(Button_Back);

        all.getChildren().addAll(v0, v1);

        GridPane.setConstraints(all, 0, 1);

        grid.setStyle("-fx-background: #2C3E50");
        grid.getChildren().addAll(header, all);

        window.centerOnScreen();
        window.setResizable(false);
        Scene scene = new Scene(grid, 500, 400);
        window.setScene(scene);
        
        return window;

    }
    
    public void CloseMe(Stage window){
    	window.close();     
    	try {
			this.start(new Stage());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    }
}
