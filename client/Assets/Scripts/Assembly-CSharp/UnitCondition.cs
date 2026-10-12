using System;
using Caocao;
using CodeStage.AntiCheat.ObscuredTypes;

[Serializable]
public class UnitCondition
{
	private ObscuredInt m_conditionID;

	private ObscuredInt m_occuredTurn;

	private ObscuredInt m_occuredTurnOwner;

	private ObscuredInt m_triggerSkillId;

	private ObscuredInt m_stackCount;

	private ObscuredInt m_maxStackCount;

	private ObscuredInt[] m_customValues;

	public readonly UnitData triggerUnit;

	public EnumData.Conditions conditionId
	{
		get
		{
			return EnumData.Conditions.NONE;
		}
	}

	public int occuredTurn
	{
		get
		{
			return 0;
		}
	}

	public UnitSide occuredTurnOwner
	{
		get
		{
			return UnitSide.OWN;
		}
	}

	public EnumData.Skills triggerSkillId
	{
		get
		{
			return EnumData.Skills.NONE;
		}
	}

	public int StackCount
	{
		get
		{
			return 0;
		}
	}

	public int MaxStackCount
	{
		get
		{
			return 0;
		}
	}

	public bool IsMaxStacked
	{
		get
		{
			return false;
		}
	}

	public UnitCondition(EnumData.Skills skillId, EnumData.Conditions conditionId, int occuredTurn, UnitSide occuredTurnOwner, UnitData triggerUnit = null)
	{
	}

	public float GetConditionPowerOfTriggerSkill()
	{
		return 0f;
	}

	public void IncreaseStackCount(int amount = 1)
	{
	}

	public void DecreaseStackCount(int amount = 1)
	{
	}

	public void SetCustomValue(int index, int value)
	{
	}

	public int GetCustomValue(int index)
	{
		return 0;
	}

	public int GetDescValue()
	{
		return 0;
	}
}
