using System.Collections.Generic;

public abstract class UserSpellBase
{
	private UserSpell pdUserSpell;

	public UserSpell UserSpellData
	{
		get
		{
			return null;
		}
	}

	public int pdUserSpellId { get; set; }

	public int SpellValue
	{
		get
		{
			return 0;
		}
	}

	public int Cost
	{
		get
		{
			return 0;
		}
	}

	public virtual void Apply()
	{
	}

	public virtual void Apply(List<UnitData> targetUnitDatas)
	{
	}

	protected List<UnitData> GetTargetList(EnumData.TargetType targetType)
	{
		return null;
	}
}
