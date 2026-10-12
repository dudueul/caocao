using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

public class PopupBattleFieldUnitInfo : MonoBehaviour
{
	[SerializeField]
	private ReinforceLev m_reinforceLev;

	[SerializeField]
	private UnitUI m_unitUI;

	[SerializeField]
	private EquipUI m_equipUI;

	[SerializeField]
	private UILabelLocalized m_unitName;

	[SerializeField]
	private UILabelLocalized m_unitLevel;

	[SerializeField]
	private ClickHandler m_infoClickHandler;

	[SerializeField]
	private UIButton m_btnQuickEqiup;

	[SerializeField]
	private Transform m_tileAttribute;

	[SerializeField]
	private Transform m_defenseTreasure;

	[SerializeField]
	private EquipUI m_defenseTreasureEquipUI;

	private ObscuredULong m_initWeaponSrl;

	private ObscuredULong m_initArmorSrl;

	private ObscuredULong m_initKitSrl;

	private UserUnit m_serverUserUnit;

	private UserUnit m_userUnit;

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

	private void Start()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDestroy()
	{
	}

	private void OnDisable()
	{
	}

	public void UpdateUI()
	{
	}

	public void UpdateDefenseTreasureUI()
	{
	}

	public void UpdateTileAttribute()
	{
	}

	private void OnDefenseTreasureItemClick(ItemUI itemUI)
	{
	}

	public void UpdateDefenseTreasure()
	{
	}

	public void SetUnitInfo(UserUnit srcUserUnit)
	{
	}

	public void Close()
	{
	}

	public void OnClickQuickEquip()
	{
	}

	private void OnClickUnitInfo()
	{
	}

	private void UpdateQuickEquip()
	{
	}

	private void OnTreasureItemClick(ItemUI itemUI)
	{
	}

	private void OnTreasureItemUpdate()
	{
	}

	private void UpdateEquippedInfo()
	{
	}
}
