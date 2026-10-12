using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class NewThousandPassive : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_PassiveId1;

	[SerializeField]
	private ObscuredInt m_value1;

	[SerializeField]
	private ObscuredInt m_Type1;

	[SerializeField]
	private ObscuredInt m_PassiveId2;

	[SerializeField]
	private ObscuredInt m_value2;

	[SerializeField]
	private ObscuredInt m_Type2;

	[SerializeField]
	private ObscuredInt m_Projectile;

	[SerializeField]
	private ObscuredInt m_Magical;

	[SerializeField]
	private ObscuredInt m_Melee;

	[SerializeField]
	private ObscuredInt m_getType;

	[SerializeField]
	private ObscuredString m_desc1;

	[SerializeField]
	private ObscuredString m_desc2;

	[SerializeField]
	private List<ObscuredInt> m_exception;

	[SerializeField]
	private List<ObscuredInt> m_jobType;

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

	public int PassiveId1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int value1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int Type1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int PassiveId2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int value2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int Type2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int Projectile
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int Magical
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int Melee
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int getType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string desc1
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string desc2
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> exception
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> jobType
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
