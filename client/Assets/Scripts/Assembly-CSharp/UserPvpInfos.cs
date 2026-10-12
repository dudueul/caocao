using System.Collections.Generic;
using LitJson;

public class UserPvpInfos : ITransmittable, iTransmitContainer
{
	private SortedDictionary<string, UserPvpInfo> m_PvpInfos;

	public SortedDictionary<string, UserPvpInfo> pvpInfos
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserPvpInfo get_Item(string limitDay)
	{
		return null;
	}

	public UserPvpInfo getLatest
	{
		get
		{
			return null;
		}
	}

	public UserPvpInfo getPrev
	{
		get
		{
			return null;
		}
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void AddPvpInfo(UserPvpInfo seasonPvpInfo)
	{
	}
}
