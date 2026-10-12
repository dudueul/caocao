using System;
using System.Collections.Generic;
using LitJson;

public class UserArenaUnits : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserArenaUnit> m_srl2ArenaUnit;

	private List<ulong> m_srls;

	private Dictionary<ulong, ulong> srl2UnitSrl;

	private Dictionary<ulong, ulong> unitSrl2Srl;

	public Action deserializeCallback;

	private List<UserArenaUnit> upsertedList;

	public Dictionary<ulong, UserArenaUnit> srl2ArenaUnit
	{
		get
		{
			return null;
		}
	}

	[DoNotJsonfy]
	public List<ulong> srls
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserArenaUnit get_Item(ulong srl)
	{
		return null;
	}

	public void ClearSrls()
	{
	}

	public ulong Srl2UnitSrl(ulong srl)
	{
		return 0uL;
	}

	public ulong UnitSrl2Srl(ulong unitSrl)
	{
		return 0uL;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public bool IsEquippedPassive(ulong passiveSrl)
	{
		return false;
	}

	public ulong GetEquippedUnitPassive(ulong passiveSrl)
	{
		return 0uL;
	}

	public UserArenaUnit GetArenaUnit(UserUnit userUnit)
	{
		return null;
	}

	public List<int> GetUnitIds()
	{
		return null;
	}
}
