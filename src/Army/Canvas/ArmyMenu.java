package Army.Canvas;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class ArmyMenu extends MenuBar
{
	public MenuItem menuNew = new MenuItem("_New"); 
	public MenuItem menuExit = new MenuItem("_Exit");
	public Menu menuFile = new Menu("_File");
	
	public ArmyMenu()
	{
		menuFile.getItems().addAll(menuNew,menuExit);
		this.getMenus().add(menuFile);
	}
}
