using System.Collections.Generic;
using LitJson;

public class CustomEventIngames
{
	private Dictionary<ulong, CustomEventIngame> m_id2CustomEventIngames;

	private List<ulong> m_ids;

	public Dictionary<ulong, CustomEventIngame> id2EventIngames
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
	public CustomEventIngame get_Item(ulong id)
	{
		return null;
	}

	public void Deserialize(JsonData jsonData)
	{
	}

	public List<string> GetImageFilePathList()
	{
		return null;
	}
}
