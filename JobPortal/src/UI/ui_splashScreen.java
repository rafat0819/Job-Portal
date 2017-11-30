package UI;


import javafx.application.Application;
import javafx.application.Preloader;
import javafx.application.Preloader.PreloaderNotification;
import javafx.application.Preloader.StateChangeNotification;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ui_splashScreen extends Preloader{
	private Stage splashScreen;
	
	public void start(Stage primaryStage) {
		splashScreen = primaryStage;
		//splashScreen.initStyle(StageStyle.TRANSPARENT);
        splashScreen.setScene(createScene());
        splashScreen.show();
	}
	
	public Scene createScene() {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 300, 200);
        return scene;
    }

    public void handleApplicationNotification(PreloaderNotification notification) {
        if (notification instanceof StateChangeNotification) {
            splashScreen.hide();
        }
    }
}
