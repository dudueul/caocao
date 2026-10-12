using System;
using System.Collections.Generic;
using Caocao;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

[Serializable]
public class UserBattleFieldDefenseGroup : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredInt m_fieldId;

	private ObscuredInt m_areaId;

	private List<BattleFieldDefenseUnit> m_units;

	private List<BattleFieldDefenseUnit> m_serverUnits;

	private UnitDirection m_direction;

	public ulong Srl
	{
		get
		{
			return 0uL;
		}
	}

	public int FieldID
	{
		get
		{
			return 0;
		}
	}

	public int AreaID
	{
		get
		{
			return 0;
		}
	}

	public List<BattleFieldDefenseUnit> Units
	{
		get
		{
			return null;
		}
	}

	public List<BattleFieldDefenseUnit> ServerUnits
	{
		get
		{
			return null;
		}
	}

	public UnitDirection direction
	{
		get
		{
			return UnitDirection.back;
		}
		set
		{
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public bool IsDefenseGroup(int unitId)
	{
		return false;
	}

	public BattleFieldDefenseUnit GetDefenseUnitEquippedItem(ulong itemSrl)
	{
		return null;
	}
}
