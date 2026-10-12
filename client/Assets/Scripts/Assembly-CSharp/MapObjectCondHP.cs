using System;

public class MapObjectCondHP : MapObjectConditionChecker
{
	private float m_befHPPercent;

	private const string m_thisConditionType = "HP";

	private UnitData m_targetUnitData;

	public override void SetTarget(BattleMapObject target)
	{
	}

	protected override void CheckCondition()
	{
	}

	public override void DetachCheckFunc()
	{
	}

	private void ChangeUnitSize(object sender, EventArgs args)
	{
	}
}
