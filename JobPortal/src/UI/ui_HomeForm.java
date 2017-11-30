package UI;

import java.util.Date;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ui_HomeForm extends Application {
	String username = "USERNAME";
	
	
	/* little components */
	Text txt_Message = new Text("Status : ");
	Text txt_Greetings = new Text("Welcome to Dashboard !");
	
	
	
	//////////////////////////
	BorderPane borderPane = new BorderPane();
    ToolBar toolbar = new ToolBar();
    //ToolBar bottoolbar = new ToolBar();
    HBox statusbar = new HBox();
    VBox appContent = new VBox(); 
    ListView list = new ListView();
    
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
    	// Main Setup
    	primaryStage.setTitle("Main Home");
    	Group root = new Group();
        Scene scene = new Scene(root, 1000, 800, Color.WHITE);
        
        // Super Items Settings
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
        

         /*Image image = new Image("http://cdn.wonderfulengineering.com/wp-content/uploads/2016/01/black-wallpaper-6.jpg");
	     // new BackgroundSize(width, height, widthAsPercentage, heightAsPercentage, contain, cover)
	     BackgroundSize backgroundSize = new BackgroundSize(500, 50, true, true, true, false);
	     // new BackgroundImage(image, repeatX, repeatY, position, size)
	     BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
	     // new Background(images...)
	     Background background = new Background(backgroundImage);
        
        toolbar.setBackground(background);*/
        
        /*String image = Application.class.getResource("C:/Users/User/workspace/JobPortal/Resourses/black-toolbar-bg").toExternalForm();
        toolbar.setStyle("-fx-background-image: url('" + image + "'); " +
                   "-fx-background-position: center center; " +
                   "-fx-background-repeat: stretch;");*/
        
        
        toolbar = new ToolBar(
			 
			 new Text("Welcome"),
			 leftSpacer,
			 rightSpacer,
			 new Separator(),
			 new Button("Profile"),
			 new Button("Settings"),
			 new Text("" + new Date().getTime()),
			 new Separator(),
			 new Text("Hello,  " + this.username),
			 new Button("Logout")
        		);
        
        
        
        String image = "http://previews.123rf.com/images/roystudio/roystudio1401/roystudio140100018/25243077-white-background-subtle-canvas-fabric-texture-and-vignette-Stock-Photo.jpg";
        //http://cdn.wonderfulengineering.com/wp-content/uploads/2016/01/black-wallpaper-6.jpg
        toolbar.setStyle("-fx-background-image: url(" + image + ");"
        		+ "-fx-background-repeat: stretch;-fx-background-position: center center;"
        		+ "-fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0);");
        //toolbar.getStyle(); "-fx-background: #6A0D45"
        toolbar.setMinSize(500, 50);
    	borderPane.setTop(toolbar);
        
        
        // Child Items Settings
    	//borderPane.setCenter(ViewDateBox());
    	
        
    	
    	borderPane.setLeft(buildView());
    	
        //borderPane.setCenter(appContent);
    	statusbar.getChildren().add(txt_Message);
    	HBox.setHgrow(txt_Message, Priority.ALWAYS);
        borderPane.setBottom(statusbar);
        
        borderPane.setPrefSize(1000, 800);
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        
        
        //listview editings
        
        
        BorderPane.setAlignment(list, Pos.TOP_LEFT);
        BorderPane.setMargin(list, new Insets(12,12,12,12));
        borderPane.setCenter(list);
        
        
        root.getChildren().add(borderPane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    Rectangle ViewDateBox(){
    	Rectangle rect = new Rectangle(20,20,200,200);
        
        rect.setArcHeight(15);
        rect.setArcWidth(15);

        rect.setStroke(Color.BLACK);
        return rect;
    }
    
    
    
    
    BorderPane buildView() {
        BorderPane root = new BorderPane();
        TabPane tabPanel = new TabPane();
        root.setCenter(tabPanel);
        Accordion accordion = new Accordion();
        Pane pane = null;
        TitledPane tiledPane;
        General1Bar general1 = new General1Bar();
        pane= general1.getView();
        tiledPane = new TitledPane("General1", pane);
        accordion.getPanes().add(tiledPane);

        General2Bar general2 = new General2Bar();
        pane = general2.getView();
         tiledPane = new TitledPane("General2", pane);
        accordion.getPanes().add(tiledPane);

        General3Bar general3 = new General3Bar();
        pane = general3.getView();
        tiledPane = new TitledPane("General3", pane);
        accordion.getPanes().add(tiledPane);

        root.setLeft(accordion);
        return root;
    }
    
    
    class General1Bar {

	    public Pane getView() {
	        Pane p = new Pane();
	        Button button = new Button("One");
	        Button button1 = new Button("Two");
	        VBox vBox = new VBox(5);
	        vBox.getChildren().addAll(button,button1);
	        p.getChildren().addAll(vBox);
	        return p;
	    }

	}

	class General2Bar {
	     public Pane getView() {
	        Pane p = new Pane();
	        Button button = new Button("One");
	        Button button1 = new Button("Two");
	        VBox vBox = new VBox(5);
	        vBox.getChildren().addAll(button,button1);
	        p.getChildren().addAll(vBox);
	        return p;
	    }

	}

	class General3Bar {
	    public Pane getView() {
	        Pane p = new Pane();
	        Button button = new Button("One");
	        Button button1 = new Button("Two");
	        VBox vBox = new VBox(5);
	        vBox.getChildren().addAll(button,button1);
	        p.getChildren().addAll(vBox);
	        return p;
	    }
	}
}
