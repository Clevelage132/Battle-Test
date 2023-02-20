package Army.Regiments;

import java.io.File;
import java.util.Random;

import Army.Type.DamageType;
import Helper.NameGenerator;

public abstract class BaseRegiment 
{
	protected double hitpoints;
	protected double experience;
	protected double morale;
	protected boolean isBroken;
	protected boolean isDead;
	protected boolean isBusy;
	protected String name;
	public int moralebase;
	protected DamageType damageType;
	protected DamageType[] weaknessType;
	protected Random randomiser = new Random();
	
	public BaseRegiment(double hitpoints, double experience, double morale)
	{
		this.hitpoints = hitpoints;
		this.experience = experience;
		this.morale = morale;
		this.isBroken = false;
		this.isDead = false;
	}
	
	public BaseRegiment(double hitpoints, double experience, double morale, boolean isBroken, boolean isDead)
	{
		this.hitpoints = hitpoints;
		this.experience = experience;
		this.morale = morale;
		this.isBroken = isBroken;
		this.isDead = isDead;
	}
	
	public BaseRegiment()
	{
		hitpoints = 100;
		morale = 100;
		experience = 0;
		isBroken = false;
		isDead = false;
	}
	
	protected String generateName()
	{
		NameGenerator ng = new NameGenerator(new File("data/names/Names.txt"), new File("data/names/Surnames.txt"), new File("data/names/Nicknames.txt"));
		return ng.getNickName();
	}
	
	public double CalcDamage()
	{
		///Damage types
		//PIERCE
		//RANGEDPIERCE
		//BLUNT
		//SHOCK
		//RANGEDSHOCK
		double damage = (randomiser.nextInt(100)/10+1)*morale/100;
		
		switch(damageType)
		{
		case RANGEDPIERCE:
			return damage*0.8;
		case BLUNT:
			return damage*0.5;
		default:
			return damage;
		}
	}
	
	public double damageHealth(BaseRegiment enemy)
	{
		boolean weakness = false;
		for(DamageType w : weaknessType)
		{
			if(enemy.damageType == w)
			{
				weakness = true;
			}
		}
		
		//calc damage add weakness
		double damage = 0;
		if(weakness)
		{
			damage = enemy.CalcDamage()*1.50;
		}
		else
		{
			damage = enemy.CalcDamage();
		}
		
		if(isBroken)
		{
			damage = enemy.CalcDamage()*5;
		}
		else
		{
			damage = enemy.CalcDamage();
		}
		
		//deal the damage
		if(hitpoints-damage <= 0)
		{
			isDead = true;
			hitpoints = 0;
			System.err.println(this.toString() + " has died");
		}
		else
		{
			hitpoints -= damage;
		}
		return damage;
	}
	
	public void improveHealth(double health) 
	{
		if(hitpoints+health >= 100)
		{
			hitpoints = 100;
		}
		else
		{
			hitpoints += health;
		}
	}
	
	public boolean isDead()
	{
		return isDead;
	}
	
	public boolean isBroken()
	{
		return isBroken;
	}
	
	public double getExperience()
	{
		return experience;
	}
	
	public void notBusy()
	{
		isBusy = false;
	}
	
	public void Busy()
	{
		isBusy = true;
	}
	
	public boolean isBusy()
	{
		return isBusy;
	}
	
	public double getHealth()
	{
		return hitpoints;
	}
	
	public double getMorale()
	{
		return morale;
	}
	
	public String getInfo()
	{
		String output = this.toString() + "\nDamage Type: " + damageType + "\nHealth: " + getHealth() + "\nExperience: " + getExperience() + "\nMorale: " + getMorale() + "(" + moralebase + ")" + "\nBroken?: " + isBroken() + "\nDead?: " + isDead;
		return output;
	}
	
	public abstract void damageMorale(double morale);
	
	public void improveMorale(double morale, int baseMorale) 
	{
		if(this.morale+morale >= 100)
		{
			this.morale = 100;
		}
		else
		{
			this.morale += morale;
			if(this.morale >= baseMorale)
			{
				isBroken = false;
			}
		}
	}

	public abstract void addExperience(double experience);
}
