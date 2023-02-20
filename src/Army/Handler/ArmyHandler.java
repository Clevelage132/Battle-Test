package Army.Handler;

import java.util.ArrayList;
import java.util.Random;

import Army.Regiments.BaseRegiment;

public class ArmyHandler 
{
	private Random randomiser = new Random();
	private int numArmies = 2;
	public Army[] army = new Army[numArmies];
	private int numRegiments;
	
	private ArrayList<String> event = new ArrayList<String>();
	private ArrayList<String> majorEvent = new ArrayList<String>();
	private int eventNum;
	
	private int brokenPenalty=0;
	
	public ArmyHandler()
	{
		//numRegiments = randomiser.nextInt(4)+1;
		numRegiments = 3;
		
		//Add regiments to army
		System.err.println("test");
		army[0] = new Army(numRegiments);
		army[1] = new Army(numRegiments);
			
		//Number of events that have happened are 0
		eventNum = 0;
	}
	
	public int coinToss()
	{
		//determine which team attacks first
		int coin = randomiser.nextInt(2);
		System.out.println(coin);
		return coin;
	}
	
	public void tickBattle(int armyNum, int enemyNum)
	{	
		addTitle("Army " + String.valueOf(armyNum+1));
		
		//for(int i=0;i<numRegiments;i++)
		//{
		//	addInfo("\n" + army[armyNum].regiments[i].getInfo());
		//}
		
		for(int i=0;i<numRegiments;i++)
		{
			//Find capable enemy and fight
			if(!army[armyNum].regiments[i].isDead() && !army[armyNum].regiments[i].isBroken())
			{
				boolean bfound = false;
				//while loop to find non busy unit
				int count = 0;
				while(count < numRegiments && !bfound)
				{
					bfound = false;
					//Not dead, not busy
					if(!army[enemyNum].regiments[count].isDead() && !army[enemyNum].regiments[count].isBusy())
					{
						//Found opponent
						bfound = true;
						
						//damage enemy
						double damage = army[enemyNum].regiments[count].damageHealth(army[armyNum].regiments[i]);
						
						//enemy gain experience
						double base = army[armyNum].regiments[i].CalcDamage();
						army[enemyNum].regiments[count].addExperience(base);
						
						//enemy lose morale
						army[enemyNum].regiments[count].damageMorale(base);
						
						//set unit as busy
						army[enemyNum].regiments[count].Busy();
						addEvent(army[armyNum].regiments[i].toString() + " has attacked \n" + army[enemyNum].regiments[count].toString() + " for " + String.valueOf(damage));
					}
					count++;
				}
				
				//while loop to find busy unit
				if(!bfound)
				{
					int newCount = 0;
					while(newCount < numRegiments && !bfound)
					{
						if(army[enemyNum].regiments[newCount].isBusy())
						{
							//Found opponent
							bfound = true;
							
							//damage enemy
							double damage = army[enemyNum].regiments[newCount].damageHealth(army[armyNum].regiments[i]);
							
							//enemy gain experience
							double base = army[armyNum].regiments[i].CalcDamage();
							army[enemyNum].regiments[newCount].addExperience(base);
							
							//enemy lose morale
							army[enemyNum].regiments[newCount].damageMorale(base);
							addEvent(army[armyNum].regiments[i].toString() + " has helped attack \n" + army[enemyNum].regiments[newCount].toString() + " for " + String.valueOf(damage));
						}
						newCount++;
					}
				}
			}
		}
		
		//is the battle over?
		int count = 0;
		int brokenCount = 0;
		for(BaseRegiment r : army[enemyNum].regiments)
		{
			
			if(r.isDead())
			{
				count++;
				addEvent("xxxxxxxxxxxx---- Army " + String.valueOf(enemyNum+1) + " " + r.toString() + " is Dead ----xxxxxxxxxxxx");
				addMajorEvent("Army " + String.valueOf(enemyNum+1) + " " + r.toString() + " is Dead");
			}
			else if(r.isBroken())
			{
				brokenCount++;
				addEvent("Army " + String.valueOf(enemyNum+1) + " " + r.toString() + " is Broken");
			}
			
		}
		if(count >= numRegiments)
		{
			army[enemyNum].dead = true;
			addMajorEvent("Army " + String.valueOf(enemyNum+1) + " is Dead");
		}
		else if(brokenCount >= numRegiments-count)
		{
			brokenPenalty++;
			addMajorEvent("Army " + String.valueOf(enemyNum+1) + " is on broken penalty " + String.valueOf(brokenPenalty));
			if(brokenPenalty >= 3)
			{
				army[enemyNum].dead = true;
				addMajorEvent("Army " + String.valueOf(enemyNum+1) + " has broken and surrendered");
			}
		}
		else
		{
			army[enemyNum].dead = false;
		}
	}
	
	public void tickRecover()
	{
		int count = 0;
		for(Army a : army)
		{
			boolean bDisease = false;
			//Disease
			if(randomiser.nextInt(100) < 25)
			{
				bDisease = true;
			}
			
			count++;
			addTitle("Army " + String.valueOf(count));
			for(int i=0;i<numRegiments;i++)
			{
				a.regiments[i].notBusy();
				//Regiment is broken but not dead(heal morale)
				if(a.regiments[i].isBroken() && !a.regiments[i].isDead())
				{
					double morale = randomiser.nextInt(50)/10.0;
					a.regiments[i].improveMorale(morale,a.regiments[i].moralebase);
					System.out.println(a.regiments[i].moralebase);
					addEvent(a.regiments[i].toString() + "\n increased morale by " + String.valueOf(morale));
				}
				//Regiment is not dead and not broken (heal regiment)
				else if(!a.regiments[i].isDead())
				{
					double morale = (randomiser.nextInt(20))/10.0;
					double health = (randomiser.nextInt(30))/10.0;
					a.regiments[i].improveHealth(health);
					a.regiments[i].improveMorale(morale,a.regiments[i].moralebase);
					System.out.println(a.regiments[i].moralebase);
					addEvent(a.regiments[i].toString() + "\n increased morale by " + String.valueOf(morale) + "\n increased health by " + String.valueOf(health));
				}
				//Leave regiment if dead
				
				//Disease
				double randomDisease = randomiser.nextInt(100);
				if(bDisease && randomDisease>50)
				{
					double diseaseMorale = randomiser.nextInt(100)/10.0;
					double diseaseHealth = randomiser.nextInt(100)/10.0;
					a.regiments[i].damageMorale(diseaseMorale);
					addEvent(a.regiments[i].toString() + "\n reduced morale by " + String.valueOf(diseaseMorale) + " from disease");
					addEvent(a.regiments[i].toString() + "\n reduced health by " + String.valueOf(diseaseHealth) + " from disease");
				}
			}
		}
	}
	
	public void addTitle(String message)
	{
		event.add("\n" + message.toUpperCase() + "\n");
	}
	
	public void addInfo(String message)
	{
		event.add(message + "\n");
	}
	
	public void clearEvents() 
	{
		event.clear();
	}
	
	private void addEvent(String message)
	{
		eventNum++;
		event.add("\n" + eventNum + ":" + message + "\n");
	}
	
	public String getEvents()
	{
		String value = "";
		for(String s : event)
		{
			value += s;
		}
		return value;
	}
	
	public void clearMajorEvents() 
	{
		majorEvent.clear();
	}
	
	private void addMajorEvent(String message)
	{
		majorEvent.add("MAJOR EVENT:\n" + message + "\n");
	}
	
	public ArrayList<String> getMajorEvents()
	{
		return majorEvent;
	}
}

