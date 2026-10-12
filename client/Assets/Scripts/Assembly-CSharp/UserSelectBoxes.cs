using System;
using System.Collections.Generic;
using LitJson;

public class UserSelectBoxes : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserSelectBox> m_srl2Boxes;

	private Dictionary<int, UserSelectBox> m_index2Boxes;

	private List<ulong> m_srls;

	[DoNotJsonfy]
	public List<UserSelectBox> boxes
	{
		get
		{
			return null;
		}
	}

	public Action deserializeCallback { get; set; }

	public UserSelectBox GetBoxByCashShopId(int cashShopId)
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
