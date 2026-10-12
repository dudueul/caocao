public class TimeBoxInfoListItem : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public string boxName;

		public string boxDesc;

		public int Index { get; set; }
	}

	public UILabelLocalized nameLabel;

	public UILabelLocalized description;

	private CellData data
	{
		get
		{
			return null;
		}
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	public void UpdateUI()
	{
	}
}
