package UI;

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
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class ui_RegisterForm extends Application{
	Stage window;
	Alert alert;
	int Type;
	
	@Override
    public void start(Stage primaryStage) throws Exception{
		window = primaryStage;
		window.setTitle("JOB PORTAL - Registration");
		window.setOnCloseRequest(e -> {
			CloseMe(window);
		});

        if(Type == 1){
        	window.setScene(appScene());
        }
        if(Type == 2){
        	window.setScene(empScene());
        }
        
        window.centerOnScreen();
        window.setResizable(false);
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    
    public void Display(String UserType){
    	if(UserType == "Applicant"){
    		Type = 1;
    	}else if(UserType == "Employer"){
    		Type = 2;
    	}

    	try {
			this.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private Scene appScene(){
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

        Text Label_header=new Text("Create your Free Applicant Account Now");
        Label_header.setStyle("-fx-font: 40 Georgia;");
        Label_header.setFill(Color.WHITE);
        header.getChildren().addAll(Label_header);

        VBox all=new VBox(20);
        all.setPadding(new Insets(10,10,10,10));

        HBox h0=new HBox(50);
        h0.setStyle("-fx-font: 20 Georgia;");

        VBox v0=new VBox();
        Label Label_Username=new Label("User Name*");
        Label_Username.setTextFill(Color.BLACK);

        TextField Field_Username=new TextField();
        v0.setMaxWidth(Double.MAX_VALUE);
        v0.getChildren().addAll(Label_Username, Field_Username);

        VBox v1=new VBox();
        Label Label_Password=new Label("Password*");
        Label_Password.setTextFill(Color.BLACK);
        PasswordField Field_Password=new PasswordField();
        v1.setMaxWidth(Double.MAX_VALUE);
        v1.getChildren().addAll(Label_Password, Field_Password);


        h0.getChildren().addAll(v0,v1);

        Label Label_Add_Info=new Label("Additional Information");
        Label_Add_Info.setTextFill(Color.BLACK);
        Label_Add_Info.setStyle("-fx-font: 30 Georgia;");

        HBox h1=new HBox(50);
        h1.setStyle("-fx-font: 20 Georgia;");
        VBox v2=new VBox();
        Label Label_First_Name=new Label("First Name");
        Label_First_Name.setTextFill(Color.BLACK);
        TextField Field_First_Name=new TextField();
        v2.getChildren().addAll(Label_First_Name, Field_First_Name);

        VBox v3=new VBox();
        Label Label_Last_Name=new Label("Last Name");
        Label_Last_Name.setTextFill(Color.BLACK);
        TextField Field_Last_Name=new TextField();
        v3.getChildren().addAll(Label_Last_Name, Field_Last_Name);

        h1.getChildren().addAll(v2, v3);


        HBox h2=new HBox(20);
        h2.setStyle("-fx-font: 20 Georgia;");
        Label Label_Gender=new Label("Gender*");
        Label_Gender.setTextFill(Color.BLACK);
        ObservableList<String> options = FXCollections.observableArrayList("Male", "Female");
        ComboBox<String> Combo_Gender = new ComboBox<String>(options);
        h2.getChildren().addAll(Label_Gender, Combo_Gender);

        HBox h3=new HBox(50);
        h3.setStyle("-fx-font: 20 Georgia;");
        VBox v4=new VBox();
        Label Label_Mobile_Number=new Label("Mobile Number");
        Label_Mobile_Number.setTextFill(Color.BLACK);
        TextField Field_Mobile_Number=new TextField();
        v4.getChildren().addAll(Label_Mobile_Number, Field_Mobile_Number);

        VBox v5=new VBox();
        Label Label_Email_Address=new Label("Email Address*");
        Label_Email_Address.setTextFill(Color.BLACK);
        TextField Field_Email_Address=new TextField();
        v5.getChildren().addAll(Label_Email_Address, Field_Email_Address);

        h3.getChildren().addAll(v4, v5);


        all.setStyle("-fx-background-color: #ECF0F1;-fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");


        
        VBox v6=new VBox(5);
        v6.setStyle("-fx-font: 20 Georgia;");
        Label Label_Emergency=new Label("Emergency Question (incase you forgot your password)");
        Label_Emergency.setStyle("-fx-font: 18 Georgia;");
        Label_Emergency.setTextFill(Color.BLACK);
        Label Label_Question=new Label("What is your favourite animal?*");
        Label_Question.setTextFill(Color.BLACK);
        TextField Field_Question=new TextField();
        Field_Question.setMaxWidth(500);
        
        
        HBox h4=new HBox(5);
        Button Button_Back = new Button("<<BACK");
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

        Button Button_Confirm= new Button("CONFIRM>>");
        Button_Confirm.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
        Button_Confirm.setTextFill(Color.WHITE);
        Button_Confirm.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        Button_Confirm.setStyle("-fx-font: 25 Georgia; -fx-background-color: #89A9C9; -fx-background-radius: 10,4,3,5; -fx-background-insets: 2,1,2,0;");
                    }
                });
        //Removing the shadow when the mouse cursor is off
        Button_Confirm.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        Button_Confirm.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
                    }
                });
        Button_Confirm.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        Button_Confirm.setStyle("-fx-font: 25 Georgia; -fx-background-color: #89A9C9; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
                    }
                });
        
        Button_Back.setOnAction(e -> {
        	try {
				new ui_LoginForm().start(new Stage());
			} catch (Exception e1) {
				e1.printStackTrace();
			}finally{
				this.window.close();
			}
        });
        
        
        Button_Confirm.setOnAction(e -> {
        	boolean answer = (Field_Email_Address.getText().indexOf("@") >= 0) && (Field_Email_Address.getText().indexOf(".") >= 0);
			if(!answer){
				alert = new Alert(AlertType.WARNING);
    			alert.setTitle("Information");
    			alert.setHeaderText("Failed !");
    			alert.setContentText("You have entered an invalid email address - Please try again !");

    			alert.showAndWait();
        	}
        	if(Field_Username.getText().trim().equals("") || Field_Password.getText().trim().equals("") || Combo_Gender.getValue().equals("") || Field_Question.getText().trim().equals("")){
        		alert = new Alert(AlertType.WARNING);
    			alert.setTitle("Information");
    			alert.setHeaderText("Failed !");
    			alert.setContentText("Required fields cannot be empty - Please try again !");

    			alert.showAndWait();
    			
        	}else{
        		ApplicantRegProcess(Field_Username.getText().toString(), Field_Password.getText().toString(), false,
        				Field_Question.getText().toString(),
            			Field_First_Name.getText().toString(), Field_Last_Name.getText().toString(), 
            			Combo_Gender.getValue().toString(), Field_Email_Address.getText().toString(), 
            			Field_Mobile_Number.getText().toString());
        	}
        	
        	
        	Field_Username.clear();
        	Field_Password.clear();
        	Field_First_Name.clear();
        	Field_Last_Name.clear();
        	Combo_Gender.valueProperty().set(null);
        	Field_Email_Address.clear();
        	Field_Mobile_Number.clear();
        	Field_Question.clear();
        });
        
        
        Button_Back.setMinWidth(200);
        Button_Confirm.setMinWidth(200);
        h4.getChildren().addAll(Button_Back, Button_Confirm);

        


        v6.getChildren().addAll(Label_Emergency, Label_Question, Field_Question);



        all.getChildren().addAll(h0, Label_Add_Info, h1, h2, h3, v6, h4);

        GridPane.setConstraints(all, 0, 1);

        grid.setStyle("-fx-background: #2C3E50");
        grid.getChildren().addAll(header, all);

        
        Scene scene = new Scene(grid, 800, 700);
        
        return scene;
    }
    
    private void ApplicantRegProcess(String USERNAME, String PASSWORD, boolean STATUS, String QNA, String FIRSTNAME, String LASTNAME, String GENDER, String EMAIL, String MOBILE){
    	bs_UserApplicant newApplicant = new bs_UserApplicant(USERNAME, PASSWORD, STATUS, QNA, FIRSTNAME, LASTNAME, GENDER, EMAIL, MOBILE);
    	
    	if(newApplicant.CheckExistingUsername(newApplicant.get_Username())){
    		if(newApplicant.RegMe()){
    			alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Information");
    			alert.setHeaderText("Success !");
    			alert.setContentText("Successfully Completed Registration !");

    			alert.showAndWait();
    		}else{
    			alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Information");
    			alert.setHeaderText("Failed !");
    			alert.setContentText("Registration Failed, Please try again !");

    			alert.showAndWait();
    		}
    	}else{
    		alert = new Alert(AlertType.ERROR);
			alert.setTitle("Information");
			alert.setHeaderText("Failed !");
			alert.setContentText("Username Already Exists, Please try a different username again !");

			alert.showAndWait();
    	}
    }
    
    
    
    private Scene empScene(){

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

        Text Label_header=new Text("Create your Free Employer Account Now");
        Label_header.setStyle("-fx-font: 40 Georgia;");
        Label_header.setFill(Color.WHITE);
        header.getChildren().addAll(Label_header);

        VBox all=new VBox(20);
        all.setPadding(new Insets(10,10,10,10));

        HBox h0=new HBox(50);
        h0.setStyle("-fx-font: 20 Georgia;");

        VBox v0=new VBox();
        Label Label_Username=new Label("User Name*");
        Label_Username.setTextFill(Color.BLACK);

        TextField Field_Username=new TextField();
        v0.setMaxWidth(Double.MAX_VALUE);
        v0.getChildren().addAll(Label_Username, Field_Username);

        VBox v1=new VBox();
        Label Label_Password=new Label("Password*");
        Label_Password.setTextFill(Color.BLACK);
        PasswordField Field_Password=new PasswordField();
        v1.setMaxWidth(Double.MAX_VALUE);
        v1.getChildren().addAll(Label_Password, Field_Password);


        h0.getChildren().addAll(v0,v1);

        Label Label_Add_Info=new Label("Additional Information");
        Label_Add_Info.setTextFill(Color.BLACK);
        Label_Add_Info.setStyle("-fx-font: 30 Georgia;");


        VBox v2=new VBox();
        v2.setStyle("-fx-font: 20 Georgia;");
        Label Label_Company_Name=new Label("Company Name");
        Label_Company_Name.setTextFill(Color.BLACK);
        TextField Field_Company_Name=new TextField();

        Label Label_Company_Address=new Label("Company Address");
        Label_Company_Address.setTextFill(Color.BLACK);
        TextField Field_Company_Address=new TextField();
        v2.getChildren().addAll(Label_Company_Name, Field_Company_Name, Label_Company_Address, Field_Company_Address);

        HBox h3=new HBox(50);
        h3.setStyle("-fx-font: 20 Georgia;");
        VBox v4=new VBox();
        Label Label_Company_Contact=new Label("Company Contact");
        Label_Company_Contact.setTextFill(Color.BLACK);
        TextField Field_Company_Contact=new TextField();
        v4.getChildren().addAll(Label_Company_Contact, Field_Company_Contact);

        VBox v5=new VBox();
        Label Label_Company_Email=new Label("Company Email*");
        Label_Company_Email.setTextFill(Color.BLACK);
        TextField Field_Company_Email=new TextField();
        v5.getChildren().addAll(Label_Company_Email, Field_Company_Email);

        HBox h6=new HBox(10);
        h6.setStyle("-fx-font: 20 Georgia;");
        Label Label_Company_Website=new Label("Company Website");
        Label_Company_Website.setTextFill(Color.BLACK);
        TextField Field_Company_Website=new TextField();
        h6.getChildren().addAll(Label_Company_Website, Field_Company_Website);

        h3.getChildren().addAll(v4, v5);


        all.setStyle("-fx-background-color: #ECF0F1;-fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");


        VBox v6=new VBox(5);
        v6.setStyle("-fx-font: 20 Georgia;");
        Label Label_Emergency=new Label("Emergency Question (incase you forgot your password)");
        Label_Emergency.setStyle("-fx-font: 18 Georgia;");
        Label_Emergency.setTextFill(Color.BLACK);
        Label Label_Question=new Label("What is your favourite animal? * ");
        Label_Question.setTextFill(Color.BLACK);
        TextField Field_Question=new TextField();
        Field_Question.setMaxWidth(500);
        
        HBox h4=new HBox(5);
        Button Button_Back = new Button("<<BACK");
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

        Button Button_Confirm= new Button("CONFIRM>>");
        Button_Confirm.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
        Button_Confirm.setTextFill(Color.WHITE);
        Button_Confirm.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        Button_Confirm.setStyle("-fx-font: 25 Georgia; -fx-background-color: #89A9C9; -fx-background-radius: 10,4,3,5; -fx-background-insets: 2,1,2,0;");
                    }
                });
        //Removing the shadow when the mouse cursor is off
        Button_Confirm.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        Button_Confirm.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
                    }
                });
        Button_Confirm.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        Button_Confirm.setStyle("-fx-font: 25 Georgia; -fx-background-color: #89A9C9; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
                    }
                });
        
        
        Button_Back.setOnAction(e -> {
        	try {
				new ui_LoginForm().start(new Stage());
			} catch (Exception e1) {
				e1.printStackTrace();
			}finally{
				this.window.close();
			}
        });
        
        
        Button_Confirm.setOnAction(e -> {
        	boolean answer = (Field_Company_Email.getText().indexOf("@") >= 0) && (Field_Company_Email.getText().indexOf(".") >= 0);
			if(!answer){
				alert = new Alert(AlertType.WARNING);
    			alert.setTitle("Information");
    			alert.setHeaderText("Failed !");
    			alert.setContentText("You have entered an invalid email address - Please try again !");

    			alert.showAndWait();
        	}
        	if(Field_Username.getText().trim().equals("") || Field_Password.getText().trim().equals("") || Field_Question.getText().trim().equals("")){
        		alert = new Alert(AlertType.WARNING);
    			alert.setTitle("Information");
    			alert.setHeaderText("Failed !");
    			alert.setContentText("Required fields cannot be empty - Please try again !");

    			alert.showAndWait();
        	}else{
        		EmpRegProcess(Field_Username.getText().toString(), Field_Password.getText().toString(), false, 
        				Field_Question.getText().toString(),
        				Field_Company_Name.getText().toString(), Field_Company_Address.getText().toString(), 
        				Field_Company_Contact.getText().toString(), Field_Company_Email.getText().toString(), 
        				Field_Company_Website.getText().toString());
        	}
        	
        	
        	Field_Username.clear();
        	Field_Password.clear();
        	Field_Company_Name.clear();
        	Field_Company_Address.clear();
        	Field_Company_Contact.clear();
        	Field_Company_Email.clear();
        	Field_Company_Website.clear();
        	Field_Question.clear();
        });
        
        
        
        
        Button_Back.setMinWidth(200);
        Button_Confirm.setMinWidth(200);
        h4.getChildren().addAll(Button_Back, Button_Confirm);

        


        v6.getChildren().addAll(Label_Emergency, Label_Question, Field_Question);



        all.getChildren().addAll(h0, Label_Add_Info, v2, h3, h6, v6, h4);

        GridPane.setConstraints(all, 0, 1);

        grid.setStyle("-fx-background: #2C3E50");
        grid.getChildren().addAll(header, all);

        window.centerOnScreen();
        window.setResizable(false);
        Scene scene = new Scene(grid, 800, 700);
        
        return scene;
    }
    
    private void EmpRegProcess(String USERNAME, String PASSWORD, boolean STATUS, String QNA, String COMNAME, String COMADDRESS, String CONATCT, String EMAIL, String WEBSITE){
    	bs_UserEmployer newEmployer = new bs_UserEmployer(USERNAME, PASSWORD, STATUS, QNA, COMNAME, COMADDRESS, CONATCT, EMAIL, WEBSITE);
    	
    	if(newEmployer.CheckExistingUsername(newEmployer.get_Username())){
    		if(newEmployer.RegMe()){
    			alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Information");
    			alert.setHeaderText("Success !");
    			alert.setContentText("Successfully Completed Registration !");

    			alert.showAndWait();
    		}else{
    			alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Information");
    			alert.setHeaderText("Failed !");
    			alert.setContentText("Registration Failed, Please try again !");

    			alert.showAndWait();
    		}
    	}else{
    		alert = new Alert(AlertType.ERROR);
			alert.setTitle("Information");
			alert.setHeaderText("Failed !");
			alert.setContentText("Username Already Exists, Please try a different username again !");

			alert.showAndWait();
    	}
    }
    public void CloseMe(Stage window){
    	try {
			new ui_LoginForm().start(new Stage());
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally{
			window.close();
		}
    }
}
