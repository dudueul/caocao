using System.Collections;
using System.Collections.Generic;
using LitJson;
using UnityEngine;

namespace CustomEvent
{
	public class WorldEventRankingDetail : MonoBehaviour
	{
		[SerializeField]
		private UIButton btnClose;

		[SerializeField]
		private UIGrid grid;

		private const int MAX_RANKING_PRINT = 10;

		protected void Awake()
		{
		}

		public void UpdateRankList(WorldEventRankingDetailListItem.RankingData myRank, float myRankPercent, List<WorldEventRankingDetailListItem.RankingData> rankingList)
		{
		}

		private void AddRankingGrid(int index, WorldEventRankingDetailListItem.RankingData rankingData, float rankPercent = -1f)
		{
		}

		private void RemoveRankingGrid(int index)
		{
		}

		public IEnumerator RequestRankData(ulong eventUnitId, int rankStart, int rankEnd)
		{
			return null;
		}

		private void OnGetEventRank(JsonData response)
		{
		}

		public void Hide()
		{
		}
	}
}
