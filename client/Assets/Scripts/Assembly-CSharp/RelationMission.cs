using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class RelationMission : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_checkType;

	[SerializeField]
	private ObscuredInt m_checkDetail;

	[SerializeField]
	private ObscuredInt m_checkCompare;

	[SerializeField]
	private ObscuredInt m_checkVal;

	[SerializeField]
	private ObscuredInt m_quick;

	[SerializeField]
	private ObscuredInt m_quickDetail;

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

	public int checkType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int checkDetail
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int checkCompare
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int checkVal
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int quick
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int quickDetail
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
