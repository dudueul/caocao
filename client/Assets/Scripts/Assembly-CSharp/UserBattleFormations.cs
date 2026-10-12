using System;
using System.Collections.Generic;
using LitJson;

public class UserBattleFormations : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserBattleFormation> m_srl2Formations;

	private List<ulong> m_srls;

	public Dictionary<ulong, UserBattleFormation> srl2Item
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
	public UserBattleFormation get_Item(ulong srl)
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

	public bool Contains(string name)
	{
		return false;
	}

	public ulong GetSrl(string name)
	{
		return 0uL;
	}

	public int GetLevel(string name)
	{
		return 0;
	}
}
