using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Guild : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_level;

	[SerializeField]
	private ObscuredInt m_needExp;

	[SerializeField]
	private ObscuredInt m_expSum;

	[SerializeField]
	private ObscuredInt m_maxUser;

	[SerializeField]
	private ObscuredInt m_maxGuest;

	[SerializeField]
	private ObscuredInt m_guildShopGrade;

	[SerializeField]
	private ObscuredInt m_supportGrade;

	[SerializeField]
	private ObscuredString m_bgTexture;

	[SerializeField]
	private ObscuredInt m_canGuildWar;

	[SerializeField]
	private ObscuredInt m_canGuildRaid;

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

	public int level
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int needExp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int expSum
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxUser
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxGuest
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int guildShopGrade
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int supportGrade
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string bgTexture
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int canGuildWar
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canGuildRaid
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}
}
