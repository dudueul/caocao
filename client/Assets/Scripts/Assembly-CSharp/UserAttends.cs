using System;
using System.Collections.Generic;
using LitJson;

public class UserAttends : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserAttend> m_srl2Attends;

	private List<ulong> m_srls;

	public Dictionary<ulong, UserAttend> srl2Attends
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserAttend get_Item(ulong srl)
	{
		return null;
	}

	[DoNotJsonfy]
	public List<ulong> srls
	{
		get
		{
			return null;
		}
	}

	public Action deserializeCallback { get; set; }

	public DateTime lastRegDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public int lastSeq
	{
		get
		{
			return 0;
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public UserAttend GetUserAttend(int seq)
	{
		return null;
	}
}
