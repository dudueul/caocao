using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserTimeBox : ITransmittable
{
	private ObscuredULong srl;

	private ObscuredULong userId;

	private ObscuredInt slotIndex;

	private ObscuredInt boxId;

	private ObscuredInt seasonId;

	private ObscuredInt boxTier;

	private JsonData fixedRewards;

	private JsonData decideList;

	private JsonData decide;

	private EnumData.TimeBoxStatus boxStatus;

	private DateTime unlockDate;

	private DateTime expireDate;

	private List<RewardInfo> m_timBoxFixedRewards;

	private List<TimeBoxDecide> m_timeBoxDecideList;

	private List<TimeBoxDecide> m_timeBoxDecided;

	public int id
	{
		get
		{
			return 0;
		}
	}

	public int index
	{
		get
		{
			return 0;
		}
	}

	public int m_seasonId
	{
		get
		{
			return 0;
		}
	}

	public int m_boxTier
	{
		get
		{
			return 0;
		}
	}

	public EnumData.TimeBoxStatus status
	{
		get
		{
			return EnumData.TimeBoxStatus.Get;
		}
	}

	public DateTime unlockDateTime
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime expireDateTime
	{
		get
		{
			return default(DateTime);
		}
	}

	public JsonData fixedRewardsJson
	{
		get
		{
			return null;
		}
	}

	public List<RewardInfo> timBoxFixedRewards
	{
		get
		{
			return null;
		}
	}

	public List<TimeBoxDecide> timeBoxDecideList
	{
		get
		{
			return null;
		}
	}

	public List<TimeBoxDecide> timeBoxDecided
	{
		get
		{
			return null;
		}
	}

	public TimeBox timeBoxPd
	{
		get
		{
			return null;
		}
	}

	public TimeSpan remainTime2Open
	{
		get
		{
			return default(TimeSpan);
		}
	}

	public bool canOpenNow
	{
		get
		{
			return false;
		}
	}

	public bool isExpired
	{
		get
		{
			return false;
		}
	}

	public TimeSpan remainTime2Expire
	{
		get
		{
			return default(TimeSpan);
		}
	}

	public UserTimeBox()
	{
	}

	public UserTimeBox(int slotIndex, int seasonId)
	{
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public int GetCostOfImmediateOpen()
	{
		return 0;
	}

	private List<TimeBoxDecide> ParseDecideListJson(JsonData decideJson)
	{
		return null;
	}
}
