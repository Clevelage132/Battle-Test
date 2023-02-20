package Army.Regiments;

import Army.Type.DamageType;

public class SpearmanRegiment extends BaseRegiment{
	
	///High morale mid tier unit
	//Morale breaks for this unit below + experience/10
	
	//Experience gain varies by below
	private int xpmodifier = 20;
	
	public SpearmanRegiment()
	{
		super();
		damageType = DamageType.PIERCE;
		weaknessType = new DamageType[2];
		weaknessType[0] = DamageType.RANGEDSHOCK;
		weaknessType[1] = DamageType.EXPLOSIVE;
		name = "Spearman " + generateName();
		moralebase = 20;
	}
	
	@Override
	public void damageMorale(double morale) 
	{
		if(super.morale-morale <= moralebase+(experience/10))
		{
			super.morale -= morale*(((randomiser.nextInt(200)+100)/100.0));
			isBroken = true;
			System.err.println("Spearman Regiment has broken");
		}
		else
		{
			if(super.morale-morale <= 0)
			{
				super.morale = 0;
			}
			else
			{
				super.morale -= morale;
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
		System.out.println("Spearman Regiment XP +" + addedExperience);
	}
	
	public String toString()
	{
		return name;
	}

}
