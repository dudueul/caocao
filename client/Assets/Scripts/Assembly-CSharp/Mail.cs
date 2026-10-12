using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Mail : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_isNotice;

	[SerializeField]
	private ObscuredInt m_face;

	[SerializeField]
	private ObscuredString m_sender;

	[SerializeField]
	private ObscuredString m_title;

	[SerializeField]
	private ObscuredString m_desc;

	[SerializeField]
	private ObscuredInt m_sendTime;

	[SerializeField]
	private ObscuredInt m_expireTime;

	[SerializeField]
	private ObscuredInt m_type0;

	[SerializeField]
	private ObscuredInt m_reward0;

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

	public int isNotice
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int face
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string sender
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string title
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

	public int sendTime
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

	public int type0
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int reward0
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
