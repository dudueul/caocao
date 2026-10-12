using System.Collections.Generic;
using LitJson;

public class CustomEventUsers
{
	private Dictionary<ulong, CustomEventUser> m_id2CustomEventUsers;

	private List<ulong> m_ids;

	public Dictionary<ulong, CustomEventUser> id2EventUsers
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
	public CustomEventUser get_Item(ulong id)
	{
		return null;
	}

	public void Deserialize(JsonData jsonData)
	{
	}

	public void UpdateRewardInfo(ulong eventUnitId)
	{
	}
}
