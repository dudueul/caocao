using System;
using System.Collections.Generic;
using LitJson;

public class UserDailyCounts : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserDailyCount> m_srl2Counts;

	private List<ulong> m_srls;

	public Action m_deserializeCallback;

	public Dictionary<ulong, UserDailyCount> srl2Counts
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserDailyCount get_Item(ulong srl)
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

	public void RegistTimer()
	{
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	private UserDailyCount GetLastOne()
	{
		return null;
	}

	public int GetFreeFastForwardCount()
	{
		return 0;
	}

	public int GetFreeUndoCount()
	{
		return 0;
	}

	public int GetFreeEnhanceCount()
	{
		return 0;
	}

	public int GetFreeChangePassiveCount()
	{
		return 0;
	}

	public bool IsFreeFastForwardAvailable()
	{
		return false;
	}

	public bool IsFirstLoggedInToday()
	{
		return false;
	}

	public int GetCurrentRefreshCount(EnumData.ShopMode shopMode)
	{
		return 0;
	}

	public int GetFreeAutoClearInstance()
	{
		return 0;
	}

	public int GetMaxAutoClearInstanceCount()
	{
		return 0;
	}

	public int GetCanFreeClearInstanceCount()
	{
		return 0;
	}

	public void TimerFuncDailyCheck()
	{
	}

	public int GetRemainedCommonMissionResetCount()
	{
		return 0;
	}

	public int GetConsequenceAttendanceSeqNum()
	{
		return 0;
	}
}
