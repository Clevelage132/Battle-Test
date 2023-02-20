import Army.Canvas.ArmyCanvas;
import Army.Canvas.ArmyMenu;
import Army.Handler.ArmyHandler;
import Army.Regiments.BaseRegiment;
import Army.Regiments.SpearmanRegiment;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application
{
	public static void main(String args[])
	{
		launch();
	}

	private ArmyHandler ah;
	
	private HBox content;
	private ArmyMenu menu;
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		//ArmyHandler Testing
		ah = new ArmyHandler();
		
		//Testing done
		
		VBox root = new VBox();
		content = new ArmyCanvas(ah);
		menu = new ArmyMenu();
		
		//Assign to root
		root.getChildren().add(menu);
		root.getChildren().add(content);
		
		//Menu Buttons
		menu.menuNew.setOnAction(e->{
			ah = new ArmyHandler();
			content = new ArmyCanvas(ah);
			root.getChildren().clear();
			root.getChildren().add(menu);
			root.getChildren().add(content);
			
		});
		
		menu.menuExit.setOnAction(e->{
			System.exit(1);
		});
		
		//Adding scene and display
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		
		//Changing stage
		primaryStage.setTitle("Army Simulator");
		primaryStage.show();
	}
}
