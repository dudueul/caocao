using UnityEngine;

public class RaidRankingListItem : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public int Index { get; set; }

		public UserRaidAwakenAttackInfo Info { get; set; }
	}

	[SerializeField]
	private UILabelLocalized m_rankLabel;

	[SerializeField]
	private UILabelLocalized m_nameLabel;

	[SerializeField]
	private UILabelLocalized m_positionLabel;

	[SerializeField]
	private UILabelLocalized m_lordRankLabel;

	[SerializeField]
	private UILabelLocalized m_powerLabel;

	[SerializeField]
	private UILabelLocalized m_scoreLabel;

	protected CellData m_cellData;

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

	protected virtual void OnDestroy()
	{
	}
}
