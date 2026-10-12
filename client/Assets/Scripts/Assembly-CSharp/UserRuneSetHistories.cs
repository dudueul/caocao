using System;
using System.Collections.Generic;
using LitJson;

public class UserRuneSetHistories : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserRuneSetHistory> m_srl2RuneSetHistories;

	private List<ulong> m_srls;

	public Dictionary<ulong, UserRuneSetHistory> srl2Runes
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserRuneSetHistory get_Item(ulong srl)
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

	public bool HaveRuneSetHistory(int runeSetId)
	{
		return false;
	}
}
