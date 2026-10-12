using System;
using System.Collections.Generic;
using LitJson;

public class UserLinkedGoodsNodes : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserLinkedGoodsNode> m_srl2LinkedGoodseNodes;

	private List<ulong> m_srls;

	private Dictionary<int, List<UserLinkedGoodsNode>> linkedGoodsId2nodes;

	public Dictionary<ulong, UserLinkedGoodsNode> srl2LinkedGoodseNodes
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserLinkedGoodsNode get_Item(ulong srl)
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

	public List<UserLinkedGoodsNode> GetNodesByLinkedGoodsId(int linkedGoodsId)
	{
		return null;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
