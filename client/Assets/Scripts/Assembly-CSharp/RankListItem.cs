using System;
using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class RankListItem : UIReuseScrollViewCell
{
	public interface IRankingReusaCellData : IReuseCellData
	{
		EnumData.rankTab tab { get; }

		EnumData.rankType type { get; }

		EnumData.rankGroup group { get; }

		int rank { get; set; }

		int point { get; set; }

		float rankPct { get; set; }

		int lastWeekRank { get; set; }

		Action<IRankingReusaCellData> onClick { get; set; }
	}

	public class CellData : IRankingReusaCellData, IReuseCellData
	{
		public ulong userId;

		public int kingUnitID;

		public int userLev;

		public string nickName;

		protected int m_skinItemId;

		protected int m_faceUnitId;

		public int Index { get; set; }

		public EnumData.rankTab tab { get; set; }

		public EnumData.rankType type
		{
			get
			{
				return EnumData.rankType.Indicator;
			}
		}

		public EnumData.rankGroup group
		{
			get
			{
				return EnumData.rankGroup.User;
			}
		}

		public int rank { get; set; }

		public int point { get; set; }

		public float rankPct { get; set; }

		public int lastWeekRank { get; set; }

		public Action<IRankingReusaCellData> onClick { get; set; }

		public int rankId { get; set; }

		public int FaceId
		{
			get
			{
				return 0;
			}
		}

		public int VisibleFaceUnitId
		{
			get
			{
				return 0;
			}
		}

		public int VisibleFaceUnitFaceId
		{
			get
			{
				return 0;
			}
		}

		public CellData(int rank, ulong user_id, string userName, int point, int kingUnitID, int userLev, float rankPct, EnumData.rankTab tab, int rankId)
		{
		}

		public CellData(JsonData json, int rank, float rankPct, EnumData.rankTab tab, int rankId)
		{
		}
	}

	public class CompetitionCellData : CellData, IRankingReusaCellData, IReuseCellData
	{
		public int tierGrade;

		public string tierIcon;

		public new EnumData.rankType type
		{
			get
			{
				return EnumData.rankType.Indicator;
			}
		}

		public CompetitionCellData(JsonData json, int rank, float rankPct, EnumData.rankTab tab, int rankId)
			: base(0, 0uL, null, 0, 0, 0, 0f, EnumData.rankTab.current, 0)
		{
		}
	}

	public class GuildCellData : IRankingReusaCellData, IReuseCellData
	{
		public GuildInfo guildInfo;

		public string ownerNickName;

		public int Index { get; set; }

		public int rankId { get; set; }

		public EnumData.rankTab tab { get; set; }

		public EnumData.rankType type { get; set; }

		public EnumData.rankGroup group
		{
			get
			{
				return EnumData.rankGroup.User;
			}
		}

		public int rank { get; set; }

		public int point { get; set; }

		public float rankPct { get; set; }

		public int lastWeekRank { get; set; }

		public Action<IRankingReusaCellData> onClick { get; set; }

		public GuildCellData(int rank, GuildInfo guildInfo, int guildLv, string userName, int point, float rankPct, EnumData.rankTab tab, int rankId)
		{
		}

		public GuildCellData(JsonData json, int rank, float rankPct, EnumData.rankTab tab, int rankId)
		{
		}
	}

	public class GuildRankerCellData : GuildCellData, IRankingReusaCellData, IReuseCellData
	{
		public List<TopRankerData> topRankers;

		public Action<IRankingReusaCellData> onViewRankerListCallBack;

		public GuildRankerCellData(JsonData json, int rank, EnumData.rankTab tab, int rankId)
			: base(0, null, 0, null, 0, 0f, EnumData.rankTab.current, 0)
		{
		}
	}

	public class TopRankerData
	{
		public string nationName;

		public int rank;

		public TopRankerData(JsonData jsonData)
		{
		}
	}

	[SerializeField]
	private UIUserRanking uiUserRanking;

	[SerializeField]
	private UIGuildRanking uiGuildRanking;

	[SerializeField]
	private UIGuildTopRanker uiGuildTopRanker;

	[SerializeField]
	private UIRankNumber uiRankNumber;

	[SerializeField]
	private UIRankFluctuation uiRankFluctuation;

	public static IRankingReusaCellData GetCellData(EnumData.rankGroup rankGroup, EnumData.rankType rankType, JsonData json, int rank, float myRankPct, EnumData.rankTab tab, int rankId)
	{
		return null;
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	private void OnClick()
	{
	}
}
