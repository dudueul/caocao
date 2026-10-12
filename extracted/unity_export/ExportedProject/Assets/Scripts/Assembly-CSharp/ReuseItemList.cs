using System;

public class ReuseItemList : UIReuseScrollViewCell
{
	public class ItemCellData : IReuseCellData
	{
		public Action<ItemCellData> OnItemClicked;

		public Action<ItemCellData> OnItemDoubleClicked;

		public int Index { get; set; }

		public UserItem userItem { get; set; }

		public bool isScrollable { get; set; }

		public bool dim { get; set; }

		public bool focus { get; set; }

		public bool isSelected { get; set; }

		public int id
		{
			get
			{
				return 0;
			}
		}

		public Item itemData
		{
			get
			{
				return null;
			}
		}
	}

	private ItemCellData m_itemCellData;

	public ItemCellData itemCellData
	{
		get
		{
			return null;
		}
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	private void OnItemClicked(ItemUI item)
	{
	}

	private void OnItemDoubleClicked(ItemUI item)
	{
	}
}
