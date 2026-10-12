using BattlePlay;
using UnityEngine;

public class PopupBrawlChangeUnitList : CaocaoUIModalPopup
{
	private enum Type
	{
		Only_Info = 0,
		Active_Tag = 1
	}

	[SerializeField]
	private UIBrawlChangeUnitListItem[] unitListItems;

	[SerializeField]
	private UIThousandPassiveGridItem conditionTooltip;

	private Type type;

	protected override void Awake()
	{
	}

	public static void ShowOnlyInfo()
	{
	}

	public static void ShowActiveTag()
	{
	}

	public void UpdateUI()
	{
	}

	private void OnTagUnit(BattleUnit tagBattleUnit)
	{
	}

	private void OnConditionClick(UnitCondition unitCondition)
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

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}
}
