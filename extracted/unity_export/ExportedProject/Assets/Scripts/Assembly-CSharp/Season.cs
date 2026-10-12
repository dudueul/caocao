using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Season : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_round;

	[SerializeField]
	private ObscuredString m_seasonTexture;

	[SerializeField]
	private ObscuredInt m_isRegular;

	[SerializeField]
	private List<ObscuredInt> m_map;

	[SerializeField]
	private List<ObscuredInt> m_userSpell;

	[SerializeField]
	private List<ObscuredInt> m_fair;

	[SerializeField]
	private ObscuredInt m_cycleTerm;

	[SerializeField]
	private ObscuredString m_startDate;

	[SerializeField]
	private ObscuredString m_endDate;

	[SerializeField]
	private ObscuredString m_rotationStartDate;

	[SerializeField]
	private ObscuredInt m_country;

	[SerializeField]
	private List<ObscuredInt> m_itemId;

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

	public string round
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string seasonTexture
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int isRegular
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> map
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> userSpell
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> fair
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int cycleTerm
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string startDate
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string endDate
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string rotationStartDate
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int country
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> itemId
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
