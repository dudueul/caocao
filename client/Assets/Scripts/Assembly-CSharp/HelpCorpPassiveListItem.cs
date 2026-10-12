public class HelpCorpPassiveListItem : UIReuseScrollViewCell
{
	public class CorpsPassiveCellData : IReuseCellData
	{
		public int guildwarPassiveId;

		public int Index { get; set; }
	}

	private CorpsPassiveCellData m_passiveCellData;

	public UILabelLocalized PassiveName;

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

	private string GetPassiveAttributeName(GuildWarPassiveAttribute passiveAttribute, int passiveValue)
	{
		return null;
	}
}
