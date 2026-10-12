using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class BattleFieldNpcUnit : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_type;

	[SerializeField]
	private ObscuredInt m_unit_id;

	[SerializeField]
	private ObscuredInt m_weapon_tr;

	[SerializeField]
	private ObscuredInt m_armor_tr;

	[SerializeField]
	private ObscuredInt m_kit_tr;

	[SerializeField]
	private ObscuredInt m_passive1;

	[SerializeField]
	private ObscuredInt m_passive2;

	[SerializeField]
	private ObscuredInt m_passive3;

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

	public int unit_id
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int weapon_tr
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int armor_tr
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int kit_tr
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int passive1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int passive2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int passive3
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
