using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class CompetitionMap : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_displayMode;

	[SerializeField]
	private ObscuredInt m_battleMapId;

	[SerializeField]
	private ObscuredString m_battleMapImage;

	[SerializeField]
	private ObscuredInt m_battleMode;

	[SerializeField]
	private List<ObscuredInt> m_tileId;

	[SerializeField]
	private ObscuredString m_description;

	[SerializeField]
	private List<ObscuredInt> m_jobtype;

	[SerializeField]
	private ObscuredInt m_axisX;

	[SerializeField]
	private ObscuredInt m_axisY;

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

	public string displayMode
	{
		get
		{
			return null;
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

	public string battleMapImage
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int battleMode
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> tileId
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string description
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> jobtype
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int axisX
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int axisY
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
