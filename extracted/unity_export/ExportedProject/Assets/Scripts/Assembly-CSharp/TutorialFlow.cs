using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class TutorialFlow : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_type;

	[SerializeField]
	private ObscuredString m_EventScript;

	[SerializeField]
	private ObscuredInt m_battleMap;

	[SerializeField]
	private ObscuredInt m_nextId;

	[SerializeField]
	private ObscuredString m_placeName;

	[SerializeField]
	private ObscuredInt m_year;

	[SerializeField]
	private ObscuredString m_eraName;

	[SerializeField]
	private ObscuredInt m_eraYear;

	[SerializeField]
	private ObscuredInt m_mission1;

	[SerializeField]
	private ObscuredInt m_mission2;

	[SerializeField]
	private ObscuredInt m_mission3;

	[SerializeField]
	private ObscuredInt m_type1;

	[SerializeField]
	private ObscuredInt m_reward1;

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

	public int type
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string EventScript
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int battleMap
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int nextId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string placeName
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int year
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string eraName
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int eraYear
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int mission1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int mission2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int mission3
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
}
