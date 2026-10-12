using System;
using UnityEngine;

public class StoreItemUI : UIReuseScrollViewCell
{
	public class StoreItemCellData : IReuseCellData
	{
		public int Index { get; set; }

		public int itemId { get; set; }

		public bool selected { get; set; }

		public StoreItem storeItem { get; set; }

		public Action<StoreItem> OnItemSelected { get; set; }
	}

	private StoreItemCellData m_storeItemCellData;

	private ItemUI m_itemUI;

	private UISprite m_priceIcon;

	private UILabelLocalized m_priceLabel;

	private UILabelLocalized m_nameLabel;

	private Transform m_vipLock;

	private UILabelLocalized m_vipLockLabel;

	private Transform m_selectedSprite;

	private Transform m_selectNowSprite;

	private Transform m_bgTreasure;

	private Transform m_soldOut;

	private UILabelLocalized m_soldOutLabel;

	public Action<StoreItem> OnItemSelected;

	public ItemUI itemUI
	{
		get
		{
			return null;
		}
	}

	public UISprite priceIcon
	{
		get
		{
			return null;
		}
	}

	public UILabelLocalized priceLabel
	{
		get
		{
			return null;
		}
	}

	public UILabelLocalized nameLabel
	{
		get
		{
			return null;
		}
	}

	public Transform vipLock
	{
		get
		{
			return null;
		}
	}

	public UILabelLocalized vipLockLabel
	{
		get
		{
			return null;
		}
	}

	public Transform selectedSprite
	{
		get
		{
			return null;
		}
	}

	public Transform selectNowSprite
	{
		get
		{
			return null;
		}
	}

	public Transform bgTreasure
	{
		get
		{
			return null;
		}
	}

	public Transform soldOut
	{
		get
		{
			return null;
		}
	}

	public UILabelLocalized soldOutLabel
	{
		get
		{
			return null;
		}
	}

	public StoreItem storeItem { get; private set; }

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	private void OnDestroy()
	{
	}

	public void UpdateStoreItemData(StoreItem _storeItem)
	{
	}

	private void UpdateStoreItemUI()
	{
	}

	private void ItemClicked(ItemUI ui)
	{
	}

	private void OnClick()
	{
	}

	public void ActivateSelectUi(StoreItem storeItem)
	{
	}

	public void DeactivateSelectUi(StoreItem storeItem)
	{
	}

	public void SelectItem()
	{
	}
}
