using System;
using System.Collections.Generic;
using LitJson;

public class UserUnitGroups : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserUnitGroup> m_srl2Groups;

	private List<ulong> m_srls;

	public Dictionary<ulong, UserUnitGroup> srl2Item
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
	public UserUnitGroup get_Item(ulong srl)
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
