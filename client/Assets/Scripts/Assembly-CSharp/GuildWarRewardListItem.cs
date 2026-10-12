using System.Collections.Generic;
using UnityEngine;

public class GuildWarRewardListItem : UIReuseScrollViewCell
{
	public class GuildWarRewardCellData : IReuseCellData
	{
		public int Index { get; set; }

		public int RewardGrade { get; set; }

		public Dictionary<int, int> TownRewardList { get; set; }
	}

	[SerializeField]
	private GameObject m_GradeA;

	[SerializeField]
	private GameObject m_GradeB;

	[SerializeField]
	private GameObject m_GradeC;

	[SerializeField]
	private GameObject[] m_TownRewardList;

	private GuildWarRewardCellData m_guildWarRewardCellData;

	public GuildWarRewardCellData guildWarRewardCellData
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
