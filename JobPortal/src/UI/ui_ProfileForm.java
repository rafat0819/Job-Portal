package UI;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Base.bs_DBConnect;
import Base.bs_UserApplicant;
import Base.bs_UserEmployer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;


public class ui_ProfileForm extends Application{
	Stage window;
	bs_UserApplicant ObjApplicant; 
	bs_UserEmployer ObjEmployer;
	int LoggedUserID;
	Alert alert;
	
	private int Type;
	/**
	 * @return the type
	 */
	public int getType() {
		return Type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		Type = type;
	}



    public void start(Stage primaryStage){ 
        window = primaryStage;
        window.initStyle(StageStyle.UTILITY);
        window.centerOnScreen();
        window.setResizable(false);
        
        window.setTitle("JOB PORTAL - Registration");
        
        if(this.getType() == 1){
            window.setScene(appScene(this.ObjApplicant));
        }
        if(this.getType() == 2){
            window.setScene(empScene(this.ObjEmployer));
        }

        window.setOnCloseRequest(e -> {
        	CloseMe();
        });
        window.show();
    }

	
	public void Display(int LoggedID, int TYPE, bs_UserApplicant ObjApplicant, bs_UserEmployer ObjEmployer){
		this.setType(TYPE);
		this.LoggedUserID = LoggedID;
		if(this.getType() == 1){
			this.ObjApplicant = ObjApplicant;
            //window.setScene(appScene(this.ObjApplicant));
        }
        if(this.getType() == 2){
            //window.setScene(empScene());
            this.ObjEmployer = ObjEmployer;
        }
		
		try{
			this.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public static void main(String[] args) {
        launch(args);
    }

    public Scene appScene(bs_UserApplicant Applicant){
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

        Text Label_header=new Text("Applicant Profile");
        Label_header.setStyle("-fx-font: 40 Georgia;");
        Label_header.setFill(Color.WHITE);
        header.getChildren().addAll(Label_header);

        VBox all=new VBox(20);
        all.setPadding(new Insets(10,20,10,20));


        HBox h0=new HBox(50);
        h0.setStyle("-fx-font: 20 Georgia;");

        VBox v0=new VBox();
        Label Label_First_Name=new Label("First Name");
        Label_First_Name.setTextFill(Color.BLACK);
        TextField Field_First_Name = new TextField();
        if(Applicant.get_FirstName().toString() == null){
        	Applicant.set_FirstName("");;
        }
        Field_First_Name.setText(Applicant.get_FirstName());
        
        v0.getChildren().addAll(Label_First_Name, Field_First_Name);

        VBox v1=new VBox();
        Label Label_Last_Name=new Label("Last Name");
        Label_Last_Name.setTextFill(Color.BLACK);
        TextField Field_Last_Name=new TextField();
        if(Applicant.get_LastName().toString() == null){
        	Applicant.set_LastName("");
        }
        Field_Last_Name.setText(Applicant.get_LastName());
        v1.getChildren().addAll(Label_Last_Name, Field_Last_Name);

        h0.getChildren().addAll(v0, v1);


        HBox h1=new HBox(20);
        h1.setStyle("-fx-font: 20 Georgia;");
        Label Label_Gender=new Label("Gender");
        Label_Gender.setTextFill(Color.BLACK);
        ObservableList<String> options = FXCollections.observableArrayList("Male", "Female", "Others");
        ComboBox<String> Combo_Gender = new ComboBox<>(options);
        if(Applicant.get_Gender().toString() == null){
        	Applicant.set_Gender("");
        }
        Combo_Gender.setValue(Applicant.get_Gender().toString());
        h1.getChildren().addAll(Label_Gender, Combo_Gender);

        HBox h2=new HBox(50);
        h2.setStyle("-fx-font: 20 Georgia;");

        VBox v2=new VBox();
        Label Label_Mobile_Number=new Label("Mobile Number");
        Label_Mobile_Number.setTextFill(Color.BLACK);
        TextField Field_Mobile_Number=new TextField();
        if(Applicant.get_Mobile().toString() == null){
        	Applicant.set_Mobile("");
        }
        Field_Mobile_Number.setText(Applicant.get_Mobile().toString());
        v2.getChildren().addAll(Label_Mobile_Number, Field_Mobile_Number);

        VBox v3=new VBox();
        Label Label_Email_Address=new Label("Email Address");
        Label_Email_Address.setTextFill(Color.BLACK);
        TextField Field_Email_Address=new TextField();
        if(Applicant.get_Email().toString() == null){
        	Applicant.set_Email("");
        }
        Field_Email_Address.setText(Applicant.get_Email().toString());
        v3.getChildren().addAll(Label_Email_Address, Field_Email_Address);

        h2.getChildren().addAll(v2, v3);

        HBox h3=new HBox(5);
        h3.setStyle("-fx-font: 20 Georgia;");
        Label Label_CV=new Label("CV Link");
        Label_CV.setTextFill(Color.BLACK);
        Label_CV.setPrefWidth(100);

        TextField Field_CV=new TextField();
        if(Applicant.get_CV() == null){
        	Applicant.set_CV("");
        }
        Field_CV.setText(Applicant.get_CV().toString());
        Field_CV.setPrefWidth(500);
        h3.getChildren().addAll(Label_CV, Field_CV);

        VBox h5=new VBox(10);
        h5.setStyle("-fx-font: 20 Georgia;");
        Label Label_Edu_BACKG=new Label("Educational Background");
        Label_Edu_BACKG.setTextFill(Color.BLACK);
        TextField Field_Edu_BACKG=new TextField();
        if(Applicant.get_EduBG() == null){
        	Applicant.set_EduBG("");
        }
        Field_Edu_BACKG.setText(Applicant.get_EduBG().toString());
        Field_Edu_BACKG.setPrefSize(450, 100);
        h5.getChildren().addAll(Label_Edu_BACKG, Field_Edu_BACKG);

        VBox h6=new VBox(10);
        h6.setStyle("-fx-font: 20 Georgia;");
        Label Label_About_me=new Label("About Me");
        Label_About_me.setTextFill(Color.BLACK);
        TextField Field_About_me=new TextField();
        if(Applicant.get_AboutMe() == null){
        	Applicant.set_AboutMe("");
        }
        Field_About_me.setText(Applicant.get_AboutMe().toString());
        Field_About_me.setPrefSize(450, 100);
        h6.getChildren().addAll(Label_About_me, Field_About_me);





        all.setStyle("-fx-background-color: #ECF0F1;-fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");


        HBox h4=new HBox(5);
        h4.setAlignment(Pos.CENTER);
        Button Button_Back = new Button("<<BACK");
        Button_Back.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
        Button_Back.setTextFill(Color.WHITE);
        Button_Back.addEventHandler(MouseEvent.MOUSE_PRESSED,
                e -> Button_Back.setStyle("-fx-font: 25 Georgia; -fx-background-color: #89A9C9; -fx-background-radius: 10,4,3,5; -fx-background-insets: 2,1,2,0;"));
        //Removing the shadow when the mouse cursor is off
        Button_Back.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> Button_Back.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;"));
        Button_Back.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> Button_Back.setStyle("-fx-font: 25 Georgia; -fx-background-color: #89A9C9; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;"));

        Button_Back.setOnAction(e -> {
        	CloseMe();
        });
        
        Button Button_Update= new Button("UPDATE");
        Button_Update.setPrefSize(50,50);
        Button_Update.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
        Button_Update.setTextFill(Color.WHITE);
        Button_Update.addEventHandler(MouseEvent.MOUSE_PRESSED,
                e -> Button_Update.setStyle("-fx-font: 25 Georgia; -fx-background-color: #89A9C9; -fx-background-radius: 10,4,3,5; -fx-background-insets: 2,1,2,0;"));
        //Removing the shadow when the mouse cursor is off
        Button_Update.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> Button_Update.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;"));
        Button_Update.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> Button_Update.setStyle("-fx-font: 25 Georgia; -fx-background-color: #89A9C9; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;"));

        Button_Update.setOnAction(e -> {
        	if(CheckOnline()){
        		if(UpdateProfile(new bs_UserApplicant(
            			Field_First_Name.getText().toString(),
            			Field_Last_Name.getText().toString(),
            			Combo_Gender.getValue().toString(),
            			Field_Email_Address.getText().toString(),
            			Field_CV.getText().toString(),
            			Field_Mobile_Number.getText().toString(),	
            			
            			Field_About_me.getText().toString(),
            			Field_Edu_BACKG.getText().toString()
            			))){
            		alert = new Alert(AlertType.INFORMATION);
        			alert.setTitle("Information");
        			alert.setHeaderText("Success !");
        			alert.setContentText("Successfully Updated Your Profile !");

        			alert.showAndWait();
            	}else{
            		alert = new Alert(AlertType.ERROR);
        			alert.setTitle("Information");
        			alert.setHeaderText("Failed !");
        			alert.setContentText("Failed to Update Your Profile !");

        			alert.showAndWait();
            	}
        	}else{
        		alert = new Alert(AlertType.WARNING);
    			alert.setTitle("Information");
    			alert.setHeaderText("Failed !");
    			alert.setContentText("You are not logged In !");
    			
    			alert.showAndWait();
    			
    			this.window.close();
        	}
        });

        Button_Back.setMinWidth(200);
        Button_Update.setMinWidth(200);
        h4.getChildren().addAll(Button_Back, Button_Update);

        all.getChildren().addAll(h0, h1, h2, h3, h5, h6, h4);

        GridPane.setConstraints(all, 0, 1);

        grid.setStyle("-fx-background: #2C3E50");
        grid.getChildren().addAll(header, all);
        
        
        Scene scene = new Scene(grid, 800, 700);

        return scene;
    }
    public void CloseMe(){
    	this.window.close();
    }

    private boolean CheckOnline(){
    	bs_DBConnect mysqlConnect = new bs_DBConnect();
    	PreparedStatement Statement = null;
    	ResultSet rs = null;
    	boolean result = false;
		try{
			
			String usrSQL = "SELECT `usr_status` AS status FROM `jp_users` WHERE `usr_id` = ?";
			
			Statement = mysqlConnect.connect().prepareStatement(usrSQL);
			
			Statement.setInt(1, this.LoggedUserID);
			
			
			rs = Statement.executeQuery();
			
			while(rs.next()){
				if(rs.getBoolean("status") == true){
					result = true;
				}else{
					result = false;
				}
			}

			return result;
		}catch(SQLException ERROR){
			result = false;
			ERROR.printStackTrace();	
		}finally{
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
		return result;
    }
    
    private boolean UpdateProfile(bs_UserApplicant Obj){
    	bs_DBConnect mysqlConnect = new bs_DBConnect();
    	PreparedStatement Statement = null;
    	ResultSet rs = null;
    	boolean result = false;
		try{
			
			String usrSQL = "UPDATE `jp_applicant_users` SET `usr_firstname` = ?, `usr_lastname` = ?, "
					+ "`usr_gender` = ?, `usr_email` = ?, `usr_mobile` = ?, "
					+ "`usr_cv` = ?, `usr_aboutme` = ?, `usr_edubg` = ? "
					+ "WHERE `usr_id` = ?";
			
			Statement = mysqlConnect.connect().prepareStatement(usrSQL);
			
			Statement.setString(1, Obj.get_FirstName().toString());
			Statement.setString(2, Obj.get_LastName().toString());
			Statement.setString(3, Obj.get_Gender().toString());
			Statement.setString(4, Obj.get_Email().toString());
			Statement.setString(5, Obj.get_Mobile().toString());
			Statement.setString(6, Obj.get_CV().toString());
			Statement.setString(7, Obj.get_AboutMe().toString());
			Statement.setString(8, Obj.get_EduBG().toString());
			Statement.setInt(9, this.LoggedUserID);
			
			
			Statement.executeUpdate();
			result = true;
			
			return result;
		}catch(SQLException ERROR){
			result = false;
			ERROR.printStackTrace();	
		}finally{
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
		return result;
    }
    
    

    public Scene empScene(bs_UserEmployer Employer){
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

        Text Label_header=new Text("Company Profile");
        Label_header.setStyle("-fx-font: 40 Georgia;");
        Label_header.setFill(Color.WHITE);
        header.getChildren().addAll(Label_header);

        VBox all=new VBox(20);
        all.setPadding(new Insets(10,20,10,20));

        VBox v0=new VBox();
        v0.setStyle("-fx-font: 20 Georgia;");
        Label Label_Company_Name=new Label("Company  Name");
        Label_Company_Name.setTextFill(Color.BLACK);
        TextField Field_Company_Name=new TextField();
        if(Employer.get_CompnayName().toString() == null){
        	Employer.set_CompnayName("");
        }
        Field_Company_Name.setText(Employer.get_CompnayName().toString());
        v0.getChildren().addAll(Label_Company_Name, Field_Company_Name);

        VBox v1=new VBox();
        v1.setStyle("-fx-font: 20 Georgia;");
        Label Label_Company_Address=new Label("Company Address");
        Label_Company_Address.setTextFill(Color.BLACK);
        TextField Field_Company_Address=new TextField();
        if(Employer.get_CompnayAddress().toString() == null){
        	Employer.set_CompnayAddress("");;
        }
        Field_Company_Address.setText(Employer.get_CompnayAddress().toString());
        Field_Company_Address.setPrefSize(400,100);
        v1.getChildren().addAll(Label_Company_Address, Field_Company_Address);


        HBox h0=new HBox(50);
        h0.setStyle("-fx-font: 20 Georgia;");

        VBox v2=new VBox();
        Label Label_Mobile_Number=new Label("Company Contact");
        Label_Mobile_Number.setTextFill(Color.BLACK);
        TextField Field_Mobile_Number=new TextField();
        if(Employer.get_Contact().toString() == null){
        	Employer.set_Contact("");;
        }
        Field_Mobile_Number.setText(Employer.get_Contact().toString());
        v2.getChildren().addAll(Label_Mobile_Number, Field_Mobile_Number);

        VBox v3=new VBox();
        Label Label_Email_Address=new Label("Company Email");
        Label_Email_Address.setTextFill(Color.BLACK);
        TextField Field_Email_Address=new TextField();
        if(Employer.get_Email().toString() == null){
        	Employer.set_Email("");;
        }
        Field_Email_Address.setText(Employer.get_Email().toString());
        v3.getChildren().addAll(Label_Email_Address, Field_Email_Address);

        h0.getChildren().addAll(v2, v3);

        VBox h1=new VBox(10);
        h1.setStyle("-fx-font: 20 Georgia;");
        Label Label_Company_Details=new Label("Company Details");
        Label_Company_Details.setTextFill(Color.BLACK);

        TextArea Field_Company_Details=new TextArea();
        Field_Company_Details.setPrefWidth(300);
        Field_Company_Details.setPrefSize(450, 100);

    	if(Employer.get_CompnayDesc().toString() == null){
        	Employer.set_CompnayDesc("");
        }
        Field_Company_Details.setText(Employer.get_CompnayDesc().toString());

        h1.getChildren().addAll(Label_Company_Details, Field_Company_Details);

        VBox h2=new VBox(10);
        h2.setStyle("-fx-font: 20 Georgia;");
        Label Label_Company_Website=new Label("Company Website");
        Label_Company_Website.setTextFill(Color.BLACK);
        TextField Field_Company_Website=new TextField();
        if(Employer.get_Website().toString() == null){
        	Employer.set_Website("");;
        }
        Field_Company_Website.setText(Employer.get_Website().toString());

        h2.getChildren().addAll(Label_Company_Website, Field_Company_Website);

        all.setStyle("-fx-background-color: #ECF0F1;-fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");


        HBox h4=new HBox(5);
        h4.setAlignment(Pos.CENTER);
        Button Button_Back = new Button("<<BACK");
        Button_Back.setPrefSize(50,50);
        Button_Back.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
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
        	this.window.close();
        });
        
        Button Button_Update= new Button("UPDATE");
        Button_Update.setPrefSize(50,50);
        Button_Update.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
        Button_Update.setTextFill(Color.WHITE);
        Button_Update.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        Button_Update.setStyle("-fx-font: 25 Georgia; -fx-background-color: #89A9C9; -fx-background-radius: 10,4,3,5; -fx-background-insets: 2,1,2,0;");
                    }
                });
        //Removing the shadow when the mouse cursor is off
        Button_Update.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        Button_Update.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
                    }
                });
        Button_Update.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        Button_Update.setStyle("-fx-font: 25 Georgia; -fx-background-color: #89A9C9; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
                    }
                });

        
        Button_Update.setOnAction(e -> {
        	if(CheckOnline()){
        		if(UpdateProfile(new bs_UserEmployer(
            			Field_Company_Name.getText().toString(),
            			Field_Company_Address.getText().toString(),
            			Field_Mobile_Number.getText().toString(),
            			Field_Email_Address.getText().toString(),
            			Field_Company_Details.getText().toString(),
            			Field_Company_Website.getText().toString()
            			))){
            		alert = new Alert(AlertType.INFORMATION);
        			alert.setTitle("Information");
        			alert.setHeaderText("Success !");
        			alert.setContentText("Successfully Updated Your Profile !");

        			alert.showAndWait();
            	}else{
            		alert = new Alert(AlertType.ERROR);
        			alert.setTitle("Information");
        			alert.setHeaderText("Failed !");
        			alert.setContentText("Failed to Update Your Profile !");

        			alert.showAndWait();
            	}
        	}else{
        		alert = new Alert(AlertType.WARNING);
    			alert.setTitle("Information");
    			alert.setHeaderText("Failed !");
    			alert.setContentText("You are not logged In !");
    			
    			alert.showAndWait();
    			
    			this.window.close();
        	}
        });
        
        
        Button_Back.setMinWidth(200);
        Button_Update.setMinWidth(200);
        h4.getChildren().addAll(Button_Back, Button_Update);

        all.getChildren().addAll( v0, v1, h0, h1, h2, h4);

        GridPane.setConstraints(all, 0, 1);

        grid.setStyle("-fx-background: #2C3E50");
        grid.getChildren().addAll(header, all);

        Scene scene2 = new Scene(grid, 800, 700);

        return scene2;
    }
    
    
    private boolean UpdateProfile(bs_UserEmployer Obj){
    	bs_DBConnect mysqlConnect = new bs_DBConnect();
    	PreparedStatement Statement = null;
    	ResultSet rs = null;
    	boolean result = false;
		try{
			
			String usrSQL = "UPDATE `jp_employer_users` SET `company_name` = ?, `company_address` = ?, "
					+ "`company_contact` = ?, `company_email` = ?, `company_desc` = ?, "
					+ "`company_website` = ? "
					+ "WHERE `usr_id` = ?";
			
			Statement = mysqlConnect.connect().prepareStatement(usrSQL);
			
			Statement.setString(1, Obj.get_CompnayName().toString());
			Statement.setString(2, Obj.get_CompnayAddress().toString());
			Statement.setString(3, Obj.get_Contact().toString());
			Statement.setString(4, Obj.get_Email().toString());
			Statement.setString(5, Obj.get_CompnayDesc().toString());
			Statement.setString(6, Obj.get_Website().toString());
			Statement.setInt(7, this.LoggedUserID);
			
			
			Statement.executeUpdate();
			result = true;
			
			return result;
		}catch(SQLException ERROR){
			result = false;
			ERROR.printStackTrace();	
		}finally{
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
		return result;
    }
    
}
