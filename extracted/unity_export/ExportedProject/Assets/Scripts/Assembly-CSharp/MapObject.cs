using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class MapObject : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_group;

	[SerializeField]
	private ObscuredString m_prefab;

	[SerializeField]
	private ObscuredString m_sprite;

	[SerializeField]
	private ObscuredString m_xml;

	[SerializeField]
	private ObscuredString m_alpha;

	[SerializeField]
	private ObscuredString m_marker;

	[SerializeField]
	private ObscuredInt m_dyeing;

	[SerializeField]
	private ObscuredString m_SE;

	[SerializeField]
	private ObscuredInt m_unit;

	[SerializeField]
	private ObscuredInt m_isFloor;

	[SerializeField]
	private ObscuredInt m_row;

	[SerializeField]
	private ObscuredInt m_col;

	[SerializeField]
	private ObscuredInt m_isCastle;

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

	public string group
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string prefab
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string sprite
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string xml
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string alpha
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string marker
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int dyeing
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string SE
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int unit
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isFloor
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int row
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int col
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isCastle
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
