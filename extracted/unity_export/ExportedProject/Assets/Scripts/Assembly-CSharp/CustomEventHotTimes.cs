using System;
using System.Collections.Generic;
using LitJson;

public class CustomEventHotTimes
{
	private Dictionary<ulong, CustomEventHotTime> m_id2CustomEventHotTimes;

	private List<ulong> m_ids;

	public Action deserializeCallback;

	public Dictionary<ulong, CustomEventHotTime> id2EventHotTimes
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
	public CustomEventHotTime get_Item(ulong id)
	{
		return null;
	}

	public void Deserialize(JsonData jsonData)
	{
	}

	public CustomEventHotTime GetActivatedByContentType(EnumData.BattleMode battleMode)
	{
		return null;
	}
}
