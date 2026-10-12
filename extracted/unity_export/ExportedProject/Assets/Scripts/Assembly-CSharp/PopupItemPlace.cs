using UnityEngine;

public class PopupItemPlace : CaocaoUIModalPopup
{
	private Item m_itemData;

	public PrefabInstance m_itemInst;

	public UILabelLocalized m_itemName;

	public UILabelLocalized m_itemCount;

	public UIButton m_confirmButton;

	public UIReuseGrid m_townGrid;

	public UIReuseScrollView m_townScrollView;

	private int m_needCount;

	private ItemPlaceListItem.ItemPlaceCellData m_selectedItem;

	public Item itemData
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private GameObject emptyList
	{
		get
		{
			return null;
		}
	}

	protected override void Awake()
	{
	}

	public static void Show(Item itemData, int materialCount)
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	private void UpdateUI()
	{
	}

	private void InitList()
	{
	}

	private void UpdateConfirmButton()
	{
	}

	private void OnSelected(ItemPlaceListItem.ItemPlaceCellData selectedItem)
	{
	}

	public void OnConfirm()
	{
	}

	private void GoToBattle()
	{
	}

	private void ClearList()
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
