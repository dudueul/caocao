using System.Collections.Generic;
using LitJson;

public class UserDailyGiftHistorys : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, DailyGiftHistory> m_dailyGiftHistoryDic;

	private List<ulong> m_srls;

	public Dictionary<ulong, DailyGiftHistory> srl2Historys
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public DailyGiftHistory get_Item(ulong srl)
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

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public bool CheckState(int dailyGiftId, EnumData.DailyGiftState state)
	{
		return false;
	}

	public bool Contain(int dailyGiftId)
	{
		return false;
	}
}
