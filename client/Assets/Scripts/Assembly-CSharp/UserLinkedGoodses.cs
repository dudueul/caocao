using System;
using System.Collections.Generic;
using LitJson;

public class UserLinkedGoodses : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserLinkedGoods> m_srl2LinkedGoodses;

	private List<ulong> m_srls;

	public Dictionary<ulong, UserLinkedGoods> srl2LinkedGoodses
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserLinkedGoods get_Item(ulong srl)
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

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public UserLinkedGoods GetUserLinkedGoods(int pdId)
	{
		return null;
	}

	public bool CheckPrecedingCondition(int linkedGoodsId, int productId)
	{
		return false;
	}
}
