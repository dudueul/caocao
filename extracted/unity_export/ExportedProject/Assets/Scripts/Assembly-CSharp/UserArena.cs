using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

[Serializable]
public class UserArena : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredInt m_stageGroup;

	private ObscuredInt m_state;

	private ObscuredInt m_scenario;

	private ObscuredInt m_curLevel;

	private ObscuredInt m_coin;

	private ObscuredInt m_openShopLevel;

	private ObscuredInt m_shopItemCnt;

	private ObscuredInt m_shopRefreshCnt;

	private ObscuredInt m_receivedReward;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public ulong uid
	{
		get
		{
			return 0uL;
		}
	}

	public int stageGroup
	{
		get
		{
			return 0;
		}
	}

	public EnumData.ArenaState state
	{
		get
		{
			return EnumData.ArenaState.Open;
		}
	}

	public int scenario
	{
		get
		{
			return 0;
		}
	}

	public int curLevel
	{
		get
		{
			return 0;
		}
	}

	public int coin
	{
		get
		{
			return 0;
		}
	}

	public int openShopLevel
	{
		get
		{
			return 0;
		}
	}

	public int shopItemCnt
	{
		get
		{
			return 0;
		}
	}

	public int shopRefreshCnt
	{
		get
		{
			return 0;
		}
	}

	public bool receivedReward
	{
		get
		{
			return false;
		}
	}

	public int clearLevel
	{
		get
		{
			return 0;
		}
	}

	public Action deserializeCallback { get; set; }

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public bool IsOpenShop()
	{
		return false;
	}
}
