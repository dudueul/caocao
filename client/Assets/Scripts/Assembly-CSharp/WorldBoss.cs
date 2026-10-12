using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class WorldBoss : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_bossUnitID;

	[SerializeField]
	private ObscuredInt m_seasonId;

	[SerializeField]
	private ObscuredString m_bossHP;

	[SerializeField]
	private ObscuredInt m_minReqTime;

	[SerializeField]
	private ObscuredInt m_maxReqTime;

	[SerializeField]
	private ObscuredInt m_waitingTime;

	[SerializeField]
	private ObscuredInt m_coolTime;

	[SerializeField]
	private ObscuredInt m_minActiveTime;

	[SerializeField]
	private ObscuredInt m_activeTime;

	[SerializeField]
	private ObscuredInt m_totalUncoverCount;

	[SerializeField]
	private ObscuredInt m_personUncoverCount;

	[SerializeField]
	private List<ObscuredInt> m_uncoverItemID1;

	[SerializeField]
	private List<ObscuredInt> m_uncoverItemCount1;

	[SerializeField]
	private List<ObscuredInt> m_uncoverFixedReward;

	[SerializeField]
	private List<ObscuredInt> m_uncoverRndReward;

	[SerializeField]
	private ObscuredInt m_battleMapID;

	[SerializeField]
	private ObscuredInt m_reward1;

	[SerializeField]
	private ObscuredInt m_rewardGoods;

	[SerializeField]
	private ObscuredInt m_earlyClearRewardRate;

	[SerializeField]
	private List<ObscuredInt> m_fixedBossPassive;

	[SerializeField]
	private List<ObscuredInt> m_rndBossPassiveGroup;

	[SerializeField]
	private ObscuredInt m_successRewardId;

	[SerializeField]
	private ObscuredString m_desc;

	public string name
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int id
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int bossUnitID
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int seasonId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string bossHP
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int minReqTime
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxReqTime
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int waitingTime
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int coolTime
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int minActiveTime
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int activeTime
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int totalUncoverCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int personUncoverCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> uncoverItemID1
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> uncoverItemCount1
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> uncoverFixedReward
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> uncoverRndReward
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int battleMapID
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int reward1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rewardGoods
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int earlyClearRewardRate
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> fixedBossPassive
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> rndBossPassiveGroup
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int successRewardId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string desc
	{
		get
		{
			return null;
		}
		set
		{
		}
	}
}
