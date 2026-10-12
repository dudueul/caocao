using UnityEngine;

public class HeroListTextCell : UIReuseScrollViewCell
{
	private HeroListTextCellData m_curCellData;

	private UILabelLocalized[] m_labels;

	private ItemUI[] m_itemUIs;

	private GameObject m_focusObj;

	private GameObject m_noticeObj;

	private GameObject m_itemUIPrefab;

	private ItemUI CreateItemUI(Transform parent)
	{
		return null;
	}

	public void Awake()
	{
	}

	private void OnDestroy()
	{
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	private bool IsItemSortType(EnumData.SortType sortType)
	{
		return false;
	}

	private void OnClick()
	{
	}

	public void ShowItemTooltip(ItemUI itemUI)
	{
	}
}
