using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class SpecialInstance : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_image;

	[SerializeField]
	private ObscuredInt m_entranceType;

	[SerializeField]
	private ObscuredString m_entranceDesc;

	[SerializeField]
	private ObscuredString m_typeDesc;

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

	public string image
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int entranceType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string entranceDesc
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string typeDesc
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
