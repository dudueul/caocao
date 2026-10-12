using UnityEngine;

public class PanelUnitPassive : SelfRegister2UIComponentsMonobehaviour
{
	private UserUnit m_userUnit;

	private UserArenaUnit m_userArenaUnit;

	private PassiveTableItem m_selected;

	private bool m_showActivatedOnly;

	private bool m_generalEffectOn;

	private bool m_treasureEffectOn;

	private bool m_troopsEffectOn;

	private bool m_mayorEffectOn;

	private bool m_lordEffectOn;

	private PassiveTableItem[] m_generalPassive;

	private PassiveTableItem[] m_treasurePassive;

	private PassiveTableItem[] m_troopsPassive;

	private PassiveTableItem m_mayorEffect;

	private PassiveTableItem m_lordEffect;

	private Transform m_tableTrans;

	private int m_totalPassiveCount;

	private bool isArenaUnit;

	public UserUnit userUnit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public UserArenaUnit userArenaUnit
	{
		get
		{
			return null;
		}
	}

	public PassiveTableItem selected
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private UITable m_table
	{
		get
		{
			return null;
		}
	}

	private UIScrollView m_scollView
	{
		get
		{
			return null;
		}
	}

	private GameObject m_checkActivatedOnly
	{
		get
		{
			return null;
		}
	}

	private GameObject m_group1Open
	{
		get
		{
			return null;
		}
	}

	private GameObject m_group2Open
	{
		get
		{
			return null;
		}
	}

	private GameObject m_group3Open
	{
		get
		{
			return null;
		}
	}

	private GameObject m_group4Open
	{
		get
		{
			return null;
		}
	}

	private GameObject m_group5Open
	{
		get
		{
			return null;
		}
	}

	private GameObject m_group1Close
	{
		get
		{
			return null;
		}
	}

	private GameObject m_group2Close
	{
		get
		{
			return null;
		}
	}

	private GameObject m_group3Close
	{
		get
		{
			return null;
		}
	}

	private GameObject m_group4Close
	{
		get
		{
			return null;
		}
	}

	private GameObject m_group5Close
	{
		get
		{
			return null;
		}
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	protected override void OnDestroy()
	{
	}

	public void ShowPanel(bool show, UserUnit userUnit)
	{
	}

	public void ClosePane()
	{
	}

	private void InitUI()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateActivate()
	{
	}

	private void UpdateActivate(PassiveTableItem passiveTableItem, bool isOn)
	{
	}

	public void ToggleShowActivated()
	{
	}

	public void ToggleGroup1()
	{
	}

	public void ToggleGroup2()
	{
	}

	public void ToggleGroup3()
	{
	}

	public void ToggleGroup4()
	{
	}

	public void ToggleGroup5()
	{
	}
}
