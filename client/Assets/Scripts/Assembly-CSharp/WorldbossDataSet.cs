using System;
using System.Collections.Generic;
using LitJson;

public class WorldbossDataSet
{
	private int m_rankIdx;

	private float m_rankPct;

	private Dictionary<string, WorldBossServerSeason> m_seasonDic;

	private Dictionary<string, WorldBossContribution> m_contributionDic;

	private Dictionary<string, WorldBossPhaseInfo> m_phaseInfoDic;

	private Dictionary<string, WorldBossInstance> m_instanceDic;

	private Dictionary<string, WorldBossSeasonInfo> m_seasonInfoDic;

	private Dictionary<ulong, WorldBossShopHistory> m_shopHistoryDic;

	private float m_lastRequestInfoTime;

	public static WorldbossDataSet instance
	{
		get
		{
			return null;
		}
	}

	public Action deserializeCallback { get; set; }

	public string yyyymmdd
	{
		get
		{
			return null;
		}
	}

	public WorldBossServerSeason Season
	{
		get
		{
			return null;
		}
	}

	public int curPhase
	{
		get
		{
			return 0;
		}
	}

	public WorldBossInstance curInstance
	{
		get
		{
			return null;
		}
	}

	public WorldBossContribution curContribution
	{
		get
		{
			return null;
		}
	}

	public WorldBossPhaseInfo curPhaseInfo
	{
		get
		{
			return null;
		}
	}

	public WorldBossSeasonInfo curSeasonInfo
	{
		get
		{
			return null;
		}
	}

	public WorldBossTier curTier
	{
		get
		{
			return null;
		}
	}

	public ulong myToken
	{
		get
		{
			return 0uL;
		}
	}

	public bool isSeasonOff
	{
		get
		{
			return false;
		}
	}

	public void Sync(Action callback)
	{
	}

	public void RequestWorldBossInfo(Action callback, bool showIndicator = true)
	{
	}

	public void RequestSeasonResult(Action callback)
	{
	}

	public void Deserialize(JsonData response)
	{
	}

	private string seasonKeySelector(JsonData jsonData)
	{
		return null;
	}

	private string createPhaseKey(string yyyymmdd, int phase)
	{
		return null;
	}

	private string phaseKeySelector(JsonData jsonData)
	{
		return null;
	}

	private bool DeserializeSeasons(JsonData jsonData)
	{
		return false;
	}

	private bool DeserializeContributions(JsonData jsonData)
	{
		return false;
	}

	private bool DeserializeInstances(JsonData jsonData)
	{
		return false;
	}

	private bool DeserializePhaseInfos(JsonData jsonData)
	{
		return false;
	}

	private bool DeserializeSeasonInfos(JsonData jsonData)
	{
		return false;
	}

	private bool DeserializeShopHistorys(JsonData jsonData)
	{
		return false;
	}

	public SortedList<int, WorldBossPhaseInfo> GetPhaseInfoList()
	{
		return null;
	}

	public WorldBossPhaseInfo GetPhaseInfo(string yyyymmdd, int phase)
	{
		return null;
	}

	public SortedList<int, WorldBossInstance> GetInstanceList()
	{
		return null;
	}

	public WorldBossInstance GetInstance(string yyyymmdd, int phase)
	{
		return null;
	}

	public Dictionary<int, WorldBossShopHistory> GetShopHistoryByShopId()
	{
		return null;
	}

	public WorldBossShopHistory GetShopHistory(int shopId)
	{
		return null;
	}
}
