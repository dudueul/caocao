using System;

public class TreasureListItem : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public UserUnit userUnit;

		public ulong itemSrl;

		public bool quickSlotMode;

		public IUserDataBase userData;

		public Action<CellData> OnClickHandler;

		public Action<CellData> OnDoubleClickHandler;

		private bool m_isSelected;

		public UserItem item
		{
			get
			{
				return null;
			}
		}

		public int Index { get; set; }

		public ItemUI itemUI { get; set; }

		public int tier
		{
			get
			{
				return 0;
			}
		}

		public bool isSelected
		{
			get
			{
				return false;
			}
			set
			{
			}
		}

		public bool isEquipped { get; protected set; }

		public bool isOwned { get; protected set; }

		public void ShowEquippedInfo(UserUnit userUnit, bool showDeckNumber)
		{
		}

		public void UpdateEquipInfo()
		{
		}

		public void UpdateOwnedInfo()
		{
		}
	}

	private ItemUI itemUI;

	private CellData cellData
	{
		get
		{
			return null;
		}
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	private void UpdateUI()
	{
	}

	private void LoadItemUI()
	{
	}

	private void OnSelectItem(ItemUI itemUI)
	{
	}

	private void OnDoubleClickItem(ItemUI itemUI)
	{
	}

	private void OnDestroy()
	{
	}
}
