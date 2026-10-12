using System;
using System.Collections.Generic;
using LitJson;

public class UserArenaTreasures : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserArenaTreasure> m_srl2ArenaTreasure;

	private List<ulong> m_srls;

	private Dictionary<ulong, ulong> srl2ItemSrl;

	private Dictionary<ulong, ulong> itemSrl2Srl;

	public Action deserializeCallback;

	private List<UserArenaTreasure> upsertedList;

	public Dictionary<ulong, UserArenaTreasure> srl2ArenaTreasure
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
	public UserArenaTreasure get_Item(ulong srl)
	{
		return null;
	}

	public void ClearSrls()
	{
	}

	public ulong Srl2ItemSrl(ulong srl)
	{
		return 0uL;
	}

	public ulong ItemSrl2Srl(ulong itemSrl)
	{
		return 0uL;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
