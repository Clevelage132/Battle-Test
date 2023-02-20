package Army.Regiments;

import Army.Type.DamageType;

public class HorsemanRegiment extends BaseRegiment{
	
	///Low Morale, High damage
	//Morale breaks for this unit below + experience/10
	
	//Experience gain varies by below
	private int xpmodifier = 20;
	
	public HorsemanRegiment()
	{
		super();
		damageType = DamageType.SHOCK;
		weaknessType = new DamageType[3];
		weaknessType[0] = DamageType.PIERCE;
		weaknessType[1] = DamageType.RANGEDPIERCE;
		weaknessType[2] = DamageType.SLASH;
		name = "Horseman " + generateName();
		moralebase = 40;
	}
	
	@Override
	public void damageMorale(double morale) 
	{
		if(super.morale-morale <= moralebase+(experience/10))
		{
			if(super.morale-morale <= 0)
			{
				super.morale = 0;
			}
			else
			{
				super.morale -= morale*(((randomiser.nextInt(200)+100)/100.0));
			}
			isBroken = true;
			System.err.println("Horseman Regiment has broken");
		}
		else
		{
			if(super.morale-morale <= 0)
			{
				super.morale = 0;
			}
			else
			{
				super.morale -= morale*(((randomiser.nextInt(200)+100)/100.0));
			}
		}
	}

	@Override
	public void addExperience(double experience) 
	{
		double modifier = randomiser.nextInt(xpmodifier)+100;
		modifier = modifier/100.0;
		double addedExperience = Math.round(experience*modifier*100.0)/100.0;
		this.experience += addedExperience;
		System.out.println("Horseman Regiment XP +" + addedExperience);
	}
	
	public String toString()
	{
		return name;
	}

}
