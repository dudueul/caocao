public class SPActionTreasureRuinAtk : EffectBase
{
	public SPActionTreasureRuinAtk(AttackBase attack, UnitData attackUnit, UnitData targetUnit)
		: base(null, null)
	{
	}

	private bool IsTreasureOriginSp(SpActionBase spAction)
	{
		return false;
	}

	private int GetLessRes(EnumData.UnitResType resType, int addValue)
	{
		return 0;
	}
}
