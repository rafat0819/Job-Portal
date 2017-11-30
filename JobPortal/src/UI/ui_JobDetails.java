package UI;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Base.bs_DBConnect;
import Base.bs_Job;
import javafx.application.Application;
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

public class ui_JobDetails extends Application {
	Stage window;
	bs_Job currJob;
	int LoggedID;
	Alert alert;
	
	public void start(Stage primaryStage) throws Exception{
        
        window = primaryStage;
        window.setTitle("JOB PORTAL - Job Details");

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

        Text Label_header=new Text("Job Profile");
        Label_header.setStyle("-fx-font: 40 Georgia;");
        Label_header.setFill(Color.WHITE);
        header.getChildren().addAll(Label_header);

        VBox all=new VBox(20);
        all.setPadding(new Insets(10,10,10,10));
        all.setPrefWidth(700);
        all.setAlignment(Pos.BOTTOM_CENTER);

        Label Label_Job=new Label(this.currJob.get_JobTitle().toString());
        Label_Job.setStyle("-fx-font: 35 Georgia;");
        Label_Job.setTextFill(Color.BLACK);

        VBox v0=new VBox();
        v0.setStyle("-fx-font: 25 Georgia;");
        Label Label_Details=new Label("Job Details");
        Label_Details.setTextFill(Color.BLACK);

        VBox v0_0=new VBox();
        Label Label_Details_Value=new Label(this.currJob.get_JobDetails().toString());
        Label_Details_Value.setStyle("-fx-border-width: 2; -fx-border-color: #34495E");
        Label_Details_Value.setTextFill(Color.BLACK);
        v0_0.getChildren().addAll(Label_Details_Value);

        v0.getChildren().addAll(Label_Details, v0_0);

        HBox h0=new HBox(150);
        h0.setStyle("-fx-font: 25 Georgia;");
        VBox v1=new VBox();
        Label Label_Issued_Date=new Label("Issued Date");
        Label_Issued_Date.setTextFill(Color.BLACK);

        VBox v1_0=new VBox();
        Label Label_Issued_Date_Value=new Label(this.currJob.get_IssueDate().toString());
        Label_Issued_Date_Value.setStyle("-fx-border-width: 2; -fx-border-color: #34495E");
        Label_Issued_Date_Value.setTextFill(Color.BLACK);
        v1_0.getChildren().addAll(Label_Issued_Date_Value);
        v1.setPrefSize(200,10);
        v1.getChildren().addAll(Label_Issued_Date, v1_0);

        VBox v2=new VBox();
        Label Label_DeadLine=new Label("DeadLine Date");
        Label_DeadLine.setTextFill(Color.BLACK);

        VBox v2_0=new VBox();
        Label Label_DeadLine_Value=new Label(this.currJob.get_DeadlineDate().toString());
        Label_DeadLine_Value.setStyle("-fx-border-width: 2; -fx-border-color: #34495E");
        Label_DeadLine_Value.setTextFill(Color.BLACK);
        v2_0.getChildren().addAll(Label_DeadLine_Value);
        v1.setPrefSize(200,10);
        v2.getChildren().addAll(Label_DeadLine, v2_0);

        h0.getChildren().addAll(v1, v2);

        HBox h1=new HBox(150);
        h1.setStyle("-fx-font: 25 Georgia;");
        VBox v3=new VBox();
        Label Label_Min_Exp=new Label("Min Experience");
        Label_Min_Exp.setTextFill(Color.BLACK);

        VBox v3_0=new VBox();
        Label Label_Min_Exp_Value=new Label("" + this.currJob.get_MinExp());
        Label_Min_Exp_Value.setStyle("-fx-border-width: 2; -fx-border-color: #34495E");
        Label_Min_Exp_Value.setTextFill(Color.BLACK);
        v3_0.getChildren().addAll(Label_Min_Exp_Value);
        v3.setPrefSize(200,10);
        v3.getChildren().addAll(Label_Min_Exp, v3_0);

        VBox v4=new VBox();
        Label Label_No_Vacancy=new Label("No of Vacancy");
        Label_No_Vacancy.setTextFill(Color.BLACK);

        VBox v4_0=new VBox();
        Label Label_No_Vacancy_Value=new Label("" + this.currJob.get_Vacancy());
        Label_No_Vacancy_Value.setStyle("-fx-border-width: 2; -fx-border-color: #34495E");
        Label_No_Vacancy_Value.setTextFill(Color.BLACK);
        v4_0.getChildren().addAll(Label_No_Vacancy_Value);
        v4.setPrefSize(200,10);
        v4.getChildren().addAll(Label_No_Vacancy, v4_0);

        h1.getChildren().addAll(v3, v4);

        VBox v5=new VBox();
        v5.setStyle("-fx-font: 25 Georgia;");
        Label Label_Posted_By=new Label("Posted By");
        Label_Posted_By.setTextFill(Color.BLACK);

        
     // --------------------------------------------------
        bs_DBConnect mysqlConnect = new bs_DBConnect();
    	PreparedStatement Statement = null;
    	ResultSet rs = null;
    	String PostedByName = "";
		try{
			
			String usrSQL = "SELECT `company_name` AS cname FROM `jp_employer_users` WHERE `usr_id` = ?";
			
			Statement = mysqlConnect.connect().prepareStatement(usrSQL);
			
			Statement.setInt(1, this.currJob.get_JobPostedBy());
			
			rs = Statement.executeQuery();
			
			while(rs.next()){
				PostedByName = rs.getString("cname");
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
        
        
        VBox v5_0=new VBox();
        Label Label_Posted_By_Value=new Label(PostedByName.toString());
        Label_Posted_By_Value.setStyle("-fx-border-width: 2; -fx-border-color: #34495E");
        Label_Posted_By_Value.setTextFill(Color.BLACK);
        v5_0.getChildren().addAll(Label_Posted_By_Value);

        v5.getChildren().addAll(Label_Posted_By, v5_0);

        HBox h2=new HBox(10);
        h2.setStyle("-fx-font: 25 Georgia;");
        Label Label_Status=new Label("Status");
        Label_Status.setTextFill(Color.BLACK);

        HBox h2_0=new HBox();
        Label Label_Status_Value=new Label();
        if(this.currJob.is_JobStatus()){
        	Label_Status_Value.setText("Open");
        }else{
        	Label_Status_Value.setText("Closed");
        }
        
        Label_Status_Value.setStyle("-fx-border-width: 2; -fx-border-color: #34495E");
        Label_Status_Value.setTextFill(Color.BLACK);
        h2_0.getChildren().addAll(Label_Status_Value);

        h2.getChildren().addAll(Label_Status, h2_0);








        all.setStyle("-fx-background-color: #ECF0F1;-fx-background-radius: 10,4,3,5; -fx-background-insets: 0,1,2,0;");


        HBox h4=new HBox(5);
        h4.setAlignment(Pos.CENTER);
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
        
        Button_Back.setOnAction(e -> {
        	this.window.close();
        });
        

        Button Button_Confirm= new Button("APPLY");
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

        Button_Confirm.setOnAction(e -> {
        	boolean answer = false;
        	answer = ApplyJob(this.currJob);
        	if(answer == true){
        		alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Success !");
				alert.setContentText("Successfully Applied for the Job !");
				
				alert.showAndWait();
        	}else if(answer == false){
        		alert = new Alert(AlertType.ERROR);
				alert.setTitle("Information");
				alert.setHeaderText("Failed !");
				alert.setContentText("Check your Internet Connection !");
				
				alert.showAndWait();
        	}
        });
        
        Button_Back.setMinWidth(200);
        Button_Confirm.setMinWidth(200);
        h4.getChildren().addAll(Button_Back, Button_Confirm);





        all.getChildren().addAll(Label_Job, v0, h0, h1, v5, h2, h4);

        GridPane.setConstraints(all, 0, 1);

        grid.setStyle("-fx-background: #2C3E50");
        grid.getChildren().addAll(header, all);

        window.centerOnScreen();
        window.setResizable(false);
        Scene scene = new Scene(grid, 800, 700);
        window.setScene(scene);
        primaryStage.show();
    }

	private boolean ApplyJob(bs_Job Obj){
		bs_DBConnect mysqlConnect = new bs_DBConnect();
    	PreparedStatement Statement = null;
    	boolean result = false;
		try{
			
			String usrSQL = "INSERT INTO `jp_rel_job_applicant`(`job_id`, `app_id`) VALUES(?, ?)";
			
			Statement = mysqlConnect.connect().prepareStatement(usrSQL);
			
			Statement.setInt(1, this.currJob.get_JobID());
			Statement.setInt(2, this.LoggedID);
			
			Statement.execute();
			
			result = true;
			
			return result;
		}catch(SQLException ERROR){
			result = false;
			ERROR.printStackTrace();	
		}finally {
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
	
	public void Display(bs_Job Obj, int id){
		this.currJob = Obj;
		this.LoggedID = id;
		
		try{
			this.start(new Stage());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    public static void main(String[] args) {
        launch(args);
    }
}
