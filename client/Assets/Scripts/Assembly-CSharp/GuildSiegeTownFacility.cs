using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildSiegeTownFacility : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_facilityType;

	[SerializeField]
	private ObscuredInt m_slotType;

	[SerializeField]
	private ObscuredInt m_linkedFacility;

	[SerializeField]
	private ObscuredString m_thumbnail;

	[SerializeField]
	private ObscuredInt m_canReinforce;

	[SerializeField]
	private ObscuredInt m_captureEffect;

	[SerializeField]
	private ObscuredInt m_battleMapId;

	[SerializeField]
	private ObscuredInt m_needGuildLv;

	[SerializeField]
	private ObscuredInt m_costType;

	[SerializeField]
	private ObscuredInt m_costValue;

	[SerializeField]
	private ObscuredInt m_type0;

	[SerializeField]
	private ObscuredInt m_reward0;

	[SerializeField]
	private ObscuredInt m_type1;

	[SerializeField]
	private ObscuredInt m_reward1;

	[SerializeField]
	private ObscuredString m_desc;

	[SerializeField]
	private ObscuredString m_caputreDesc;

	[SerializeField]
	private List<ObscuredInt> m_byLv;

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

	public int facilityType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int slotType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int linkedFacility
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string thumbnail
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int canReinforce
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int captureEffect
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int battleMapId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int needGuildLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int costType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int costValue
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

	public int type1
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

	public string caputreDesc
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> byLv
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
