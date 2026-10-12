using Caocao;
using UnityEngine;

public class SkillSpawn : SkillBase
{
	public override EnumData.Skills skillID
	{
		get
		{
			return EnumData.Skills.NONE;
		}
	}

	public override EnumData.SkillTypes skillType
	{
		get
		{
			return EnumData.SkillTypes.ATK_FIRE;
		}
	}

	public SkillSpawn(UnitData castUnit, UnitData targetUnit)
		: base(null, null)
	{
	}

	private void spawnCreature(UnitData castUnit, UnitData targetUnit)
	{
	}

	private IntVector2 GetPos(IntVector2 pos, UnitDirection direction)
	{
		return default(IntVector2);
	}

	private void AddEffect(Transform tf)
	{
	}
}
