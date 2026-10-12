using System;
using System.Collections.Generic;
using LitJson;

public class UserRuneSlots : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserRuneSlot> m_srl2RuneSlots;

	private List<ulong> m_srls;

	private Dictionary<int, SortedList<int, UserRuneSlot>> m_unitId2RuneSlots;

	public Dictionary<ulong, UserRuneSlot> srl2RuneSlots
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserRuneSlot get_Item(ulong srl)
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

	public SortedList<int, UserRuneSlot> GroupByUnitId(int unitId)
	{
		return null;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	private void Categorize(List<UserRuneSlot> upsertList, List<UserRuneSlot> removeList)
	{
	}

	private void ApplyRuneStat(UserRuneSlot runeSlot)
	{
	}
}
