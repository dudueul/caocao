using System;
using System.Collections.Generic;
using LitJson;

public class UserUnits : ITransmittable, iTransmitContainer
{
	public static ulong ms_srl;

	private Dictionary<ulong, UserUnit> m_srl2Units;

	private Dictionary<int, UserUnit> m_id2Units;

	private List<ulong> m_srls;

	private List<UserUnit> m_favorite;

	private List<UserUnit> m_favoriteOut;

	private List<UserUnit> m_other;

	private List<UserUnit> m_unitList;

	private bool m_sortDescend;

	private EnumData.SortType m_sortType;

	public Dictionary<ulong, UserUnit> srl2Units
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserUnit get_Item(ulong srl)
	{
		return null;
	}

	[DoNotJsonfy]
	public List<ulong> srls
	{
		get
		{
			return null;
		}
	}

	public Action deserializeCallback { get; set; }

	[DoNotJsonfy]
	public List<UserUnit> unitList
	{
		get
		{
			return null;
		}
	}

	public bool sortDescend
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public EnumData.SortType sortType
	{
		get
		{
			return EnumData.SortType.START;
		}
		set
		{
		}
	}

	public int totalCombatPower
	{
		get
		{
			return 0;
		}
	}

	private void CategorizeUnit()
	{
	}

	private void OrderUnitList()
	{
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void UpdateId2Units()
	{
	}

	public List<int> GetAddedUnitIds(JsonData units)
	{
		return null;
	}

	public bool IsEquippedItem(ulong unitSrl, ulong itemSrl)
	{
		return false;
	}

	public bool IsEquippedItem(ulong itemSrl)
	{
		return false;
	}

	public ulong GetSrlFromID(int unitID)
	{
		return 0uL;
	}

	public UserUnit GetUserUnitFromID(int unitID)
	{
		return null;
	}

	public bool HaveUnit(int unitID)
	{
		return false;
	}

	public void InjuryUnits()
	{
	}

	public List<ulong> GetRecoverableUnits()
	{
		return null;
	}

	public List<UserUnit> GetScenarioModeWhteList(int storybookId)
	{
		return null;
	}

	public List<UserUnit> GetRelationModeWhiteList(int relationId)
	{
		return null;
	}

	public void UpdateDefenseInfo()
	{
	}

	public bool isRetainingUnit(int unitId)
	{
		return false;
	}

	public int GetTotalCombatPower(List<ulong> unitSrls)
	{
		return 0;
	}
}
