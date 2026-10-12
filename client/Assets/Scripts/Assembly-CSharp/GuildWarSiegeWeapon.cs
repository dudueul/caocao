using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildWarSiegeWeapon : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_temporaryPrefab;

	[SerializeField]
	private ObscuredInt m_corpsJobId;

	[SerializeField]
	private ObscuredInt m_temporaryPassiveListId;

	[SerializeField]
	private ObscuredInt m_productPassiveListId;

	[SerializeField]
	private ObscuredString m_atlas;

	[SerializeField]
	private ObscuredString m_icon;

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

	public string temporaryPrefab
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int corpsJobId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int temporaryPassiveListId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int productPassiveListId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string atlas
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string icon
	{
		get
		{
			return null;
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
