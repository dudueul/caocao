using System.Collections.Generic;
using UnityEngine;

namespace CustomEvent
{
	public class WorldEventUser : MonoBehaviour
	{
		[SerializeField]
		private UILabelLocalized title;

		[SerializeField]
		private UILabelLocalized scoreTitle;

		[SerializeField]
		private UILabelLocalized scoreValue;

		[SerializeField]
		private UIButton btnRanking;

		[SerializeField]
		private UIScrollView rewardScrollView;

		[SerializeField]
		private UIGrid rewardGrid;

		[SerializeField]
		private UIScrollView rankingScrollView;

		[SerializeField]
		private UIGrid rankingGrid;

		[SerializeField]
		private WorldEventRankingDetail rankingDetail;

		private WorldEvent parent;

		private CustomEventUnits unitContainer;

		private CustomEventRewards rewardContainer;

		private CustomEventUsers recordContainer;

		public ulong pointUnitId { get; private set; }

		public CustomEventUnit pointUnit
		{
			get
			{
				return null;
			}
		}

		public CustomEventUser pointUser
		{
			get
			{
				return null;
			}
		}

		public List<CustomEventReward> pointRewardList { get; private set; }

		public ulong rankUnitId { get; private set; }

		public CustomEventUnit rankUnit
		{
			get
			{
				return null;
			}
		}

		public List<CustomEventReward> rankRewardList { get; private set; }

		public void SetEventInfo(WorldEvent parent, ulong pointUnitId, ulong rankUnitId)
		{
		}

		public void UpdateUI()
		{
		}

		private void UpdateTitle()
		{
		}

		public void UpdateScore()
		{
		}

		private void UpdatePointReward()
		{
		}

		private void UpdateRankingDetail()
		{
		}

		private void UpdateRankingReward()
		{
		}
	}
}
