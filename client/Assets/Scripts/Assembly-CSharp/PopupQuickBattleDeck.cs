using System.Collections.Generic;
using UnityEngine;

public class PopupQuickBattleDeck : CaocaoUIModalPopup
{
	[SerializeField]
	private UILabelLocalized m_title;

	[SerializeField]
	private UILabelLocalized m_type;

	[SerializeField]
	private UIButton m_closeBtn;

	[SerializeField]
	private List<QuickBattleDeckListItem> m_listItems;

	private EnumData.BattleMode battleMode;

	private EnumData.QuickBattleDeckAction currentAction;

	protected override void Awake()
	{
	}

	private void Initialize()
	{
	}

	public static void Show(EnumData.BattleMode battleMode, EnumData.QuickBattleDeckAction action)
	{
	}

	public void UpdateUI()
	{
	}

	private void UpdateTitle()
	{
	}

	private void UpdateType()
	{
	}

	private void UpdateGrid()
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
