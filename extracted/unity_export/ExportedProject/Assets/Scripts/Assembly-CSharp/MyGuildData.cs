using System;
using System.Collections;
using LitJson;
using UnityEngine;

public class MyGuildData : GuildData
{
	protected GuildApplicants m_applicants;

	private GuildMissions m_missions;

	private GuildStoreHistories m_storeHistories;

	private GuildStorage m_storage;

	private GuildPresentHistories m_presentHistories;

	private GuildPresentHistories m_storageHistories;

	private GuildHistories m_histories;

	private GuildSupportLogs m_supportLogs;

	public Action deserializeMeCallback;

	public GuildApplicants applicants
	{
		get
		{
			return null;
		}
	}

	public GuildMissions missions
	{
		get
		{
			return null;
		}
	}

	public GuildStoreHistories storeHistories
	{
		get
		{
			return null;
		}
	}

	public GuildStorage storage
	{
		get
		{
			return null;
		}
	}

	public GuildPresentHistories presentHistories
	{
		get
		{
			return null;
		}
	}

	public GuildPresentHistories storageHistories
	{
		get
		{
			return null;
		}
	}

	public GuildHistories histories
	{
		get
		{
			return null;
		}
	}

	public GuildSupportLogs supportLogs
	{
		get
		{
			return null;
		}
	}

	public static MyGuildData instance
	{
		get
		{
			return null;
		}
	}

	public static bool hasInstance
	{
		get
		{
			return false;
		}
	}

	public GuildMember myInfo { get; protected set; }

	public bool canSucceedToMaster
	{
		get
		{
			return false;
		}
	}

	private Coroutine StartCoroutine(IEnumerator routine)
	{
		return null;
	}

	[AppSealingSecurityMethod]
	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	private void DeserializeMe(JsonData jsonData)
	{
	}

	public override void Clear()
	{
	}

	public void GetGuildStorage(Action success, Action fail)
	{
	}

	public GuildSiegeWarSupportDeck GetMySupportDeck()
	{
		return null;
	}

	public GuildSiegeWarAttackDeck GetMySiegeWarAttackDeck()
	{
		return null;
	}

	public void GetMyDonateInfo(Action success, Action fail)
	{
	}

	public void DonateGoods(int gold, int silver, Action success, Action fail)
	{
	}

	public int GetOwnedGoods(EnumData.GoodsType goodsType)
	{
		return 0;
	}

	public bool CanUseGoods(EnumData.GoodsType goodsType, int amount)
	{
		return false;
	}
}
