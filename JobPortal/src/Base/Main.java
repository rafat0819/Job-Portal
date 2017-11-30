package Base;

import UI.ui_LoginForm;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	
	public void start(Stage stage){
		try{
			new ui_LoginForm().start(new Stage());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String []argument){
		launch(argument);
	}
}

