using System.Collections.Generic;
using UnityEngine;

namespace Worldboss
{
	public class UIWorldBossRanking : MonoBehaviour
	{
		public UIReuseScrollView scrollView;

		public UIReuseGrid grid;

		public WorldBossRankingListItem myRank;

		public void OnDestroy()
		{
		}

		public void UpdateUI()
		{
		}

		private void UpdateUI(WorldBossRankingListItem.CellData myData, List<WorldBossRankingListItem.CellData> rankDataList)
		{
		}
	}
}
