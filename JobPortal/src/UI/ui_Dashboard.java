package UI;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import Base.bs_DBConnect;
import Base.bs_Job;
import Base.bs_UserApplicant;
import Base.bs_UserEmployer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.BooleanStringConverter;


public class ui_Dashboard extends Application{
	Stage MainWindow;
	Message newMessage;
	Alert alert;
	
	// Class Memebers To Store Informations From Other Forms
	private int LoggedUserID;
	private bs_UserApplicant UserIsApplicant;
	private bs_UserEmployer UserIsEmployer;
	
	//private Object LoggedUser;
	
	private String MainWindow_Title;
	
	
	/**
	 * @return the loggedUserID
	 */
	public int getLoggedUserID() {
		return LoggedUserID;
	}

	/**
	 * @param loggedUserID the loggedUserID to set
	 */
	public void setLoggedUserID(int loggedUserID) {
		LoggedUserID = loggedUserID;
	}

	/**
	 * @return the userIsApplicant
	 */
	public bs_UserApplicant getUserIsApplicant() {
		return UserIsApplicant;
	}

	/**
	 * @param userIsApplicant the userIsApplicant to set
	 */
	public void setUserIsApplicant(bs_UserApplicant userIsApplicant) {
		UserIsApplicant = userIsApplicant;
	}

	/**
	 * @return the userIsEmploer
	 */
	public bs_UserEmployer getUserIsEmploer() {
		return UserIsEmployer;
	}

	/**
	 * @param userIsEmploer the userIsEmploer to set
	 */
	public void setUserIsEmployer(bs_UserEmployer userIsEmploer) {
		UserIsEmployer = userIsEmploer;
	}

	public void start(Stage Window) {
		// Settings for MainWindow
		MainWindow = Window;
		MainWindow.centerOnScreen();
		MainWindow.setResizable(false);


		MainWindow.setScene(mySetScene());
		MainWindow.setTitle(this.getMainWindow_Title());
		if(CheckUserStatus()){
			MainWindow.show();
		}else{
			Platform.exit();
		    System.exit(0);
		}
	}
	
	public void Display(int LoggedUserID){
		this.setLoggedUserID(LoggedUserID);
		
		try{
			this.start(new Stage());
		}catch(Exception e1){
			e1.printStackTrace();
		}	
	}
	
	public Scene mySetScene(){
		Group MainGroup = new Group();
        Scene scene = new Scene(MainGroup, 1000, 800, Color.WHITE);
        
        // Main BorderPane Settings
        BorderPane MainBorder = new BorderPane();
        MainBorder.prefHeightProperty().bind(scene.heightProperty());
        MainBorder.prefWidthProperty().bind(scene.widthProperty());
        
		// Component parts Classes Initialization   
		Header header = new Header();
		Body body = new Body();
		Footer footer = new Footer();
		
		// Set All Messages
		Message newMessage = new Message("Hello ", Color.ALICEBLUE);
		
		
		// Locate Components in MainBorder
		if(CheckUserType() == "Applicant"){
			// Set MainForm Internal Settings
			this.setMainWindow_Title("Welcome To Applicant Dashboard");
			
			// Set Contents
			MainBorder.setTop(header.ApplicantHeaderParent(this.UserIsApplicant, this.UserIsEmployer, this.LoggedUserID));
			MainBorder.setCenter(body.ApplicantBodyParent());
			MainBorder.setBottom(footer.FooterParent(newMessage));
		}else if(CheckUserType() == "Employer"){
			// Set MainForm Internal Settings
			this.setMainWindow_Title("Welcome To Employer Dashboard");
			
			// Set Contents
			MainBorder.setTop(header.EmployerHeaderParent(this.UserIsApplicant, this.UserIsEmployer, this.LoggedUserID));
			body.empID = this.getLoggedUserID();
			MainBorder.setCenter(body.EmployerBodyParent());
			MainBorder.setBottom(footer.FooterParent(newMessage));
		}else{
			Platform.exit();
		    System.exit(0);
		}
		
		
		MainGroup.getChildren().add(MainBorder);
		return scene;
	}
	
	private bs_UserApplicant getProfile(){
    	bs_DBConnect mysqlConnect = new bs_DBConnect();
    	PreparedStatement Statement = null;
    	ResultSet rs = null;
    	bs_UserApplicant Obj = new bs_UserApplicant();
		try{
			
			String usrSQL = "SELECT * FROM `jp_applicant_users` WHERE `usr_id` = ?";
			
			Statement = mysqlConnect.connect().prepareStatement(usrSQL);
			
			Statement.setInt(1, this.LoggedUserID);
			
			rs = Statement.executeQuery();
			
			while(rs.next()){
				Obj.set_FirstName(rs.getString(2));
				Obj.set_LastName(rs.getString(3));
				Obj.set_Gender(rs.getString(4));
				Obj.set_Email(rs.getString(5));
				Obj.set_Mobile(rs.getString(6));
				Obj.set_CV(rs.getString(7));
				Obj.set_AboutMe(rs.getString(8));
				Obj.set_EduBG(rs.getString(9));
			}
			
			return Obj;
		}catch(SQLException ERROR){
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
		return Obj;
    }
	
	private boolean CheckUserStatus(){	
		bs_DBConnect mysqlConnect = new bs_DBConnect();
    	PreparedStatement usrStatement = null;
    	ResultSet rs = null;
    	boolean result = false;
		try{
			
			String usrSQL = "SELECT `usr_status` AS status FROM `jp_users` WHERE `usr_id` = ?";
			
			usrStatement = mysqlConnect.connect().prepareStatement(usrSQL);
			
			usrStatement.setInt(1, this.LoggedUserID);
			
			rs = usrStatement.executeQuery();
			
			while(rs.next()){
				if(rs.getBoolean("status") == true){
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
	
	private String CheckUserType(){
		String TYPE = new String("");
		
		if(CheckApplicant() == true){
			this.setUserIsEmployer(null);
			this.setUserIsApplicant(CreateApplicant());
			TYPE = "Applicant";
		}
		if(CheckEmployer() == true){
			this.setUserIsApplicant(null);
			this.setUserIsEmployer(CreateEmployer());
			TYPE = "Employer";
		}
		
		return TYPE;
	}
	
	private boolean CheckApplicant(){
		bs_DBConnect mysqlConnect = new bs_DBConnect();
    	PreparedStatement usrStatement = null;
    	ResultSet rs = null;
    	boolean result = false;
    	try{
			
			String usrSQL = "SELECT COUNT(*) AS existedApp FROM `jp_applicant_users` WHERE `usr_id` = ?";
			
			usrStatement = mysqlConnect.connect().prepareStatement(usrSQL);
			
			usrStatement.setInt(1, this.LoggedUserID);
			
			rs = usrStatement.executeQuery();
			
			while(rs.next()){
				if(rs.getInt("existedApp") > 0){
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
	
	private boolean CheckEmployer(){
		bs_DBConnect mysqlConnect = new bs_DBConnect();
    	PreparedStatement usrStatement = null;
    	ResultSet rs = null;
    	boolean result = false;
    	try{
			
			String usrSQL = "SELECT COUNT(*) AS existedEmp FROM `jp_employer_users` WHERE `usr_id` = ?";
			
			usrStatement = mysqlConnect.connect().prepareStatement(usrSQL);
			
			usrStatement.setInt(1, this.LoggedUserID);
			
			rs = usrStatement.executeQuery();
			
			while(rs.next()){
				if(rs.getInt("existedEmp") > 0){
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
	
	private bs_UserApplicant CreateApplicant(){
		bs_DBConnect mysqlConnect = new bs_DBConnect();
    	PreparedStatement usrStatement = null;
    	ResultSet rs = null;
    	
    	bs_UserApplicant newApplicant = new bs_UserApplicant();
    	try{
			
			String usrSQL = "SELECT `usr_firstname`, `usr_lastname`, `usr_gender`, `usr_email`,"
					+ " `usr_mobile`, `usr_cv`, `usr_aboutme`, `usr_edubg` "
					+ "FROM `jp_applicant_users` WHERE `usr_id` = ?";
			
			usrStatement = mysqlConnect.connect().prepareStatement(usrSQL);
			
			usrStatement.setInt(1, this.LoggedUserID);
			
			rs = usrStatement.executeQuery();
			
			while(rs.next()){
				newApplicant.set_FirstName(rs.getString(1));
				newApplicant.set_LastName(rs.getString(2));
				newApplicant.set_Gender(rs.getString(3));
				newApplicant.set_Email(rs.getString(4));
				newApplicant.set_Mobile(rs.getString(5));
				newApplicant.set_CV(rs.getString(6));
				newApplicant.set_AboutMe(rs.getString(7));
				newApplicant.set_EduBG(rs.getString(8));
			}
			
			return newApplicant;
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
		return newApplicant;
	}

	private bs_UserEmployer CreateEmployer(){
		bs_DBConnect mysqlConnect = new bs_DBConnect();
    	PreparedStatement usrStatement = null;
    	ResultSet rs = null;
    	
    	bs_UserEmployer newEmployer = new bs_UserEmployer();
    	try{
			
			String usrSQL = "SELECT `company_name`, `company_address`, `company_contact`, "
					+ "`company_email`, `company_desc`, `company_website` "
					+ "FROM `jp_employer_users` WHERE `usr_id` = ?";
			
			usrStatement = mysqlConnect.connect().prepareStatement(usrSQL);
			
			usrStatement.setInt(1, this.LoggedUserID);
			
			rs = usrStatement.executeQuery();
			
			while(rs.next()){
				newEmployer.set_CompnayName(rs.getString(1));;
				newEmployer.set_CompnayAddress(rs.getString(2));
				newEmployer.set_Contact(rs.getString(3));
				newEmployer.set_Email(rs.getString(4));
				newEmployer.set_CompnayDesc(rs.getString(5));
				newEmployer.set_Website(rs.getString(6));
			}
			
			return newEmployer;
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
		return newEmployer;
	}
	
	
	class Header{
		public VBox ApplicantHeaderParent(bs_UserApplicant APPLICANT, bs_UserEmployer EMPLOYER, int USERID){
			final Pane leftSpacer = new Pane();
	        HBox.setHgrow(
	                leftSpacer,
	                Priority.SOMETIMES
	        );

	        final Pane rightSpacer = new Pane();
	        HBox.setHgrow(
	                rightSpacer,
	                Priority.SOMETIMES
	        );
	        
	        Region spacer = new Region();
	        spacer.setStyle("-fx-padding: 0 5.417em 0 0;");
	        
			VBox exContainer = new VBox();
			//exContainer.setVgrow(child, value);
			exContainer.setMinSize(1000, 50);
			//exContainer.setStyle("-fx-padding: 10px;-fx-border-color: #000000;-fx-border-width: 5px;");
			exContainer.setStyle("-light-black: rgb(74, 75, 78);" +
							    "-dark-highlight: rgb(87, 89, 92);" + 
							    "-dark-black: rgb(39, 40, 40);" + 
							    "-darkest-black: rgb(5, 5, 5);" + 
							    "-mid-gray: rgb(216, 222, 227);" + 
							    "-fx-background-color: -mid-gray;");
			
			
			HBox buttonBar = new HBox();
			buttonBar.setMinSize(1000, 30);
	        buttonBar.setStyle("-fx-background-color:-darkest-black,-dark-highlight,linear-gradient(to bottom, -light-black 2%, -dark-black 98%);" +
							    "-fx-background-insets: 0, 1 1 1 0, 2 1 1 1;" + 
							    "-fx-background-radius: 0;" + 
							    "-fx-padding: 0.4em 1.833333em 0.4em 1.833333em;");
			
	        Button btn_notification = new Button();
	        btn_notification.setText("0");
	        btn_notification.setStyle("-fx-font: 13px Tahoma;" +
									    "-fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);" + 
									    "-fx-stroke: black;" + 
									    "-fx-stroke-width: 1;-fx-background-insets: 0, 1, 2 1 1 1;-fx-background-radius: 3 0 0 3, 2 0 0 2, 2 0 0 2;");
	        
			Button btn_Username = new Button(APPLICANT.get_FirstName().toString() + APPLICANT.get_LastName().toString());
			Button btn_LogOut = new Button();
			btn_Username.setStyle("");
			Image imageLogout = new Image(getClass().getResourceAsStream("/btn_logout.png"));
			btn_LogOut.setGraphic(new ImageView(imageLogout));
			btn_LogOut.setStyle("-fx-background-insets: 0, 1 1 1 0, 2 1 1 1;-fx-background-radius: 0 3 3 0, 0 2 2 0, 0 2 2 0;");
			
			
			//Adding the shadow when the mouse cursor is on
			btn_notification.addEventHandler(MouseEvent.MOUSE_PRESSED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btn_notification.setStyle("-fx-background-color:-darkest-black," +
								        "rgb(55, 57, 58)," + 
								        "linear-gradient(to top, -light-black 2%, -dark-black 98%);");
			        }
			});
			//Removing the shadow when the mouse cursor is off
			btn_notification.addEventHandler(MouseEvent.MOUSE_EXITED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btn_notification.setStyle(null);
			        }
			});
			
			
			btn_notification.setOnAction(e -> {
				alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Information");
    			alert.setHeaderText("Notification System");
    			alert.setContentText("Coming Soon ! ");

    			alert.showAndWait();
			});
			
			btn_Username.addEventHandler(MouseEvent.MOUSE_PRESSED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btn_Username.setStyle("-fx-background-color:-darkest-black," +
								        "rgb(55, 57, 58)," + 
								        "linear-gradient(to top, -light-black 2%, -dark-black 98%);");
			        }
			});
			//Removing the shadow when the mouse cursor is off
			btn_Username.addEventHandler(MouseEvent.MOUSE_EXITED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btn_Username.setStyle(null);
			        }
			});
			
			
			btn_Username.setOnAction(e -> {				
				new ui_ProfileForm().Display(USERID, 1, getProfile(), EMPLOYER);
			});
			
			
			btn_LogOut.addEventHandler(MouseEvent.MOUSE_PRESSED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btn_LogOut.setStyle("-fx-background-color:-darkest-black," +
								        "rgb(55, 57, 58)," + 
								        "linear-gradient(to top, -light-black 2%, -dark-black 98%);");
			        }
			});
			//Removing the shadow when the mouse cursor is off
			btn_LogOut.addEventHandler(MouseEvent.MOUSE_EXITED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btn_LogOut.setStyle(null);
			        }
			});
			
			MainWindow.setOnCloseRequest(e -> {
				if(APPLICANT.LogMeOut(USERID)){
					alert = new Alert(AlertType.INFORMATION);
	    			alert.setTitle("Information");
	    			alert.setHeaderText("Success !");
	    			alert.setContentText("Successfully Logged Out !");

	    			alert.showAndWait();
	    			
	    			try{
	    				new ui_LoginForm().start(new Stage());
	    			}catch(Exception ERROR){
	    				ERROR.printStackTrace();
	    			}
				}else{
					alert = new Alert(AlertType.WARNING);
	    			alert.setTitle("Information");
	    			alert.setHeaderText("Failed !");
	    			alert.setContentText("Check your Internet Connection !");

	    			alert.showAndWait();
				}
			});
			
			btn_LogOut.setOnAction(e -> {
				if(APPLICANT.LogMeOut(USERID)){
					alert = new Alert(AlertType.INFORMATION);
	    			alert.setTitle("Information");
	    			alert.setHeaderText("Success !");
	    			alert.setContentText("Successfully Logged Out !");

	    			alert.showAndWait();
	    			
	    			try{
	    				MainWindow.close();
	    				//Platform.exit();
	    				new ui_LoginForm().start(new Stage());
	    			}catch(Exception ERROR){
	    				ERROR.printStackTrace();
	    			}
				}else{
					alert = new Alert(AlertType.WARNING);
	    			alert.setTitle("Information");
	    			alert.setHeaderText("Failed !");
	    			alert.setContentText("Check your Internet Connection !");

	    			alert.showAndWait();
				}
			});
			
			
			Image imageMain = new Image(getClass().getResourceAsStream("/logo_applicant.png"));
			ImageView Logo = new ImageView(imageMain);
			Logo.setFitWidth(150);
			Logo.setFitHeight(40);
			
			buttonBar.getChildren().addAll(Logo, rightSpacer, btn_notification, btn_Username, btn_LogOut);
			
			ToolBar toolbar = new ToolBar();
			toolbar.setMinSize(1000, 30);
			//toolbar.setStyle("-fx-background-color: linear-gradient(to bottom, #b5bcc6, #dee3e4);-fx-background-radius: 5px;-fx-border-color: #000000;-fx-border-width: 2px;-fx-border-radius: 5px;");
			toolbar.setStyle("-fx-base: -dark-black;" +
							 "-fx-font-size: 10pt;" +
							 "-fx-background-color: linear-gradient(to bottom, derive(-fx-base,-30%), derive(-fx-base,-60%)), linear-gradient(to bottom, -light-black 2%, -dark-black 98%);" +    
							 "-fx-background-insets: 0, 0 0 1 0;" +
							 "-fx-padding: .9em 0.416667em .9em 0.416667em;" +
							 "-fx-effect: dropshadow(two-pass-box,black,5,.2,0,0);");
			//toolbar.getItems().addAll(rightSpacer, lbl_Greetings, lbl_Username);
			toolbar.getItems().addAll(buttonBar);
			
			exContainer.getChildren().add(toolbar);
			return exContainer;
		}
		
		public VBox EmployerHeaderParent(bs_UserApplicant APPLICANT, bs_UserEmployer Employer, int USERID){
			final Pane leftSpacer = new Pane();
	        HBox.setHgrow(
	                leftSpacer,
	                Priority.SOMETIMES
	        );

	        final Pane rightSpacer = new Pane();
	        HBox.setHgrow(
	                rightSpacer,
	                Priority.SOMETIMES
	        );
	        
	        Region spacer = new Region();
	        spacer.setStyle("-fx-padding: 0 5.417em 0 0;");
	        
			VBox exContainer = new VBox();
			//exContainer.setVgrow(child, value);
			exContainer.setMinSize(1000, 50);
			//exContainer.setStyle("-fx-padding: 10px;-fx-border-color: #000000;-fx-border-width: 5px;");
			exContainer.setStyle("-light-black: rgb(74, 75, 78);" +
							    "-dark-highlight: rgb(87, 89, 92);" + 
							    "-dark-black: rgb(39, 40, 40);" + 
							    "-darkest-black: rgb(5, 5, 5);" + 
							    "-mid-gray: rgb(216, 222, 227);" + 
							    "-fx-background-color: -mid-gray;");
			
			
			HBox buttonBar = new HBox();
			buttonBar.setMinSize(1000, 30);
	        buttonBar.setStyle("-fx-background-color:-darkest-black,-dark-highlight,linear-gradient(to bottom, -light-black 2%, -dark-black 98%);" +
							    "-fx-background-insets: 0, 1 1 1 0, 2 1 1 1;" + 
							    "-fx-background-radius: 0;" + 
							    "-fx-padding: 0.4em 1.833333em 0.4em 1.833333em;");
			
	        Button btn_notification = new Button();
	        btn_notification.setText("0");
	        btn_notification.setStyle("-fx-font: 13px Tahoma;" +
									    "-fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);" + 
									    "-fx-stroke: black;" + 
									    "-fx-stroke-width: 1;-fx-background-insets: 0, 1, 2 1 1 1;-fx-background-radius: 3 0 0 3, 2 0 0 2, 2 0 0 2;");
	        
			Button btn_Username = new Button(Employer.get_CompnayName().toString());
			Button btn_LogOut = new Button();
			btn_Username.setStyle("");
			Image imageLogout = new Image(getClass().getResourceAsStream("/btn_logout.png"));
			btn_LogOut.setGraphic(new ImageView(imageLogout));
			btn_LogOut.setStyle("-fx-background-insets: 0, 1 1 1 0, 2 1 1 1;-fx-background-radius: 0 3 3 0, 0 2 2 0, 0 2 2 0;");
			
			
			//Adding the shadow when the mouse cursor is on
			btn_notification.addEventHandler(MouseEvent.MOUSE_PRESSED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btn_notification.setStyle("-fx-background-color:-darkest-black," +
								        "rgb(55, 57, 58)," + 
								        "linear-gradient(to top, -light-black 2%, -dark-black 98%);");
			        }
			});
			//Removing the shadow when the mouse cursor is off
			btn_notification.addEventHandler(MouseEvent.MOUSE_EXITED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btn_notification.setStyle(null);
			        }
			});
			
			btn_notification.setOnAction(e -> {
				alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Information");
    			alert.setHeaderText("Notification System");
    			alert.setContentText("Coming Soon ! ");

    			alert.showAndWait();
			});
			
			btn_Username.addEventHandler(MouseEvent.MOUSE_PRESSED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btn_Username.setStyle("-fx-background-color:-darkest-black," +
								        "rgb(55, 57, 58)," + 
								        "linear-gradient(to top, -light-black 2%, -dark-black 98%);");
			        }
			});
			//Removing the shadow when the mouse cursor is off
			btn_Username.addEventHandler(MouseEvent.MOUSE_EXITED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btn_Username.setStyle(null);
			        }
			});
			
			
			btn_Username.setOnAction(e -> {
				new ui_ProfileForm().Display(USERID, 2, APPLICANT, CreateEmployer());
			});
			
			
			btn_LogOut.addEventHandler(MouseEvent.MOUSE_PRESSED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btn_LogOut.setStyle("-fx-background-color:-darkest-black," +
								        "rgb(55, 57, 58)," + 
								        "linear-gradient(to top, -light-black 2%, -dark-black 98%);");
			        }
			});
			//Removing the shadow when the mouse cursor is off
			btn_LogOut.addEventHandler(MouseEvent.MOUSE_EXITED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btn_LogOut.setStyle(null);
			        }
			});
			
			btn_LogOut.setOnAction(e -> {
				if(Employer.LogMeOut(USERID)){
					alert = new Alert(AlertType.INFORMATION);
	    			alert.setTitle("Information");
	    			alert.setHeaderText("Success !");
	    			alert.setContentText("Successfully Logged Out !");

	    			alert.showAndWait();
	    			
	    			try{
	    				MainWindow.close();
	    				new ui_LoginForm().start(new Stage());
	    			}catch(Exception ERROR){
	    				ERROR.printStackTrace();
	    			}
				}else{
					alert = new Alert(AlertType.WARNING);
	    			alert.setTitle("Information");
	    			alert.setHeaderText("Failed !");
	    			alert.setContentText("Check your Internet Connection !");

	    			alert.showAndWait();
				}
			});
			
			Image imageMain = new Image(getClass().getResourceAsStream("/logo_employer.png"));
			ImageView Logo = new ImageView(imageMain);
			Logo.setFitWidth(150);
			Logo.setFitHeight(40);
			
			buttonBar.getChildren().addAll(Logo, rightSpacer, btn_notification, btn_Username, btn_LogOut);
			
			ToolBar toolbar = new ToolBar();
			toolbar.setMinSize(1000, 30);
			//toolbar.setStyle("-fx-background-color: linear-gradient(to bottom, #b5bcc6, #dee3e4);-fx-background-radius: 5px;-fx-border-color: #000000;-fx-border-width: 2px;-fx-border-radius: 5px;");
			toolbar.setStyle("-fx-base: -dark-black;" +
							 "-fx-font-size: 10pt;" +
							 "-fx-background-color: linear-gradient(to bottom, derive(-fx-base,-30%), derive(-fx-base,-60%)), linear-gradient(to bottom, -light-black 2%, -dark-black 98%);" +    
							 "-fx-background-insets: 0, 0 0 1 0;" +
							 "-fx-padding: .9em 0.416667em .9em 0.416667em;" +
							 "-fx-effect: dropshadow(two-pass-box,black,5,.2,0,0);");
			//toolbar.getItems().addAll(rightSpacer, lbl_Greetings, lbl_Username);
			toolbar.getItems().addAll(buttonBar);
			
			exContainer.getChildren().add(toolbar);
			return exContainer;
		}
	}
	
	
	class Body{
		int empID;
		VBox tabJobs_vBox = new VBox(1);
		ComboBox<String> txtf_SearchByCat = new ComboBox<String>();
		BorderPane childBorder = new BorderPane();
		TableView<bs_Job> table;
		
		public VBox ApplicantBodyParent(){
			VBox exContainer = new VBox();
			exContainer.setMinSize(1000, 500);
			
			TabPane tabPane = new TabPane();
			tabPane.setStyle("" +
								"-fx-background-insets: 0 1 0 1,0,0;");
			
		    
		    Tab jobsTab = new Tab("Find Jobs");
		    
		    //jobsTab.setStyle("-fx-background-color: #e6e6e6;-fx-alignment: CENTER;-fx-text-fill: #828282;-fx-font-size: 12px;-fx-font-weight: bold;");
		    
		    //Tab historyTab = new Tab("Applied History");
		    
			if(!tabPane.getTabs().contains(jobsTab)) //  && !tabPane.getTabs().contains(historyTab)
			{
				tabPane.getTabs().add(jobsTab);
				//tabPane.getTabs().add(historyTab);
				
			}
			
			jobsTab.setOnCloseRequest(e -> {
				e.consume();
			});
			
			/*if(jobsTab.isSelected()){
				jobsTab.setStyle("-fx-background-color: #3c3c3c;-fx-alignment: CENTER;-fx-text-fill: #FFFFFF;");
				//tabPane.setStyle("-fx-background-color: #3c3c3c;");
			}*/
			
			tabPane.getSelectionModel().select(jobsTab);
			
			
			// Contents Adding
			ToolBar separator = new ToolBar();
			separator.setPrefSize(5, 800);
			separator.setStyle("-light-black: rgb(74, 75, 78);" +
							    "-dark-highlight: rgb(87, 89, 92);" + 
							    "-dark-black: rgb(39, 40, 40);" + 
							    "-darkest-black: rgb(5, 5, 5);" + 
							    "-mid-gray: rgb(216, 222, 227);" + 
							    "-fx-background-color: -mid-gray;-fx-background-color:-darkest-black,-dark-highlight,linear-gradient(to bottom, -light-black 2%, -dark-black 98%);" +
							    "-fx-background-insets: 0, 1 1 1 0, 2 1 1 1;" + 
							    "-fx-background-radius: 0;" + 
							    "-fx-padding: 0.4em 1.833333em 0.4em 1.833333em;");
			
			BorderPane newBorder = new BorderPane();
			
			ScrollPane jobsScrollPane = new ScrollPane();
			jobsScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
			jobsScrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			
			
			
			// ---------------------------------------------------------
			tabJobs_vBox = getJobs();
			
			// -------------------------------------------------------------
			
			jobsScrollPane.setContent(tabJobs_vBox);
			
			HBox searchBox = new HBox();
			searchBox.setPrefSize(500, 800);
			searchBox.setStyle("-fx-padding: 5px;");
			
			// Adding contents to leftside (Search)
			GridPane childrenGrid = new GridPane();
			childrenGrid.setPadding(new Insets(10, 10, 10, 10));
			childrenGrid.setVgap(10);
			childrenGrid.setHgap(10);
			childrenGrid.setAlignment(Pos.CENTER);
			
			Label txt_Title = new Label("Search Jobs - ");
			txt_Title.setStyle("-fx-font-size: 20px");
			
			Label txt_SearchByCat = new Label("Search By Category : ");
			txtf_SearchByCat = getCategories();
			//txtf_SearchByCat.getItems().add("Any");
	
			
			
			Label txt_exp = new Label("Search By Minimum Experience Required : ");
			TextField txtf_SearchByExp = new TextField();
			
			Button btn_Search = new Button("Search");
			Button btn_Reset = new Button("Reset");
			
			childrenGrid.add(txt_Title, 0, 0);
			childrenGrid.add(txt_SearchByCat, 0, 1);
			childrenGrid.add(txtf_SearchByCat, 0, 2);
			childrenGrid.add(txt_exp, 0, 3);
			childrenGrid.add(txtf_SearchByExp, 0, 4);
			childrenGrid.add(btn_Search, 0, 5);
			childrenGrid.add(btn_Reset, 0, 6);
			
			btn_Reset.setOnAction(e -> {
				tabJobs_vBox= null;
				tabJobs_vBox = getJobs();
				jobsScrollPane.setContent(tabJobs_vBox);
				
				txtf_SearchByCat.setValue(null);
				txtf_SearchByExp.clear();
			});
			
			btn_Search.setOnAction(e -> {
				boolean isMyComboBoxEmpty = txtf_SearchByCat.getSelectionModel().isEmpty();
				if(!isMyComboBoxEmpty && !txtf_SearchByExp.getText().trim().equals("")){
					tabJobs_vBox = null;
					tabJobs_vBox = getJobsBySearch(txtf_SearchByCat.getValue().toString(), Integer.parseInt(txtf_SearchByExp.getText()));
					jobsScrollPane.setContent(tabJobs_vBox);
				}else{
					alert = new Alert(AlertType.WARNING);
	    			alert.setTitle("Information");
	    			alert.setHeaderText("Failed !");
	    			alert.setContentText("Search Fields cannot be empty - Please try again !");

	    			alert.showAndWait();
				}
			});
			
			
			searchBox.getChildren().add(childrenGrid);
			
			newBorder.setCenter(separator);
			newBorder.setLeft(jobsScrollPane);
			newBorder.setRight(searchBox);
			jobsTab.setContent(newBorder);
			
			exContainer.getChildren().add(tabPane);
			return exContainer;
		}
		
		private VBox getJobs(){
			VBox trunc = new VBox();
			bs_DBConnect mysqlConnect = new bs_DBConnect();
	    	PreparedStatement Statement = null;
	    	ResultSet rs = null;
	    	ObservableList<VBox> vList;
			try{
				
				String usrSQL = "SELECT * FROM `jp_jobs`";
				
				Statement = mysqlConnect.connect().prepareStatement(usrSQL);
				
				rs = Statement.executeQuery();
				
				
				while(rs.next()){
					JVParent JobViewer = new JVParent(new bs_Job(
							rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getDate(4),
							rs.getDate(5),
							rs.getBoolean(6),
							rs.getInt(7),
							rs.getInt(8),
							rs.getInt(9),
							rs.getInt(10)
							));
					
					vList = FXCollections.observableArrayList (JobViewer.createParent("#b5bcc6"));
					vList.forEach(trunc.getChildren()::add);
				}
				
				return trunc;
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
			return trunc;
		}
		
		private VBox getJobsBySearch(String VALUECAT, int VALUEEXP){
			VBox trunc = new VBox();
			bs_DBConnect mysqlConnect = new bs_DBConnect();
	    	PreparedStatement Statement = null;
	    	ResultSet rs = null;
	    	ObservableList<VBox> vList;
			try{
				
				String usrSQL = "SELECT * FROM `jp_jobs` WHERE (`job_minexp` <= ? AND `cat_id` = (SELECT `cat_id` FROM `jp_categories` WHERE `cat_name` = ?))";
				
				Statement = mysqlConnect.connect().prepareStatement(usrSQL);
				
				Statement.setInt(1, VALUEEXP);
				Statement.setString(2, VALUECAT.toString());
				
				rs = Statement.executeQuery();
				
				
				while(rs.next()){
					JVParent JobViewer = new JVParent(new bs_Job(
							rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getDate(4),
							rs.getDate(5),
							rs.getBoolean(6),
							rs.getInt(7),
							rs.getInt(8),
							rs.getInt(9),
							rs.getInt(10)
							));
					
					vList = FXCollections.observableArrayList (JobViewer.createParent("#b5bcc6"));
					vList.forEach(trunc.getChildren()::add);
				}
				
				return trunc;
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
			return trunc;
		}
		
		HBox newBox;
		
		public VBox EmployerBodyParent(){
			Tab jobsTab = new Tab("My Posted Jobs");
			final Pane rightSpacer = new Pane();
	        HBox.setHgrow(
	                rightSpacer,
	                Priority.SOMETIMES
	        );
			VBox exContainer = new VBox();
			exContainer.setMinSize(1000, 500);
			
			TabPane tabPane = new TabPane();
			tabPane.setStyle("" +
								"-fx-background-insets: 0 1 0 1,0,0;");
			
		    
			// ------------------------------------------------------
			BorderPane border = new BorderPane();
	        border.setPadding(new Insets(20, 0, 20, 20));

	        Button btnAdd = new Button("Add");
	        btnAdd.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
	        Button btnSave = new Button("Refresh");
	        btnSave.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");


	        btnAdd.setPrefWidth(120);
	        btnSave.setPrefWidth(120);
	        btnAdd.addEventHandler(MouseEvent.MOUSE_ENTERED,
	                new EventHandler<MouseEvent>() {
	                    @Override public void handle(MouseEvent e) {
	                        btnAdd.setStyle("-fx-font: 25 Georgia; -fx-background-color: #89A9C9; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
	                    }
	                });
	        btnAdd.addEventHandler(MouseEvent.MOUSE_PRESSED,
	                new EventHandler<MouseEvent>() {
	                    @Override public void handle(MouseEvent e) {
	                        btnAdd.setStyle("-fx-font: 25 Georgia; -fx-background-color: #89A9C9; -fx-background-radius: 10,4,3,5; -fx-background-insets: 2,1,2,0;");
	                    }
	                });

	        btnAdd.addEventHandler(MouseEvent.MOUSE_EXITED,
	                new EventHandler<MouseEvent>() {
	                    @Override public void handle(MouseEvent e) {
	                        btnAdd.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
	                    }
	                });

	        
	        btnAdd.setOnAction(e -> {
	        	Tab AddJobTab = new Tab("Add New Job Information");
	        	
	        	tabPane.getTabs().add(AddJobTab);
		        tabPane.getSelectionModel().select(AddJobTab);
		        AddJobTab.setContent(new HBox(getAddJobLayout()));
	        });
	        
	        
	        btnSave.setMaxWidth(Double.MAX_VALUE);
	        btnSave.addEventHandler(MouseEvent.MOUSE_ENTERED,
	                new EventHandler<MouseEvent>() {
	                    @Override public void handle(MouseEvent e) {
	                        btnSave.setStyle("-fx-font: 25 Georgia; -fx-background-color: #89A9C9; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
	                    }
	                });

	        btnSave.addEventHandler(MouseEvent.MOUSE_PRESSED,
	                new EventHandler<MouseEvent>() {
	                    @Override public void handle(MouseEvent e) {
	                        btnSave.setStyle("-fx-font: 25 Georgia; -fx-background-color: #89A9C9; -fx-background-radius: 10,4,3,5; -fx-background-insets: 2,1,2,0;");
	                    }
	                });

	        btnSave.addEventHandler(MouseEvent.MOUSE_EXITED,
	                new EventHandler<MouseEvent>() {
	                    @Override public void handle(MouseEvent e) {
	                        btnSave.setStyle("-fx-font: 25 Georgia; -fx-background-color: #547799; -fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");
	                    }
	                });

	        btnSave.setOnAction(e -> {
	        	table.setItems(null);
	        	table.setItems(showData());
	        });
	        
	        HBox toolbarPane = new HBox();
	        ToolBar toolbar = new ToolBar();
	        toolbar.setMinSize(1000, 50);

	        
	        
	        toolbarPane.setStyle("-fx-background-color: #f0f0f0;");

	        HBox vbButtons = new HBox();
	        //vbButtons.setStyle("-fx-background-color: red;");
	        vbButtons.setSpacing(10);
	        vbButtons.setPadding(new Insets(10, 10, 10, 20));
	        vbButtons.setAlignment(Pos.CENTER);
	        vbButtons.getChildren().addAll(btnAdd, btnSave);


	        toolbar.getItems().addAll(vbButtons, rightSpacer, btnSave);
	        toolbarPane.getChildren().add(toolbar);
	        childBorder.setTop(toolbarPane);
	        //TabPane tabPane = new TabPane();
	        //tabPane.setStyle("-fx-background-color: pink;");
	        tabPane.setStyle("-fx-background-insets: 0 1 0 1,0,0;");


	        BorderPane borderPane = new BorderPane();
	        //  GridPane gridPane = new GridPane();

	       

	        jobsTab.setStyle("");
	        tabPane.getTabs().add(jobsTab);
	        tabPane.getSelectionModel().select(jobsTab);
	        jobsTab.setOnCloseRequest(e -> e.consume());

	        
	        HBox tablePane = new HBox();
	        
	        

	        
	        final Label label = new Label("Job Inforamtion");
	        label.setFont(new Font("Arial", 30));
	        
	        table = new TableView<>();
	        table.setEditable(true);
	        
	        TableColumn<bs_Job, Integer> Col1 = new TableColumn<>("Job Id");
	        Col1.setMinWidth(30);
	        Col1.setEditable(false);
	        Col1.setCellValueFactory(new PropertyValueFactory<bs_Job, Integer>("_JobID"));
	        
	        TableColumn<bs_Job, String> Col2 = new TableColumn<>("Job Title");
	        Col2.setMinWidth(100);
	        Col2.setCellValueFactory(new PropertyValueFactory<bs_Job, String>("_JobTitle"));
	        Col2.setCellFactory(TextFieldTableCell.forTableColumn());
	        Col2.setOnEditCommit(
	                new EventHandler<CellEditEvent<bs_Job, String>>() {
	                    @Override
	                    public void handle(CellEditEvent<bs_Job, String> t) {
	                        ((bs_Job) t.getTableView().getItems().get(
	                                t.getTablePosition().getRow())
	                                ).set_JobTitle(t.getNewValue());
	                        UpdateTitleData(((bs_Job) t.getTableView().getItems().get(
	                                t.getTablePosition().getRow())
	                                ).get_JobTitle(), ((bs_Job) t.getTableView().getItems().get(
	    	                                t.getTablePosition().getRow())
	    	                                ).get_JobID());
	                    }
	                }
	            );
	        
	        TableColumn<bs_Job, String> Col3 = new TableColumn<>("Job Details");
	        Col3.setMinWidth(200);
	        Col3.setCellValueFactory(new PropertyValueFactory<>("_JobDetails"));
	        Col3.setCellFactory(TextFieldTableCell.forTableColumn());
	        Col3.setOnEditCommit(
	                new EventHandler<CellEditEvent<bs_Job, String>>() {
	                    @Override
	                    public void handle(CellEditEvent<bs_Job, String> t) {
	                        ((bs_Job) t.getTableView().getItems().get(
	                                t.getTablePosition().getRow())
	                                ).set_JobDetails(t.getNewValue());
	                        UpdateDetailsData(((bs_Job) t.getTableView().getItems().get(
	                                t.getTablePosition().getRow())
	                                ).get_JobDetails(), ((bs_Job) t.getTableView().getItems().get(
	    	                                t.getTablePosition().getRow())
	    	                                ).get_JobID());
	                    }
	                }
	            );
	        
	        TableColumn<bs_Job, String> Col4 = new TableColumn<>("Issue Date");
	        Col4.setMinWidth(50);
	        Col4.setCellValueFactory(new PropertyValueFactory<>("_IssueDate"));
	        Col4.setEditable(false);
	       
	        TableColumn<bs_Job, Date> Col5 = new TableColumn<>("Deadline");
	        Col5.setMinWidth(50);
	        Col5.setCellValueFactory(new PropertyValueFactory<>("_DeadlineDate"));
	        Col5.setEditable(false);
	        
	        TableColumn<bs_Job, Boolean> Col6 = new TableColumn<>("Status");
	        Col6.setMinWidth(30);
	        Col6.setCellValueFactory(new PropertyValueFactory<>("_JobStatus"));
	        Col6.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
	        Col6.setOnEditCommit(
	                new EventHandler<CellEditEvent<bs_Job, Boolean>>() {
	                    @Override
	                    public void handle(CellEditEvent<bs_Job, Boolean> t) {
	                        ((bs_Job) t.getTableView().getItems().get(
	                                t.getTablePosition().getRow())
	                                ).set_JobStatus(t.getNewValue());
	                        UpdateStatusData(((bs_Job) t.getTableView().getItems().get(
	                                t.getTablePosition().getRow())
	                                ).is_JobStatus(), ((bs_Job) t.getTableView().getItems().get(
	    	                                t.getTablePosition().getRow())
	    	                                ).get_JobID());
	                    }
	                }
	            );
	        
	        TableColumn<bs_Job, Integer> Col7 = new TableColumn<>("No. of Vacancy");
	        Col7.setMinWidth(30);
	        Col7.setCellValueFactory(new PropertyValueFactory<>("_Vacancy"));
	        Col7.setCellFactory(TextFieldTableCell.<bs_Job, Integer>forTableColumn(new StringConverter<Integer>(){
	            @Override
	            public String toString(Integer t) {
	                return t.toString();
	            }

	            @Override
	            public Integer fromString(String string) {
	                return Integer.parseInt(string);
	            }
	        }));
	        Col7.setOnEditCommit(
	                new EventHandler<CellEditEvent<bs_Job, Integer>>() {
	                    @Override
	                    public void handle(CellEditEvent<bs_Job, Integer> t) {
	                        ((bs_Job) t.getTableView().getItems().get(
	                                t.getTablePosition().getRow())
	                                ).set_Vacancy(t.getNewValue());
	                        UpdateVacancyData(((bs_Job) t.getTableView().getItems().get(
	                                t.getTablePosition().getRow())
	                                ).get_Vacancy(), ((bs_Job) t.getTableView().getItems().get(
	    	                                t.getTablePosition().getRow())
	    	                                ).get_JobID());
	                    }
	                }
	            );
	        
	        TableColumn<bs_Job, Integer> Col8 = new TableColumn<>("Minimum Experience Required");
	        Col8.setPrefWidth(200);
	        Col8.setCellValueFactory(new PropertyValueFactory<>("_MinExp"));
	        Col8.setCellFactory(TextFieldTableCell.<bs_Job, Integer>forTableColumn(new StringConverter<Integer>(){
	            @Override
	            public String toString(Integer t) {
	                return t.toString();
	            }

	            @Override
	            public Integer fromString(String string) {
	                return Integer.parseInt(string);
	            }
	        }));
	        Col8.setOnEditCommit(
	                new EventHandler<CellEditEvent<bs_Job, Integer>>() {
	                    @Override
	                    public void handle(CellEditEvent<bs_Job, Integer> t) {
	                        ((bs_Job) t.getTableView().getItems().get(
	                                t.getTablePosition().getRow())
	                                ).set_MinExp(t.getNewValue());
	                        UpdateMinExpData(((bs_Job) t.getTableView().getItems().get(
	                                t.getTablePosition().getRow())
	                                ).get_MinExp(), ((bs_Job) t.getTableView().getItems().get(
	    	                                t.getTablePosition().getRow())
	    	                                ).get_JobID());
	                    }
	                }
	            );
	        
	        TableColumn<bs_Job, Integer> Col9 = new TableColumn<>("Category");
	        Col9.setMinWidth(30);
	        Col9.setCellValueFactory(new PropertyValueFactory<>("_catID"));
	        Col9.setCellFactory(TextFieldTableCell.<bs_Job, Integer>forTableColumn(new StringConverter<Integer>(){
	            @Override
	            public String toString(Integer t) {
	                return t.toString();
	            }

	            @Override
	            public Integer fromString(String string) {
	                return Integer.parseInt(string);
	            }
	        }));
	        Col9.setOnEditCommit(
	                new EventHandler<CellEditEvent<bs_Job, Integer>>() {
	                    @Override
	                    public void handle(CellEditEvent<bs_Job, Integer> t) {
	                        ((bs_Job) t.getTableView().getItems().get(
	                                t.getTablePosition().getRow())
	                                ).set_catID(t.getNewValue());
	                        UpdateCatData(((bs_Job) t.getTableView().getItems().get(
	                                t.getTablePosition().getRow())
	                                ).get_catID(), ((bs_Job) t.getTableView().getItems().get(
	    	                                t.getTablePosition().getRow())
	    	                                ).get_JobID());
	                    }
	                }
	            );
	        
	        TableColumn<bs_Job, bs_Job> delCol = new TableColumn<>("Options");
	        delCol.setMinWidth(40);
	        delCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
	        delCol.setCellFactory(param -> new TableCell<bs_Job, bs_Job>() {
	            private final Button deleteButton = new Button("Delete");

	            @Override
	            protected void updateItem(bs_Job Job, boolean empty) {
	                super.updateItem(Job, empty);

	                if (Job == null) {
	                    setGraphic(null);
	                    return;
	                }

	                setGraphic(deleteButton);
	                deleteButton.setOnAction(e -> {
	                	table.getItems().remove(Job);
	                	DeleteData(Job.get_JobID());
	                });
	            }
	        });

	        
	        //table.setPrefSize(10,500);
	        table.setItems(showData());

	        table.getColumns().addAll(Col1, Col2, Col3, Col4, Col5, Col6, Col7, Col8, Col9, delCol);

	        
	        
	        
	        
	        
	        tablePane.getChildren().add(table);
	        ScrollPane sc = new ScrollPane();
	        VBox vbox = new VBox();
	        vbox.setPrefSize(1000, 500);
	        vbox.setSpacing(10);
	        vbox.setPadding(new Insets(0, 10, 0, 10));
	        vbox.getChildren().addAll(label, sc);
	        vbox.setAlignment(Pos.CENTER);
	        VBox.setVgrow(table, Priority.ALWAYS);
	        sc.setContent(table);
	        sc.setHbarPolicy(ScrollBarPolicy.NEVER);
	        sc.setVbarPolicy(ScrollBarPolicy.ALWAYS);
	        childBorder.setCenter(vbox);
	        childBorder.setStyle("");

	        borderPane.prefHeightProperty().bind(exContainer.heightProperty());
	        borderPane.prefWidthProperty().bind(exContainer.widthProperty());

	        //border.prefHeightProperty().bind(exContainer.heightProperty());
	        //border.prefWidthProperty().bind(exContainer.widthProperty());
	        
	        jobsTab.setContent(childBorder);
	        borderPane.setCenter(tabPane);
			// ---------------------------------------------------------------
			
			exContainer.getChildren().add(borderPane);
			return exContainer;
		}
		ComboBox<String> comboBox;
		
		private HBox getAddJobLayout(){
			comboBox = new ComboBox<>();
			comboBox = getCategories();
			bs_Job newJobObj = new bs_Job();
			
			GridPane grid = new GridPane();
	        grid.setHgap(20);
	        grid.setVgap(10);
	        grid.setPadding(new Insets(20, 150, 10, 10));

	        Button btn = new Button("Add");
	        btn.setPrefWidth(100);
	        btn.setStyle("-fx-font-size: 20px");
	        grid.add(btn, 3, 13);
	        
	        


	        Text Label_header=new Text("Add new Jobs");
	        grid.add(Label_header, 3, 0);
	        Label_header.setStyle("fx-font: 40 Georgia; -fx-font-size: 30px;");





	        TextField textArea1 = new TextField();
	        
	        
	        
	        grid.add(new Label("Job Title"), 3, 2);
	        grid.add(textArea1, 3, 3);

	        TextField textArea2 = new TextField();
	        grid.add(new Label("Job Details"), 3, 4);
	        grid.add(textArea2, 3, 5 );
	        
	        

	        DatePicker dp = new DatePicker(LocalDate.now().plusWeeks(1));
	        grid.add(new Label("Deadline"), 3, 6);
	        grid.add(dp, 3, 7);
	        
	        
	        
	        TextField textArea6 = new TextField();
	        grid.add(new Label("Vacancy"), 3, 8);
	        grid.add(textArea6, 3, 9);
	        
	        

	        TextField 
	        textArea7 = new TextField();
	        grid.add(new Label("Min Experience"), 4, 8);
	        grid.add(textArea7, 4, 9);

	        
	        
	        /*TextField textArea5 = new TextField();
	        grid.add(new Label("Job Status"), 3, 10);
	        grid.add(textArea5, 3, 11);*/
	        
	        

	        /*ObservableList<String> options =
	                FXCollections.observableArrayList(
	                        "Option 1",
	                        "Option 2",
	                        "Option 3"
	                );*/
	        
	        grid.add(new Label("Category"), 4, 10);
	        grid.add(comboBox, 4, 11);
	        
	        
	        
	        //
	        HBox h=new HBox();
	        h.getChildren().add(grid);
	        
	        btn.setOnAction(e -> {
	        	java.sql.Date date = java.sql.Date.valueOf(dp.getValue());
	        	if(!textArea1.getText().trim().equals("") || !textArea2.getText().trim().equals("") || !textArea6.getText().trim().equals("") || !textArea7.getText().trim().equals("")){
	        		newJobObj.set_JobTitle(textArea1.getText().toString());
	        		newJobObj.set_JobDetails(textArea2.getText().toString());
	        		newJobObj.set_DeadlineDate(date);
	        		newJobObj.set_Vacancy(Integer.parseInt(textArea6.getText().toString()));
	        		newJobObj.set_MinExp(Integer.parseInt(textArea7.getText().toString()));
	        		newJobObj.set_JobPostedBy(empID);
	        		newJobObj.set_JobStatus(true);
		        	if(AddNewJob(newJobObj, comboBox.getValue().toString())){
		        		alert = new Alert(AlertType.INFORMATION);
		    			alert.setTitle("Information");
		    			alert.setHeaderText("Success !");
		    			alert.setContentText("Successfully Added !");

		    			alert.showAndWait();
		    			
		    			textArea1.clear();
		    			textArea2.clear();
		    			textArea6.clear();
		    			textArea7.clear();
		    			//dp.
		        	}else{
		        		alert = new Alert(AlertType.ERROR);
		    			alert.setTitle("Information");
		    			alert.setHeaderText("Falied !");
		    			alert.setContentText("Check your Internet Connection !");

		    			alert.showAndWait();
		        	}
	        	}else{
	        		alert = new Alert(AlertType.ERROR);
	    			alert.setTitle("Information");
	    			alert.setHeaderText("Falied !");
	    			alert.setContentText("Please fill all the fields to continue !");

	    			alert.showAndWait();
	        	}
	        	
	        });
	        
	        return h;
		}
		
		private boolean AddNewJob(bs_Job ObjJOB, String catname){
			boolean result = false;
			bs_DBConnect mysqlConnect = new bs_DBConnect();
	    	PreparedStatement usrStatement = null;
			try{
				
				String usrSQL = "INSERT INTO `jp_jobs`(`job_title`, `job_details`, `job_issuedate`, `job_deadlinedate`, `job_status`, `job_vacancy`, "
						+ "`job_minexp`, `job_postedby`, `cat_id`) VALUES(?, ?, NOW(), ?, ?, ?, ?, ?, (SELECT `cat_id` FROM `jp_categories` WHERE `cat_name` = ?))";
				
				usrStatement = mysqlConnect.connect().prepareStatement(usrSQL);
				
				usrStatement.setString(1, ObjJOB.get_JobTitle());
				usrStatement.setString(2, ObjJOB.get_JobDetails());
				usrStatement.setDate(3, ObjJOB.get_DeadlineDate());
				usrStatement.setBoolean(4, ObjJOB.is_JobStatus());
				usrStatement.setInt(5, ObjJOB.get_Vacancy());
				usrStatement.setInt(6, ObjJOB.get_MinExp());
				usrStatement.setInt(7, ObjJOB.get_JobPostedBy());
				usrStatement.setString(8, catname.toString());
				
				usrStatement.executeUpdate();
				
				result = true;
				
				return result;
			}catch(SQLException ERROR){
				result = false;
				ERROR.printStackTrace();	
			}finally {
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
		
		private void UpdateVacancyData(int vancancy, int jobid){
			bs_DBConnect mysqlConnect = new bs_DBConnect();
	    	PreparedStatement usrStatement = null;
			try{
				
				String usrSQL = "UPDATE `jp_jobs` SET `job_vacancy` = ? WHERE `jp_jobs`.`job_id` = ?";
				
				usrStatement = mysqlConnect.connect().prepareStatement(usrSQL);
				
				usrStatement.setInt(1, vancancy);
				usrStatement.setInt(2, jobid);
				
				usrStatement.executeUpdate();
				
			}catch(SQLException ERROR){
				ERROR.printStackTrace();	
			}finally {
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
		}
		
		private void UpdateTitleData(String title, int jobid){
			bs_DBConnect mysqlConnect = new bs_DBConnect();
	    	PreparedStatement usrStatement = null;
			try{
				
				String usrSQL = "UPDATE `jp_jobs` SET `job_title` = ? WHERE `jp_jobs`.`job_id` = ?";
				
				usrStatement = mysqlConnect.connect().prepareStatement(usrSQL);
				
				usrStatement.setString(1, title);
				usrStatement.setInt(2, jobid);
				
				usrStatement.executeUpdate();
				
			}catch(SQLException ERROR){
				ERROR.printStackTrace();	
			}finally {
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
		}
		
		private void UpdateDetailsData(String details, int jobid){
			bs_DBConnect mysqlConnect = new bs_DBConnect();
	    	PreparedStatement usrStatement = null;
			try{
				
				String usrSQL = "UPDATE `jp_jobs` SET `job_details` = ? WHERE `jp_jobs`.`job_id` = ?";
				
				usrStatement = mysqlConnect.connect().prepareStatement(usrSQL);
				
				usrStatement.setString(1, details);
				usrStatement.setInt(2, jobid);
				
				usrStatement.executeUpdate();
				
			}catch(SQLException ERROR){
				ERROR.printStackTrace();	
			}finally {
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
		}
		
		private void UpdateStatusData(Boolean status, int jobid){
			bs_DBConnect mysqlConnect = new bs_DBConnect();
	    	PreparedStatement usrStatement = null;
			try{
				
				String usrSQL = "UPDATE `jp_jobs` SET `job_status` = ? WHERE `jp_jobs`.`job_id` = ?";
				
				usrStatement = mysqlConnect.connect().prepareStatement(usrSQL);
				
				usrStatement.setBoolean(1, status);
				usrStatement.setInt(2, jobid);
				
				usrStatement.executeUpdate();
				
			}catch(SQLException ERROR){
				ERROR.printStackTrace();	
			}finally {
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
		}
		
		private void UpdateMinExpData(int minexp, int jobid){
			bs_DBConnect mysqlConnect = new bs_DBConnect();
	    	PreparedStatement usrStatement = null;
			try{
				
				String usrSQL = "UPDATE `jp_jobs` SET `job_minexp` = ? WHERE `jp_jobs`.`job_id` = ?";
				
				usrStatement = mysqlConnect.connect().prepareStatement(usrSQL);
				
				usrStatement.setInt(1, minexp);
				usrStatement.setInt(2, jobid);
				
				usrStatement.executeUpdate();
				
			}catch(SQLException ERROR){
				ERROR.printStackTrace();	
			}finally {
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
		}
		
		private void UpdateCatData(int catid, int jobid){
			bs_DBConnect mysqlConnect = new bs_DBConnect();
	    	PreparedStatement usrStatement = null;
			try{
				
				String usrSQL = "UPDATE `jp_jobs` SET `cat_id` = ? WHERE `jp_jobs`.`job_id` = ?";
				
				usrStatement = mysqlConnect.connect().prepareStatement(usrSQL);
				
				usrStatement.setInt(1, catid);
				usrStatement.setInt(2, jobid);
				
				usrStatement.executeUpdate();
				
			}catch(SQLException ERROR){
				ERROR.printStackTrace();	
			}finally {
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
		}
		
		private void DeleteData(int JobID){
			bs_DBConnect mysqlConnect = new bs_DBConnect();
	    	PreparedStatement usrStatement = null;
			try{
				
				String usrSQL = "DELETE FROM `jp_jobs` WHERE `jp_jobs`.`job_id` = ?";
				
				usrStatement = mysqlConnect.connect().prepareStatement(usrSQL);
				
				usrStatement.setInt(1, JobID);
				
				usrStatement.executeUpdate();
				
			}catch(SQLException ERROR){
				ERROR.printStackTrace();	
			}finally {
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
		}
		
		private ObservableList<bs_Job> showData(){
			ObservableList<bs_Job> newList = FXCollections.observableArrayList();
			bs_DBConnect mysqlConnect = new bs_DBConnect();
	    	PreparedStatement usrStatement = null;
	    	ResultSet rs = null;
			try{
				
				String usrSQL = "SELECT `job_id` AS userID, `job_title` AS jobtitle, `job_details` AS jobdetails, "
						+ "`job_issuedate` AS jobissue, `job_deadlinedate` AS jobdeadline, `job_status` AS jobstatus, "
						+ "`job_vacancy` AS jobvacancy, `job_minexp` AS jobminexp, "
						+ "`cat_id` AS catid FROM `jp_jobs` WHERE `job_postedby` = ?";
				
				usrStatement = mysqlConnect.connect().prepareStatement(usrSQL);

				usrStatement.setInt(1, this.empID);
				
				rs = usrStatement.executeQuery();
				
				while(rs.next()){
					newList.add(new bs_Job(rs.getInt("userID"), 
							rs.getString("jobtitle"), 
							rs.getString("jobdetails"), 
							rs.getDate("jobissue"),
							rs.getDate("jobdeadline"),
							rs.getBoolean("jobstatus"), 
							rs.getInt("jobvacancy"), 
							rs.getInt("jobminexp"), 
							rs.getInt("catid"))
							);
				}
				
				return newList;
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
			return newList;
		}
		
		private ComboBox<String> getCategories(){
			ComboBox<String> cmb = new ComboBox<>();
			bs_DBConnect mysqlConnect = new bs_DBConnect();
	    	PreparedStatement Statement = null;
	    	ResultSet rs = null;
			try{
				
				String usrSQL = "SELECT `cat_name` AS cat FROM `jp_categories` ORDER BY `cat_name`";
				
				Statement = mysqlConnect.connect().prepareStatement(usrSQL);
				
				rs = Statement.executeQuery();
				
				while(rs.next()){
					cmb.getItems().add(rs.getString("cat"));	
				}

				return cmb;
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
			return cmb;
		}
	
	}
	
	
	
	
	class JVParent{
		private bs_Job ObjJob;
		/**
		 * @return the objJob
		 */
		public bs_Job getObjJob() {
			return ObjJob;
		}
		/**
		 * @param objJob the objJob to set
		 */
		public void setObjJob(bs_Job objJob) {
			ObjJob = objJob;
		}
		
		
		public JVParent(bs_Job Obj){
			this.setObjJob(Obj);
		}
		
		// Job Class pass kore value show koraite pari though Parameter
		public VBox createParent(String TypeColor){		
			VBox exContainer = new VBox();
			exContainer.setMinSize(400, 100);
			exContainer.setStyle("-fx-padding: 10px;-fx-border-color: #FFFFFF;-fx-border-width: 5px;");
			
	        GridPane gridPane = new GridPane();
	        gridPane.setGridLinesVisible(false);

	        //Give the GridPane an ID for CSS Styles.
	        gridPane.setStyle("-fx-border-color: #ffffff;" +
	        				  " -fx-border-width: 1px; " +
	        		          "-fx-border-radius: 4;" +
	        				  "-fx-background-radius: 4;" +
	        		          "-fx-background-color: linear-gradient(to top," + TypeColor + ", #dee3e4);" +
	        				  "-fx-padding: 15;-fx-border-color: #404D52;-fx-border-width: 5px;");
	        
	        //#915956
	        //#b5bcc6
	        //Comment the next 2 lines out to see what happens when this is
	        //not explicitly set. It will remove the padding you specified.
	        gridPane.setHgap(5);
	        gridPane.setVgap(5);
	        
	        //This description starts in Row 0, Col 0 and spans
	        //2 columns and one row.
	        Label label = new Label(ObjJob.get_JobTitle().toString());
	        label.setStyle("-fx-font-size: 14px;" +
                           "-fx-font-weight: bold;");
	        
	        gridPane.add(label, 0,0,2,1);

	        //Add A Label. The label starts in Col 0, Row 1 and does not
	        //span any columns or rows.
	        Label lbl_createdDate = new Label("Minimum Experience Required : ");
	        lbl_createdDate.setStyle("-fx-font-size: 12px;" +
                           "-fx-font-weight: lighter;");
	        gridPane.add(lbl_createdDate, 0,1);

	        //Add a TextField. The textfield starts in Col 1, Row 1 and
	        //does not span any columns or rows.
	        Text txtFirstName = new Text("" + ObjJob.get_MinExp());
	        txtFirstName.setStyle("");

	        gridPane.add(txtFirstName, 1,1);
	        //Add Job Description Label in Col 0, Row 2
	        // --------------------------------------------------
	        bs_DBConnect mysqlConnect = new bs_DBConnect();
	    	PreparedStatement Statement = null;
	    	ResultSet rs = null;
	    	String CatName = "";
			try{
				
				String usrSQL = "SELECT `cat_name` AS cat FROM `jp_categories` WHERE `cat_id` = ?";
				
				Statement = mysqlConnect.connect().prepareStatement(usrSQL);
				
				Statement.setInt(1, ObjJob.get_catID());
				
				rs = Statement.executeQuery();
				
				while(rs.next()){
					CatName = rs.getString("cat");
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
			// --------------------------------------------------
	        Label lbl_Desc = new Label("Category : " + CatName.toString());
	        
	        
	        lbl_Desc.setStyle("-fx-font-size: 12px;" +
                           "-fx-font-weight: lighter;");
	        gridPane.add(lbl_Desc, 0,2);

	        // Add Deadline Date -
	        Label lbl_deadlineDate = new Label("Deadline Date : " + ObjJob.get_DeadlineDate().toString());
	        lbl_deadlineDate.setStyle("-fx-font-size: 12px;" +
                           "-fx-font-weight: bold;");
	        
	        gridPane.add(lbl_deadlineDate, 2, 3);
	        //Add a Details Button.
	        Button submitButton = new Button("Details");
	        submitButton.setOnAction(e -> {
	        	new ui_JobDetails().Display(this.ObjJob, getLoggedUserID());
	        });
	        /*submitButton.setOnAction(new EventHandler() {

	            @Override
	            public void handle(ActionEvent event) {
	                System.out.printf("Submiton Clicked. Hi there %s %s",
	                        txtFirstName.getText(), txtLastName.getText());
	            }
	        });*/
	        gridPane.add(submitButton, 3, 3);

	        
	        //Align the Details Button to Right.
	        GridPane.setHalignment(submitButton, HPos.RIGHT);

	        
	        exContainer.getChildren().addAll(gridPane);
			return exContainer;
		}
	}
	
	
	
	class Footer{
		public VBox FooterParent(Message _message){

			final Pane leftSpacer = new Pane();
	        HBox.setHgrow(
	                leftSpacer,
	                Priority.SOMETIMES
	        );

	        final Pane rightSpacer = new Pane();
	        HBox.setHgrow(
	                rightSpacer,
	                Priority.SOMETIMES
	        );	        
	        
			VBox exContainer = new VBox();
			//exContainer.setVgrow(child, value);
			exContainer.setMinSize(1000, 50);
			//exContainer.setStyle("-fx-padding: 10px;-fx-border-color: #000000;-fx-border-width: 5px;");
			exContainer.setStyle("-light-black: rgb(74, 75, 78);" +
							    "-dark-highlight: rgb(87, 89, 92);" + 
							    "-dark-black: rgb(39, 40, 40);" + 
							    "-darkest-black: rgb(5, 5, 5);" + 
							    "-mid-gray: rgb(216, 222, 227);" + 
							    "-fx-background-color: -mid-gray;");
			
			
			HBox buttonBar = new HBox();
			buttonBar.setMinSize(1000, 30);
	        buttonBar.setStyle("-fx-background-color:-darkest-black,-dark-highlight,linear-gradient(to bottom, -light-black 2%, -dark-black 98%);" +
							    "-fx-background-insets: 0, 1 1 1 0, 2 1 1 1;" + 
							    "-fx-background-radius: 0;" + 
							    "-fx-padding: 0.4em 1.833333em 0.4em 1.833333em;");
			
	        /*Label lbl_StatusTitle = new Label("Status : ");
	        lbl_StatusTitle.setFont(Font.font("George", FontWeight.BOLD, 13));
	        
	        Text lbl_Status = new Text();
	        lbl_Status.setFill(_message.get_StatusColor());
	        lbl_Status.setFont(Font.font("George", FontPosture.ITALIC, 13));
	        lbl_Status.setText(_message.get_StatusMessage().toString());*/
	        
	        Button btn_notification = new Button("About");
	        btn_notification.setStyle("-fx-font: 10px Tahoma;" +
									    "-fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);" + 
									    "-fx-stroke: black;" + 
									    "-fx-stroke-width: 1;-fx-background-insets: 0, 1, 2 1 1 1;-fx-background-radius: 5px;");
			
			
			
			//buttonBar.getChildren().addAll(lbl_StatusTitle, lbl_Status, rightSpacer, btn_notification);
			buttonBar.getChildren().addAll(rightSpacer, btn_notification);
			
			ToolBar toolbar = new ToolBar();
			toolbar.setMinSize(1000, 30);
			//toolbar.setStyle("-fx-background-color: linear-gradient(to bottom, #b5bcc6, #dee3e4);-fx-background-radius: 5px;-fx-border-color: #000000;-fx-border-width: 2px;-fx-border-radius: 5px;");
			toolbar.setStyle("-fx-base: -dark-black;" +
							 "-fx-font-size: 10pt;" +
							 "-fx-background-color: linear-gradient(to bottom, derive(-fx-base,-30%), derive(-fx-base,-60%)), linear-gradient(to bottom, -light-black 2%, -dark-black 98%);" +    
							 "-fx-background-insets: 0, 0 0 1 0;" +
							 "-fx-padding: .9em 0.416667em .9em 0.416667em;" +
							 "-fx-effect: dropshadow(two-pass-box,black,5,.2,0,0);");
			//toolbar.getItems().addAll(rightSpacer, lbl_Greetings, lbl_Username);
			toolbar.getItems().addAll(buttonBar);
			
			exContainer.getChildren().add(toolbar);
			return exContainer;
		}
	}
	
	class Message{
		private String _StatusMessage;
		private Color _StatusColor;
		
		public Message(String MSG, Color COLOR){
			this.set_StatusMessage(MSG);
			this.set_StatusColor(COLOR);
		}
		
		/**
		 * @return the _StatusMessage
		 */
		public String get_StatusMessage() {
			return _StatusMessage;
		}
		/**
		 * @param _StatusMessage the _StatusMessage to set
		 */
		public void set_StatusMessage(String _StatusMessage) {
			this._StatusMessage = _StatusMessage;
		}
		/**
		 * @return the _StatusColor
		 */
		public Color get_StatusColor() {
			return _StatusColor;
		}
		/**
		 * @param _StatusColor the _StatusColor to set
		 */
		public void set_StatusColor(Color _StatusColor) {
			this._StatusColor = _StatusColor;
		}
	}
	
	
	public static void main(String[] args) {
        launch(args);
    }

	/**
	 * @return the mainWindow_Title
	 */
	public String getMainWindow_Title() {
		return MainWindow_Title;
	}

	/**
	 * @param mainWindow_Title the mainWindow_Title to set
	 */
	public void setMainWindow_Title(String mainWindow_Title) {
		MainWindow_Title = mainWindow_Title;
	}
}
