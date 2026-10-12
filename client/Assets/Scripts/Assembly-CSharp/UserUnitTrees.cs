using System;
using System.Collections.Generic;
using LitJson;

public class UserUnitTrees : ITransmittable, iTransmitContainer
{
	private Dictionary<int, UserUnitTree> m_id2UnitTree;

	private List<int> m_ids;

	public Dictionary<int, UserUnitTree> id2UnitTree
	{
		get
		{
			return null;
		}
	}

	[DoNotJsonfy]
	public List<int> ids
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserUnitTree get_Item(int srl)
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
