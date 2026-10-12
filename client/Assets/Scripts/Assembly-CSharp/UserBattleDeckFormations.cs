using System;
using System.Collections.Generic;
using LitJson;

public class UserBattleDeckFormations : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserBattleDeckFormation> m_srl2Formations;

	private List<ulong> m_srls;

	public Dictionary<ulong, UserBattleDeckFormation> srl2Item
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
	public UserBattleDeckFormation get_Item(ulong srl)
	{
		return null;
	}

	public Action deserializeCallback { get; set; }

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
