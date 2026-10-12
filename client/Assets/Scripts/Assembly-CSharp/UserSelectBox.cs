using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserSelectBox : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredInt m_cashShopId;

	private ObscuredInt m_selectBoxId;

	private ObscuredInt m_purchaseCount;

	private ObscuredInt m_refreshCount;

	private JsonData m_rewards;

	private EnumData.SelectBoxStatus m_boxStatus;

	private DateTime m_refreshLastDate;

	private List<RewardInfo> m_selectBoxRewards;

	public int cashShopId
	{
		get
		{
			return 0;
		}
	}

	public int selectBoxId
	{
		get
		{
			return 0;
		}
	}

	public EnumData.SelectBoxStatus status
	{
		get
		{
			return EnumData.SelectBoxStatus.Refresh;
		}
	}

	public int purchaseCount
	{
		get
		{
			return 0;
		}
	}

	public int refreshCount
	{
		get
		{
			return 0;
		}
	}

	public int refreshCost
	{
		get
		{
			return 0;
		}
	}

	public int maxPurchaseCount
	{
		get
		{
			return 0;
		}
	}

	public int maxRefreshCount
	{
		get
		{
			return 0;
		}
	}

	public DateTime refreshLastDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public List<RewardInfo> selectBoxRewards
	{
		get
		{
			return null;
		}
	}

	public SelectBox selectBoxPd
	{
		get
		{
			return null;
		}
	}

	public CashShop cashShopPd
	{
		get
		{
			return null;
		}
	}

	public UserSelectBox()
	{
	}

	public UserSelectBox(int cashShopId, int selectBoxId, EnumData.SelectBoxStatus boxStatus)
	{
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	private List<RewardInfo> ParseRewardsJson(JsonData rewardsJson)
	{
		return null;
	}
}
