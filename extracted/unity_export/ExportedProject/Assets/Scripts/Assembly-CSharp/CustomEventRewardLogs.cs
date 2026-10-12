using System.Collections.Generic;
using LitJson;

public class CustomEventRewardLogs
{
	private Dictionary<ulong, CustomEventRewardLog> m_id2EventRewardLogs;

	private List<ulong> m_ids;

	public Dictionary<ulong, CustomEventRewardLog> id2EventRewardLogs
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
	public CustomEventRewardLog get_Item(ulong id)
	{
		return null;
	}

	public void Deserialize(JsonData jsonData)
	{
	}

	public bool isRecieved(ulong srl)
	{
		return false;
	}
}
