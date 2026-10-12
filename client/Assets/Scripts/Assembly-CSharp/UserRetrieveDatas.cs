using System;
using System.Collections.Generic;
using LitJson;

public class UserRetrieveDatas : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserRetrieveData> m_srl2RetrieveDatas;

	private List<ulong> m_srls;

	private string m_baseAlertMsg;

	private string m_alertMsg;

	private Dictionary<int, int> m_allGoodsRetrieveData;

	private Dictionary<int, int> m_allItemsRetrieveData;

	public Dictionary<ulong, UserRetrieveData> srl2RetrieveDatas
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserRetrieveData get_Item(ulong srl)
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

	public string alertMsg
	{
		get
		{
			return null;
		}
	}

	public Action deserializeCallback { get; set; }

	public void alertMsgClear()
	{
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public bool HaveRetrieveItem(int id)
	{
		return false;
	}

	private string GetItemMsg(int itemId, int amount, int delta)
	{
		return null;
	}

	private string GetGoodsMsg(EnumData.GoodsType goodsType, int amount, int delta)
	{
		return null;
	}

	private string GetRunesMsg(int runeId, int passive, int amount, int delta)
	{
		return null;
	}

	private void RefreshRetrieveData()
	{
	}
}
