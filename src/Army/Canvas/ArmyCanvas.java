package Army.Canvas;

import Army.Handler.ArmyHandler;
import Army.Regiments.BaseRegiment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ArmyCanvas extends HBox
{
	private ArmyHandler ah;
	private boolean isBattlePhase;
	//Button panel
	private Pane buttonPanel = new Pane();
	public Button btnTick = new Button("Start");
	public Button btnTickTen = new Button("Tick 10");
	private TextArea textArea = new TextArea();
	
	//Regiment list
	private ObservableList<String> regimentList1 = FXCollections.observableArrayList();
	private ObservableList<String> regimentList2 = FXCollections.observableArrayList();
	
	private ListView lView1 = new ListView();
	private ListView lView2 = new ListView();
	
	//Events list
	private ObservableList<String> eventList = FXCollections.observableArrayList();
	private ListView lEvent = new ListView();
	
	//tick data panel
	private Pane tickPanel = new Pane();
	
	//regiment list panel
	private Pane regimentPanel = new Pane();
	
	public ArmyCanvas(ArmyHandler ah)
	{
		this.ah = ah;
		
		///BUTTON PANEL
		buttonPanel.setMinSize(200, 500);
		btnTick.setMinWidth(100);
		btnTickTen.setMinWidth(100);
		
		isBattlePhase = true;
		btnTick.setOnAction(e ->
		{
			textArea.setText("");
			int coin = 0;
			ah.clearEvents();
			ah.addTitle("Single Tick");
			if(isBattlePhase)
			{
				ah.clearMajorEvents();
				isBattlePhase = false;
				coin = ah.coinToss();
				if(coin == 0)
				{
					ah.tickBattle(0, 1);
					ah.tickBattle(1, 0);
				}
				else
				{
					ah.tickBattle(1, 0);
					ah.tickBattle(0, 1);
				}
				if(ah.army[0].dead)
				{
					Alert ab = new Alert(AlertType.INFORMATION,"Army 1 is dead");
					ab.setTitle("Army Simulator");
					ab.setHeaderText("Army Dead");
					ab.show();
				}
				else if(ah.army[1].dead)
				{
					Alert ab = new Alert(AlertType.INFORMATION,"Army 2 is dead");
					ab.setTitle("Army Simulator");
					ab.setHeaderText("Army Dead");
					ab.show();
				}
				btnTick.setText("Recover");
				reloadRegiments();
				reloadEvents();
				textArea.appendText(" __________________________________ \n Army " + (coin+1) + "\n Wins the coin toss and attacks first" + "\n __________________________________\n");
			}
			else
			{
				ah.tickRecover();
				isBattlePhase = true;
				btnTick.setText("Battle");
				reloadRegiments();
				reloadEvents();
			}
			textArea.appendText(ah.getEvents());
		});
		
		btnTickTen.setOnAction(e->{
			textArea.setText("");
			ah.clearEvents();
			for(int i=0;i<10;i++)
			{
				ah.addTitle("Tick " + String.valueOf(i));
				ah.clearMajorEvents();
				if(isBattlePhase)
				{
					isBattlePhase = false;
					int coin = ah.coinToss();
					if(coin == 0)
					{
						ah.tickBattle(0, 1);
						ah.tickBattle(1, 0);
					}
					else
					{
						ah.tickBattle(1, 0);
						ah.tickBattle(0, 1);
					}
					
					btnTick.setText("Recover");
					reloadRegiments();
					reloadEvents();
				}
				else
				{
					ah.tickRecover();
					isBattlePhase = true;
					btnTick.setText("Battle");
					reloadRegiments();
					reloadEvents();
				}
				textArea.setText(ah.getEvents());
			}
			if(ah.army[0].dead)
			{
				Alert ab = new Alert(AlertType.INFORMATION,"Army 1 is dead");
				ab.setTitle("Army Simulator");
				ab.setHeaderText("Army Dead");
				ab.show();
			}
			else if(ah.army[1].dead)
			{
				Alert ab = new Alert(AlertType.INFORMATION,"Army 2 is dead");
				ab.setTitle("Army Simulator");
				ab.setHeaderText("Army Dead");
				ab.show();
			}
		});
		
		VBox vb = new VBox();
		
		//Events list
		Button btnRefreshEvents = new Button("Refresh");
		reloadEvents();
		lEvent.maxWidth(200);
		lEvent.minHeight(200);
		
		btnRefreshEvents.setOnAction(e->{
			reloadEvents();
		});
		
		vb.getChildren().addAll(btnTick,btnTickTen,lEvent,btnRefreshEvents);
		buttonPanel.getChildren().add(vb);
		
		///TICK PANEL
		tickPanel.setMinSize(200, 500);
		textArea.setMinSize(200, 500);
		tickPanel.getChildren().add(textArea);
		
		///REGIMENT PANEL
		regimentPanel.setMinSize(200, 500);
		reloadRegiments();
		HBox hb = new HBox();
		hb.getChildren().addAll(lView1,lView2);
		VBox vb1 = new VBox();
		
		Button btnRefresh = new Button("Refresh");
		vb1.getChildren().add(hb);
		vb1.getChildren().add(btnRefresh);
		regimentPanel.getChildren().add(vb1);
		btnRefresh.setOnAction(e->{
			reloadRegiments();
		});
		
		///Add to HBOX
		this.getChildren().add(buttonPanel);
		this.getChildren().add(tickPanel);
		this.getChildren().add(regimentPanel);
	}
	
	private void reloadRegiments()
	{
		regimentList1.clear();
		regimentList2.clear();
		regimentList1.add("Army 1");
		regimentList2.add("Army 2");
		for(int i=0;i<ah.army[0].getNumRegiments();i++)
		{
			regimentList1.add(ah.army[0].regiments[i].getInfo());
			regimentList2.add(ah.army[1].regiments[i].getInfo());
		}
		lView1.setItems(regimentList1);
		lView2.setItems(regimentList2);
	}
	
	private void reloadEvents()
	{
		eventList.clear();
		eventList.add("Major Events");
		for(String s : ah.getMajorEvents())
		{
			eventList.add(s);
		}
		lEvent.setItems(eventList);
	}
}
