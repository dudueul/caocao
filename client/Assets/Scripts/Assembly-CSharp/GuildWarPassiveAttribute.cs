using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildWarPassiveAttribute : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_passiveName;

	[SerializeField]
	private ObscuredInt m_passiveId;

	[SerializeField]
	private ObscuredInt m_accumulate;

	[SerializeField]
	private ObscuredInt m_orderNumber;

	[SerializeField]
	private ObscuredInt m_valueDisplayType;

	[SerializeField]
	private ObscuredString m_atlas;

	[SerializeField]
	private ObscuredString m_icon;

	[SerializeField]
	private ObscuredString m_desc;

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

	public string passiveName
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int passiveId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int accumulate
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int orderNumber
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int valueDisplayType
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
