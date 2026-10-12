public class GuildWarCorpsPassiveListItem : UIReuseScrollViewCell
{
	public class CorpsPassiveCellData : IReuseCellData
	{
		public int guildwarPassiveId;

		public int Index { get; set; }
	}

	private CorpsPassiveCellData m_passiveCellData;

	public UILabelLocalized PassiveName;

	public UILabelLocalized PassiveDesc;

	public CorpsPassiveCellData PassiveCellData
	{
		get
		{
			return null;
		}
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}
}
