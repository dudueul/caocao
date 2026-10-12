using UnityEngine;

public class UnitGroup : MonoBehaviour
{
	private MapInfo m_mapInfo;

	private bool m_bShowUnitEdit;

	private GameObject m_units;

	private GameObject m_selectedUnitObj;

	private bool m_showGrid;

	private GameObject m_Owns;

	private GameObject m_Allies;

	private GameObject m_Enemies;

	private GameObject m_EnemyAllies;

	private GameObject m_Slots;

	public bool ShowUnitEdit
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public GameObject units
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public GameObject selectedUnitObj
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public bool showGrid
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public GameObject Owns
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public GameObject Allies
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public GameObject Enemies
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public GameObject EnemyAllies
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public GameObject Slots
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private void Awake()
	{
	}

	private void OnDrawGizmosSelected()
	{
	}

	private void _DrawGrid()
	{
	}

	private int _GetLevelAvg(GameObject units)
	{
		return 0;
	}
}
