using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Soldier : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_unitId;

	[SerializeField]
	private ObscuredInt m_rank;

	[SerializeField]
	private ObscuredInt m_vipLv;

	[SerializeField]
	private ObscuredInt m_techId;

	[SerializeField]
	private ObscuredInt m_techLv;

	[SerializeField]
	private ObscuredInt m_getType;

	[SerializeField]
	private ObscuredInt m_getValue;

	[SerializeField]
	private ObscuredInt m_upType;

	[SerializeField]
	private ObscuredInt m_upValue;

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

	public int unitId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rank
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int vipLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int techId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int techLv
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

	public int getValue
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int upType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int upValue
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
