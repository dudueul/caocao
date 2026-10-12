using Caocao;

public class MapObjectCondTurnWithSide : MapObjectConditionChecker
{
	private const string m_thisConditionType = "TurnWithSide";

	private int m_turnMapObjCreated;

	private UnitSide m_turnOwner;

	public override void SetTarget(BattleMapObject target)
	{
	}

	protected override void CheckCondition()
	{
	}

	public override void DetachCheckFunc()
	{
	}
}
