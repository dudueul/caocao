using System;
using System.Collections.Generic;
using LitJson;

public class UserTreasureQuickSlots : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserTreasureQuickSlot> m_srl2QuickSlot;

	private List<ulong> m_srls;

	private Dictionary<ulong, List<UserTreasureQuickSlot>> m_itemSrl2QuickSlot;

	// C# has no syntax for parameterized property 'Item'.
	public UserTreasureQuickSlot get_Item(ulong srl)
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

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public UserTreasureQuickSlot GetQuickSlot(int jobType, int deckId)
	{
		return null;
	}

	public Dictionary<int, UserTreasureQuickSlot> GetQuickSlots(int jobType)
	{
		return null;
	}

	public List<UserTreasureQuickSlot> GetQuickSlotsByItem(ulong itemSrl)
	{
		return null;
	}
}
