using System;

public class GuildUIPresentHistoryListItem : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public int Index { get; set; }

		public GuildPresentHistory history { get; set; }

		public Action UpdateNationName { get; set; }

		public CellData(GuildPresentHistory history)
		{
		}
	}

	private CellData m_cellData;

	public UILabelLocalized labelDate;

	public UILabelLocalized labelType;

	public UILabelLocalized labelItem;

	public UILabelLocalized labelName;

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

	private void UpdateNationName()
	{
	}
}
