public class PvpUnitStatisticListItem : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public PvpUnitStatistic unitStatistic;

		public int topCount;

		public int ranking;

		public int Index { get; set; }

		public int width { get; set; }

		public int height { get; set; }
	}

	public PrefabInstance unitUIPrefabInstance;

	public UILabelLocalized unitName;

	public UILabelLocalized rankLabel;

	public UILabel percentLabel;

	public UISlider slider;

	public UISprite bar;

	private CellData m_cellData;

	public PvpUnitStatistic unitStatistic;

	public int topCount;

	public int ranking;

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	public void UpdateUI(PvpUnitStatistic unitStatistic, int ranking, int topCount)
	{
	}

	protected void UpdateRankLabel()
	{
	}

	protected void UpdateUnitUI()
	{
	}

	protected void UpdatePercent()
	{
	}

	protected void UpdateBar()
	{
	}

	public void OnClick()
	{
	}
}
