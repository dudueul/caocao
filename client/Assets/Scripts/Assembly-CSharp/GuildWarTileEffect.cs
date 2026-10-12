using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildWarTileEffect : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_tileEffectPrefab;

	[SerializeField]
	private List<ObscuredInt> m_guildWarPassiveList;

	[SerializeField]
	private ObscuredInt m_tileEffectDmgType;

	[SerializeField]
	private ObscuredInt m_tileEffectDmgValue;

	[SerializeField]
	private ObscuredInt m_tileEffectMinDmgRate;

	[SerializeField]
	private ObscuredInt m_expireTime;

	[SerializeField]
	private ObscuredInt m_isIgnited;

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

	public string tileEffectPrefab
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> guildWarPassiveList
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int tileEffectDmgType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int tileEffectDmgValue
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int tileEffectMinDmgRate
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int expireTime
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isIgnited
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
