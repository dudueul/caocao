using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Hero : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_faceId;

	[SerializeField]
	private ObscuredInt m_charType;

	[SerializeField]
	private ObscuredInt m_state;

	[SerializeField]
	private ObscuredInt m_kingdom;

	[SerializeField]
	private ObscuredInt m_birth;

	[SerializeField]
	private ObscuredInt m_death;

	[SerializeField]
	private ObscuredString m_criticalMsg;

	[SerializeField]
	private ObscuredString m_defeatMsg;

	[SerializeField]
	private ObscuredInt m_gender;

	[SerializeField]
	private ObscuredInt m_prefab;

	[SerializeField]
	private ObscuredInt m_voice;

	[SerializeField]
	private ObscuredInt m_modmain;

	[SerializeField]
	private ObscuredInt m_modsub;

	[SerializeField]
	private ObscuredInt m_passive;

	[SerializeField]
	private ObscuredInt m_relation;

	[SerializeField]
	private ObscuredInt m_canDesc;

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

	public int faceId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int charType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int state
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int kingdom
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int birth
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int death
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string criticalMsg
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string defeatMsg
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int gender
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int prefab
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int voice
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int modmain
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int modsub
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int passive
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int relation
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canDesc
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
