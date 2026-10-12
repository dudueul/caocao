using System;
using System.Collections.Generic;
using LitJson;

public class UserFollowUsers : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserFollowUser> m_uid2FollowUsers;

	private List<ulong> m_uids;

	private Comparison<ulong> sortFunc;

	[DoNotJsonfy]
	public List<ulong> uids
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserFollowUser get_Item(ulong uid)
	{
		return null;
	}

	public int Count
	{
		get
		{
			return 0;
		}
	}

	public Action deserializeCallback { get; set; }

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
