package Army.Regiments;

import Army.Type.DamageType;

public class FootmanRegiment extends BaseRegiment{
	
	///Standard unit
	//Morale breaks for this unit below + experience/10
	
	//Experience gain varies by below
	private int xpmodifier = 10;
	
	public FootmanRegiment()
	{
		super();
		damageType = DamageType.BLUNT;
		weaknessType = new DamageType[4];
		weaknessType[0] = DamageType.SHOCK;
		weaknessType[1] = DamageType.RANGEDSHOCK;
		weaknessType[2] = DamageType.EXPLOSIVE;
		weaknessType[3] = DamageType.SLASH;
		name = "Footman " + generateName();
		moralebase = 10;
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
			System.err.println("Crossbow Regiment has broken");
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
		System.out.println("Footman Regiment XP +" + addedExperience);
	}
	
	public String toString()
	{
		return name;
	}

}
