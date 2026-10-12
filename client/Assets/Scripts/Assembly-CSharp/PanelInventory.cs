using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;

public class PanelInventory : PanelSelfRegister2UIComponentsMonobehaviour
{
	private const string KEY_SORTORDER = "INVENTORY_SORTORDER_{0}_{1}";

	private const string KEY_DESCENDING = "INVENTORY_DESCENDING_{0}_{1}";

	private EnumData.InvenItemSortType[] sortOrder;

	private bool[] descending;

	private StoreItemUI.StoreItemCellData selectedItem;

	private Dictionary<ulong, StoreItem> storeItems;

	private StoreItemUI.StoreItemCellData[] cellDatas;

	public static PanelInventory self;

	private bool isSubscriptionItem;

	private List<UserItem> items;

	private int needSetGridNum;

	private UIReuseGrid m_grid;

	private TooltipContentsGenerator m_tooltip;

	private PanelInventoryListSort m_sorter;

	private UILabelLocalized m_sortingLabel;

	private UIButton m_gainInfoButton;

	private UILabelLocalized m_gainInfoButtonLabel;

	private UIReuseGrid grid
	{
		get
		{
			return null;
		}
	}

	private TooltipContentsGenerator tooltip
	{
		get
		{
			return null;
		}
	}

	private PanelInventoryListSort sorter
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized sortingLabel
	{
		get
		{
			return null;
		}
	}

	private UIButton gainInfoButton
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized gainInfoButtonLabel
	{
		get
		{
			return null;
		}
	}

	public static void Show()
	{
	}

	private void updateInventoryItems()
	{
	}

	private void InitSortOrder()
	{
	}

	private void SaveSortOrder()
	{
	}

	public override void ShowPanel()
	{
	}

	public override void ClosePanel()
	{
	}

	public void OnSortButton()
	{
	}

	public void OnGainInfoButton()
	{
	}

	private void DoShortCut(Shortcut shortCut)
	{
	}

	private void SetSortType(EnumData.InvenItemSortType sortType)
	{
	}

	private void UpdateUI()
	{
	}

	private void FixedUpdate()
	{
	}

	[DebuggerHidden]
	private IEnumerator LateUpdateGrid(int gridItemNum)
	{
		return null;
	}

	private void OnItemSelected(StoreItem storeItem)
	{
	}

	protected override void OnDestroy()
	{
	}
}
