using System.Collections.Generic;
using LitJson;

public class CustomEventRewards
{
	private Dictionary<ulong, CustomEventReward> m_id2CustomEventRewards;

	private List<ulong> m_ids;

	public Dictionary<ulong, CustomEventReward> id2EventRewards
	{
		get
		{
			return null;
		}
	}

	public List<ulong> ids
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public CustomEventReward get_Item(ulong id)
	{
		return null;
	}

	public void Deserialize(JsonData jsonData)
	{
	}

	public List<CustomEventReward> GetRewards(ulong eventUnitId)
	{
		return null;
	}
}
