using System.Collections.Generic;
using LitJson;

public class BingoEventUsers
{
	private Dictionary<ulong, BingoEventUser> m_id2BingoEventUsers;

	private List<ulong> m_ids;

	public Dictionary<ulong, BingoEventUser> id2BingoEventUsers
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
	public BingoEventUser get_Item(ulong id)
	{
		return null;
	}

	public void Deserialize(JsonData jsonData)
	{
	}
}
