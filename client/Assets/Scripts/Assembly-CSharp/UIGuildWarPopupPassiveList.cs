using GuildWar;
using UnityEngine;

public class UIGuildWarPopupPassiveList : CaocaoUIModalPopup
{
	public GameObject passiveObject;

	public UIScrollView passivesScrollView;

	public UIGrid passivesGrid;

	public GameObject passiveTooltipObject;

	public UIGuildWarPassive passiveTooltip;

	public GameObject passiveListObject;

	public UIScrollView passiveListsScrollView;

	public UIGrid passiveListsGrid;

	private const int m_columnSizeStep = 168;

	private const int m_initialGridX = -47;

	private const string passivePoolName = "guildWarPassiveGridItem";

	private const string passiveListPoolName = "guildWarPassiveListGridItem";

	private UIGuildWarPassiveGridItem m_selectedPassiveItem;

	private int m_passiveTooltipScrollBgThreshold;

	private static UIGuildWarPopupPassiveList _self;

	private EBattleFieldObjectType m_targetObjectType;

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

	protected override void OnDestroy()
	{
	}

	public static void Show(Transform parent, EBattleFieldObjectType targetObjectType)
	{
	}

	public void UpdateUI()
	{
	}

	private void UpdatePassives()
	{
	}

	private void SelectPassive(UIGuildWarPassiveGridItem passive)
	{
	}

	private void RemoveAllItem()
	{
	}
}
