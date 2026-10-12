using System.Collections.Generic;

public class AttackBase
{
	public class TargetResult
	{
		private UnitData m_targetUnit;

		private List<ResultPerUnit> m_listResultPerUnit;

		public int rawDamage { get; set; }

		public UnitData targetUnit
		{
			get
			{
				return null;
			}
		}

		public List<ResultPerUnit> listResultPerUnit
		{
			get
			{
				return null;
			}
		}

		public TargetResult(UnitData targetUnit)
		{
		}

		public ResultPerUnit AddResultPerUnit(UnitData triggerUnit, EnumData.Skills skillID, bool isDirectResult)
		{
			return null;
		}
	}

	protected Dictionary<UnitData, TargetResult> m_resultPerUnit;

	public Dictionary<UnitData, TargetResult> resultPerUnit
	{
		get
		{
			return null;
		}
	}

	public List<UnitData> targetUnitDatas
	{
		get
		{
			return null;
		}
	}

	public ResultPerUnit AddResultPerUnit(UnitData triggerUnit, UnitData targetUnit, float attackSuccessRate, bool isDirectResult, EnumData.Skills skillID = EnumData.Skills.NONE)
	{
		return null;
	}

	public ResultPerUnit AddResultPerUnit(UnitData triggerUnit, UnitData targetUnit, bool isDirectResult, EnumData.Skills skillID = EnumData.Skills.NONE)
	{
		return null;
	}

	public void AddHPDamage(UnitData targetUnit, int rawDamage)
	{
	}

	public ResultPerUnit GetFirstResultPerUnit()
	{
		return null;
	}

	public ResultPerUnit GetFirstResultPerUnit(UnitData targetUnit)
	{
		return null;
	}

	public int GetTargetDamage(UnitData targetUnit)
	{
		return 0;
	}

	public void ClearTargetsDamage(List<UnitData> targetUnits)
	{
	}
}
