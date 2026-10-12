using UnityEngine;

public class UnitAbilityPopup : MonoBehaviour
{
	private UserUnit m_userUnit;

	private GameObject m_tooltipPanel;

	public GameObject m_popupButton;

	public PrefabInstance m_generalStatInst;

	public PrefabInstance m_troopsStatInst;

	public PrefabInstance m_equipInst;

	public PrefabInstance m_treasureEquipInst;

	public RuneSlotUI[] m_runeSlotUIs;

	public UILabelLocalized m_charType;

	private void OnDisable()
	{
	}

	private void OnDestroy()
	{
	}

	public void UpdateUI()
	{
	}

	private void UpdateUnitRune()
	{
	}

	private void OnItemClick(ItemUI itemUI)
	{
	}

	private void OnTreasureItemClick(ItemUI itemUI)
	{
	}
}
