package Army.Regiments;

import Army.Type.DamageType;

public class GrenadierRegiment extends BaseRegiment{
	
	///Very Low Morale, very high damage
	//Morale breaks for this unit below + experience/10
	
	//Experience gain varies by below
	private int xpmodifier = 50;
	
	public GrenadierRegiment()
	{
		super();
		damageType = DamageType.EXPLOSIVE;
		weaknessType = new DamageType[4];
		weaknessType[0] = DamageType.PIERCE;
		weaknessType[1] = DamageType.SHOCK;
		weaknessType[2] = DamageType.RANGEDSHOCK;
		weaknessType[3] = DamageType.EXPLOSIVE;
		name = "Grenadier " + generateName();
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
			System.err.println("Grenadier Regiment has broken");
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
		System.out.println("Grenadier Regiment XP +" + addedExperience);
	}
	
	public String toString()
	{
		return name;
	}

}
