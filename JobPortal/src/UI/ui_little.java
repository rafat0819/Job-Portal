package UI;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class ui_little extends Application{	
	
	
	public static void main(String[] args) {
        launch(args);
    }
	
	public void start(Stage stage) {
		
		stage.setTitle("Job Block");
        //stage.setWidth(1000);
        //stage.setHeight(1000);
        //------------- 
		JVParent JobViewer = new JVParent();
		//Header header = new Header();
		//Footer footer = new Footer();
		//SubHeader submenu = new SubHeader();
		//Body body = new Body();
		//borderPane.setMinSize(400, 100);
        
        // ------------ Scene scene = new Scene(JobViewer.createParent("#b5bcc6"));
        //Scene scene = new Scene(footer.FooterParent("Conected to the server", Color.DARKGREEN));
		Scene scene = new Scene(JobViewer.createParent("#b5bcc6"));
        
        stage.setScene(scene);
        stage.show();
        
	}
	
	public void Display(){
		this.start(new Stage());
	}
	
	class JVParent{
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
	        Label label = new Label("job_title");
	        label.setStyle("-fx-font-size: 14px;" +
                           "-fx-font-weight: bold;");
	        
	        gridPane.add(label, 0,0,2,1);

	        //Add A Label. The label starts in Col 0, Row 1 and does not
	        //span any columns or rows.
	        Label lbl_createdDate = new Label("Created Date : ");
	        lbl_createdDate.setStyle("-fx-font-size: 12px;" +
                           "-fx-font-weight: lighter;");
	        gridPane.add(lbl_createdDate, 0,1);

	        //Add a TextField. The textfield starts in Col 1, Row 1 and
	        //does not span any columns or rows.
	        TextField txtFirstName = new TextField();
	        txtFirstName.setStyle("-fx-border-color: #939393;" +
	        					  "-fx-border-radius: 5px;" +
	        					  "-fx-background-radius: 5px;" +
	        					  "-fx-background-color: linear-gradient(to bottom, #cccccc, #f2f2f2);");

	        gridPane.add(txtFirstName, 1,1);
	        //Add Job Description Label in Col 0, Row 2
	        Label lbl_Desc = new Label("job_Desc");
	        lbl_Desc.setStyle("-fx-font-size: 12px;" +
                           "-fx-font-weight: lighter;");
	        gridPane.add(lbl_Desc, 0,2);

	        // Add Deadline Date -
	        Label lbl_deadlineDate = new Label("Deadline Date : 11/11/12 ");
	        lbl_deadlineDate.setStyle("-fx-font-size: 12px;" +
                           "-fx-font-weight: bold;");
	        
	        gridPane.add(lbl_deadlineDate, 2, 3);
	        //Add a Details Button.
	        Button submitButton = new Button("Details");
	        submitButton.setOnAction(e -> System.out.printf("Submiton Clicked. Hi there"));
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
	
	class Header{
		public VBox HeaderParent(){
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
	        
			Button btn_Username = new Button("Username");
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
			
			
			buttonBar.getChildren().addAll(rightSpacer, btn_notification, btn_Username, btn_LogOut);
			
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
	
	class SubHeader{
		public VBox MenuParent(){
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
			
	        MenuBar mainMenu = new MenuBar();
	        Menu parentMenu = new Menu("File");
			
	        MenuItem menuItems = new MenuItem("Shuffle",
	                new ImageView(new Image("/btn_logout.png")));
	        MenuItem menuItems1 = new MenuItem("Shuffle",
	                new ImageView(new Image("/btn_logout.png")));
	        parentMenu.getItems().addAll(menuItems, menuItems1);
	        
	        Menu parentMenu1 = new Menu("Open");
	        
	        mainMenu.getMenus().addAll(parentMenu, parentMenu1);
			buttonBar.getChildren().add(mainMenu);
			
			ToolBar toolbar = new ToolBar();
			toolbar.setMinSize(1000, 30);
			//toolbar.setStyle("-fx-background-color: linear-gradient(to bottom, #b5bcc6, #dee3e4);-fx-background-radius: 5px;-fx-border-color: #000000;-fx-border-width: 2px;-fx-border-radius: 5px;");
			toolbar.setStyle("-fx-base: -dark-black;" +
							 "-fx-font-size: 10pt;" +
							 "-fx-background-color: linear-gradient(to bottom, derive(-fx-base,-30%), derive(-fx-base,-60%)), linear-gradient(to bottom, -light-black 2%, -dark-black 98%);" +    
							 "-fx-background-insets: 0, 0 0 1 0;" +
							 "-fx-padding: .9em 0.416667em .9em 0.416667em;" +
							 "-fx-effect: dropshadow(two-pass-box,black,5,.2,0,0);");
			toolbar.getItems().addAll(buttonBar);
			
			exContainer.getChildren().add(toolbar);
			return exContainer;
		}
	}
	
	
	class Footer{
		public VBox FooterParent(String _message, Color _color){
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
			
	        Label lbl_StatusTitle = new Label("Status : ");
	        lbl_StatusTitle.setFont(Font.font("George", FontWeight.BOLD, 13));
	        
	        Text lbl_Status = new Text();
	        lbl_Status.setFill(_color);
	        lbl_Status.setFont(Font.font("George", FontPosture.ITALIC, 13));
	        lbl_Status.setText(_message.toString());
	        
	        Button btn_notification = new Button("About");
	        btn_notification.setStyle("-fx-font: 10px Tahoma;" +
									    "-fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);" + 
									    "-fx-stroke: black;" + 
									    "-fx-stroke-width: 1;-fx-background-insets: 0, 1, 2 1 1 1;-fx-background-radius: 5px;");
			
			
			
			buttonBar.getChildren().addAll(lbl_StatusTitle, lbl_Status, rightSpacer, btn_notification);
			
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
		public VBox bodyParent(){
			VBox exContainer = new VBox();
			exContainer.setMinSize(1000, 500);
			
			TabPane tabPane = new TabPane();
			tabPane.setStyle("" +
								"-fx-background-insets: 0 1 0 1,0,0;");
			
		    
		    Tab jobsTab = new Tab("Find Jobs");
		    
		    //jobsTab.setStyle("-fx-background-color: #e6e6e6;-fx-alignment: CENTER;-fx-text-fill: #828282;-fx-font-size: 12px;-fx-font-weight: bold;");
		    
		    Tab historyTab = new Tab("Applied History");
		    
			if(!tabPane.getTabs().contains(jobsTab) && !tabPane.getTabs().contains(historyTab))
			{
				tabPane.getTabs().add(jobsTab);
				tabPane.getTabs().add(historyTab);
				
			}
			
			/*if(jobsTab.isSelected()){
				jobsTab.setStyle("-fx-background-color: #3c3c3c;-fx-alignment: CENTER;-fx-text-fill: #96b946;");
				//tabPane.setStyle("-fx-background-color: #3c3c3c;");
			}*/
			
			tabPane.getSelectionModel().select(jobsTab);
			
			
			historyTab.setOnCloseRequest(e -> {    
				if(tabPane.getTabs().contains(historyTab))
				{
					tabPane.getTabs().remove(historyTab);
				}
			    //e.consume();
			});
			
			// Contents Adding
			JVParent JobViewer = new JVParent();
			VBox tabJobs_vBox = new VBox();
			
			tabJobs_vBox = JobViewer.createParent("#b5bcc6");
			
			jobsTab.setContent(tabJobs_vBox);
			
			exContainer.getChildren().add(tabPane);
			return exContainer;
		}
	}
}
