using UnityEngine;

public class PanelUnitInfo : SelfRegister2UIComponentsMonobehaviour
{
	private PanelUnitTreeInfo.UnitInfo m_subTab;

	private UserUnit m_userUnit;

	public GameObject m_unitNameObj;

	public PrefabInstance m_equipInst;

	public PrefabInstance m_equipTreInst;

	public GameObject m_unitLookObj;

	public GameObject m_unitEnergyObj;

	public GameObject m_unitEtcInfoObj;

	public PanelUnitStat m_unitStatPanel;

	public PanelUnitPassive m_unitPassivePanel;

	public PanelUnitRune m_unitRunePanel;

	public PanelUnitTreeInfo.UnitInfo subTab
	{
		get
		{
			return PanelUnitTreeInfo.UnitInfo.Info;
		}
		set
		{
		}
	}

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

	private void RegistArenaUnitCallback(bool regist)
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	public void ShowPanel(PanelUnitTreeInfo.UnitInfo unitInfo, UserUnit userUnit)
	{
	}

	protected override void Awake()
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

	private void UpdateSubTab()
	{
	}

	private void UpdateUnitName()
	{
	}

	private void UpdateUnitLook()
	{
	}

	private void UpdateUnitEnergy()
	{
	}

	public void SetUnitItem()
	{
	}

	private void SetEtcInfo()
	{
	}

	private void UpdateSkillBtn()
	{
	}

	private void OnItemClick(ItemUI itemUI)
	{
	}

	private void OnTreasureItemClick(ItemUI itemUI)
	{
	}

	private void OnUpdatedItem()
	{
	}

	public void RotateLeft()
	{
	}

	public void RotateRight()
	{
	}
}
