package Army.Handler;

import java.util.Random;

import Army.Regiments.ArcherRegiment;
import Army.Regiments.BaseRegiment;
import Army.Regiments.CrossbowRegiment;
import Army.Regiments.FootmanRegiment;
import Army.Regiments.GrenadierRegiment;
import Army.Regiments.HalberdierRegiment;
import Army.Regiments.HorsemanRegiment;
import Army.Regiments.KnightRegiment;
import Army.Regiments.SpearmanRegiment;

public class Army 
{
	public BaseRegiment[] regiments;
	private Random randomiser = new Random();
	private int numRegiments;
	public boolean dead;
	
	public Army(int numRegiments)
	{
		regiments = new BaseRegiment[numRegiments];
		this.numRegiments = numRegiments;
		generateRegiments();
		System.out.println("Generated Army");
	}
	
	public void generateRegiments()
	{
		for(int i=0;i<numRegiments;i++)
		{
			int value = randomiser.nextInt(8);
			switch(value)
			{
			case 0:
				regiments[i] = new FootmanRegiment();
				System.out.println(i + ": Created Footman");
				break;
			case 1:
				regiments[i] = new SpearmanRegiment();
				System.out.println(i + ": Created Spearman");
				break;
			case 2:
				regiments[i] = new ArcherRegiment();
				System.out.println(i + ": Created Archer");
				break;
			case 3:
				regiments[i] = new HorsemanRegiment();
				System.out.println(i + ": Created Horseman");
				break;
			case 4:
				regiments[i] = new CrossbowRegiment();
				System.out.println(i + ": Created Crossbow");
				break;
			case 5:
				regiments[i] = new KnightRegiment();
				System.out.println(i + ": Created HeavyHorseman");
				break;
			case 6:
				regiments[i] = new GrenadierRegiment();
				System.out.println(i + ": Created Grenadier");
				break;
			case 7:
				regiments[i] = new HalberdierRegiment();
				System.out.println(i + ": Created Halberdier");
				break;
			}
		}
	}
	
	public BaseRegiment getRegiment(int pos)
	{
		return regiments[pos];
	}
	
	public int getNumRegiments()
	{
		return numRegiments;
	}
}
