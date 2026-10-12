using System;
using System.Collections.Generic;
using LitJson;

public class UserArenaScenarios : ITransmittable, iTransmitContainer
{
	private Dictionary<int, UserArenaScenario> m_id2Arena;

	private List<int> m_ids;

	public Action deserializeCallback;

	public Dictionary<int, UserArenaScenario> id2Arena
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
	public UserArenaScenario get_Item(int id)
	{
		return null;
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
