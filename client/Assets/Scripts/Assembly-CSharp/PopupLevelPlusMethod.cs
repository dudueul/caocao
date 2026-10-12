using System.Collections.Generic;
using UnityEngine;

public class PopupLevelPlusMethod : CaocaoUIModalPopup
{
	public interface IParentLevelPlusMethod
	{
		Transform transform { get; }

		string methodTitle { get; }

		void SetItemData(ItemData itemData);

		List<ItemData> GetItemDatas(out ItemData selected);
	}

	public enum IdType
	{
		Goods = 0,
		Item = 1
	}

	public class ItemData
	{
		public int index;

		public IdType type;

		public int id;

		public int count;
	}

	public UIScrollView m_scrollView;

	public UIGrid m_itemGrid;

	private ItemUI m_focusedUI;

	private ItemData m_selected;

	private string m_title;

	public new IParentLevelPlusMethod parent { get; private set; }

	public static void Show(IParentLevelPlusMethod parent)
	{
	}

	private void UpdateUI()
	{
	}

	private void AddItemUI(ItemData itemData)
	{
	}

	private bool TryParseItemDataToItem(ItemData itemData, out int itemId, out int itemCount)
	{
		itemId = default(int);
		itemCount = default(int);
		return false;
	}

	public void OnChange()
	{
	}

	public void OnCancel()
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
