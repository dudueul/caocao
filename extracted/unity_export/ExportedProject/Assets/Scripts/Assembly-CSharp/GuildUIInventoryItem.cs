using System;

public class GuildUIInventoryItem : UIReuseScrollViewCell
{
	public class GuildInvenCellData : IReuseCellData
	{
		public int Index { get; set; }

		public bool good { get; set; }

		public int itemId { get; set; }

		public int amount { get; set; }

		public Action<int, bool> OnItemSelected { get; set; }

		public GuildInvenCellData(bool good, int itemId, int amount)
		{
		}
	}

	private GuildInvenCellData m_invenCellData;

	public PrefabInstance itemUIInstance;

	public PrefabInstance goodsUIInstance;

	private ItemUI m_itemUI;

	private GoodUI m_goodUI;

	private ItemUI itemUI
	{
		get
		{
			return null;
		}
	}

	private GoodUI goodUI
	{
		get
		{
			return null;
		}
	}

	public override int Index
	{
		get
		{
			return 0;
		}
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	private void UpdateUI()
	{
	}

	private void ItemClicked(ItemUI ui)
	{
	}

	private void GoodClicked(GoodUI ui)
	{
	}

	private void OnDestroy()
	{
	}
}
