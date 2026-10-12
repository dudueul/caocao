using UnityEngine;

public class UnitDataDevolved : UnitData
{
	private UserUnit m_userUnit;

	private static IntVector2 ms_invalidPos;

	public override UserUnit userUnit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public GameObject gameObjectOnTile
	{
		get
		{
			return null;
		}
		private set
		{
		}
	}

	public override IntVector2 gridPos
	{
		get
		{
			return default(IntVector2);
		}
	}

	protected override void ProcessFirstAppearance()
	{
	}

	protected override void Register2TroopMan()
	{
	}

	public override void SetStat(bool init)
	{
	}

	public override void RemoveAllUnitCondition()
	{
	}
}
