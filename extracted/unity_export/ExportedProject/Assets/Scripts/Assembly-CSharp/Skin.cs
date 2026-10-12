using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Skin : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_face;

	[SerializeField]
	private ObscuredString m_battlePrefab;

	[SerializeField]
	private ObscuredInt m_unitId;

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

	public string battlePrefab
	{
		get
		{
			return null;
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
}
