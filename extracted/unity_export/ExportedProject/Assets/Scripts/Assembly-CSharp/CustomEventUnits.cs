using System.Collections.Generic;
using LitJson;

public class CustomEventUnits
{
	private Dictionary<ulong, CustomEventUnit> m_id2CustomEventUnits;

	private List<ulong> m_ids;

	public Dictionary<ulong, CustomEventUnit> id2EventUnits
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
	public CustomEventUnit get_Item(ulong id)
	{
		return null;
	}

	public void Deserialize(JsonData jsonData)
	{
	}

	public List<CustomEventUnit> GetEventUnits(ulong ingameId)
	{
		return null;
	}
}
