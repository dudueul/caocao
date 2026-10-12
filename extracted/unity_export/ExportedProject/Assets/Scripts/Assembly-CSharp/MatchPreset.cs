using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class MatchPreset : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_town;

	[SerializeField]
	private ObscuredInt m_saveflagId;

	[SerializeField]
	private ObscuredInt m_value;

	[SerializeField]
	private ObscuredInt m_castleLv;

	[SerializeField]
	private ObscuredString m_nationName;

	[SerializeField]
	private ObscuredInt m_lordUnitId;

	[SerializeField]
	private ObscuredInt m_mayorUnitId;

	[SerializeField]
	private List<ObscuredInt> m_unitId;

	[SerializeField]
	private List<ObscuredInt> m_unitLv;

	[SerializeField]
	private List<ObscuredInt> m_slot;

	[SerializeField]
	private List<ObscuredInt> m_soldierId;

	[SerializeField]
	private List<ObscuredInt> m_soldierLv;

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

	public int town
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int saveflagId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int value
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int castleLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string nationName
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int lordUnitId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int mayorUnitId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> unitId
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> unitLv
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> slot
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> soldierId
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> soldierLv
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
