using System;
using System.Collections.Generic;
using LitJson;

public class UserRuneItems : ITransmittable, IUpsertedMarkable
{
	private Dictionary<ulong, UserRuneItem> m_srl2RuneItems;

	private List<ulong> m_srls;

	private int totalAmount;

	private Dictionary<int, int> id2Amount;

	public bool trackGetItem;

	public Dictionary<ulong, UserRuneItem> srl2RuneItems
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserRuneItem get_Item(ulong srl)
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

	[DoNotJsonfy]
	public int TotalAmount
	{
		get
		{
			return 0;
		}
	}

	public Action deserializeCallback { get; set; }

	[DoNotJsonfy]
	public bool isAdded
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	[DoNotJsonfy]
	public bool isUpdated
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool isInvenMaxAmount(int addAmount = 0)
	{
		return false;
	}

	public bool IsExistRuneId(int id)
	{
		return false;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public UserRuneItem GetUserRuneItem(int id, int passiveId)
	{
		return null;
	}
}
