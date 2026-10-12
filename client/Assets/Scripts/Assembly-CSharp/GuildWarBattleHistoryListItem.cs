using UnityEngine;

public class GuildWarBattleHistoryListItem : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public int Index { get; set; }

		public int guildEmblemSpriteIndex { get; set; }

		public int guildWarRank { get; set; }

		public string guildName { get; set; }

		public string nationName { get; set; }

		public int killCount { get; set; }

		public int deathCount { get; set; }

		public int occupationCount { get; set; }

		public int destroyCount { get; set; }

		public int usedGold { get; set; }

		public int contribution { get; set; }
	}

	private CellData m_cellData;

	[SerializeField]
	private UITexture m_guildMarkTexture;

	[SerializeField]
	private UILabel m_guildName;

	[SerializeField]
	private UILabel m_nationName;

	[SerializeField]
	private UILabel m_killCount;

	[SerializeField]
	private UILabel m_deathCount;

	[SerializeField]
	private UILabel m_occupationCount;

	[SerializeField]
	private UILabel m_destroyCount;

	[SerializeField]
	private UILabel m_contribution;

	[SerializeField]
	private UILabel m_usedGold;

	public CellData cellData
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
}
