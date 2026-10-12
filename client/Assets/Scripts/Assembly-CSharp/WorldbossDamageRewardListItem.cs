public class WorldbossDamageRewardListItem : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public int Index { get; set; }

		public int Rank { get; set; }

		public int Damage { get; set; }

		public int RewardAmount { get; set; }

		public bool Received { get; set; }
	}

	public UILabelLocalized m_rankLabel;

	public UILabelLocalized m_damageLabel;

	public RewardItemUI m_rewardUI;

	public override void UpdateData(IReuseCellData cellData)
	{
	}
}
