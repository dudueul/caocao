using System;
using System.Collections.Generic;
using LitJson;

public class UserArenaUnitGroups : ITransmittable
{
	private Dictionary<ulong, UserArenaUnitGroup> m_srl2UnitGroup;

	private List<ulong> m_srls;

	public Action deserializeCallback;

	public Dictionary<ulong, UserArenaUnitGroup> srl2UnitGroup
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
	public UserArenaUnitGroup get_Item(ulong srl)
	{
		return null;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public UserArenaUnitGroup GetArenaUnitGroup(ulong arenaSrl)
	{
		return null;
	}
}
