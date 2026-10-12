using System.Collections;
using UnityEngine;

public class PanelBattleFieldDefense : CaocaoUIModalPopup
{
	[SerializeField]
	private PopupBattleFieldUnitList m_popupUnitList;

	[SerializeField]
	private PopupBattleFieldUnitInfo m_popupUnitInfo;

	[SerializeField]
	private PopupBattleFieldUnitSelect m_popupUnitSelect;

	[SerializeField]
	private PopupBattleFieldUnitMove m_popupUnitMove;

	[SerializeField]
	private UILabelLocalized m_labelTownName;

	private BattleField m_battleFieldData;

	private bool battleFieldMode
	{
		get
		{
			return false;
		}
	}

	private bool guildSiegeMode
	{
		get
		{
			return false;
		}
	}

	private void Start()
	{
	}

	public void ShowUnitList()
	{
	}

	public void UpdateTileAttribute()
	{
	}

	public void UpdateUnitList()
	{
	}

	public void ShowUnitInfo(UserUnit userUnit)
	{
	}

	public void CloseUnitInfo()
	{
	}

	public void ShowUnitSelect(UserUnit userUnit)
	{
	}

	public void CameraMoveEventComplete(Hashtable hashTable)
	{
	}

	public void ShowPopupUnitList()
	{
	}

	public void CloseUnitSelect()
	{
	}

	public void ShowPopupUnitMove(UnitData srcUnitData)
	{
	}

	public void ClosePopupUnitMove()
	{
	}

	public void OnClickReconSlot(Vector2 reconSlotPos, ReconSlot reconSlot)
	{
	}

	private void InitUI()
	{
	}

	private void OnClickHeroListItem(ulong srl, HeroListItem item)
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}
}
