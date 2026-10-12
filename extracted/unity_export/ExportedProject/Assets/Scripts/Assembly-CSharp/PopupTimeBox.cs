using System.Collections.Generic;

public class PopupTimeBox : CaocaoUIModalPopup
{
	public enum TabMode
	{
		Common = 0,
		Weekly = 1,
		Arena = 2
	}

	private static PopupTimeBox self;

	private List<UserTimeBox> userTimeBoxes;

	private Dictionary<int, TimeBoxSlotItem> slotListItems;

	public UILabelLocalized seasonTitle;

	public UILabelLocalized seasonSubTitle;

	public UIGrid unitSpritesGrid;

	public UIButton timeBoxInfoBtn;

	public CaocaoTabButtons tabButtons;

	public UIGrid timeBoxSlotsGrid;

	public UIButton closeBtn;

	private TabMode tabMode;

	private static readonly int[] SLOT_START_INDEX;

	private static readonly TabMode[] SLOT_TABMODE;

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}

	public static void ShowUI(TabMode tabMode = TabMode.Common)
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public static void UpdateSlot(int index)
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateUIByTabMode()
	{
	}

	private void Init()
	{
	}

	private void OnTabChanged(CaocaoButton button)
	{
	}

	private void UpdateSeasonTitle()
	{
	}

	private void UpdateUnitSprite()
	{
	}

	private void ClearGrid()
	{
	}

	private void UpdateSlotItems()
	{
	}

	private void UpdateCommonRewardSlotItem()
	{
	}

	private void UpdateWeeklyRewardsSlotItem()
	{
	}

	private void UpdateArenaRewardSlotItem()
	{
	}

	public void OnBoxInfoBtnClicked()
	{
	}
}
