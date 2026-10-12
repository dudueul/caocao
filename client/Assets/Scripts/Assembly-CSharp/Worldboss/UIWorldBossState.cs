using System;

namespace Worldboss
{
	public class UIWorldBossState : UIWorldBossStateBase, IWorldBossTab
	{
		public UILabelLocalized remainTime;

		public UIButton btnGoBattle;

		public UILabelLocalized paticipationCount;

		public UserRankingUI[] topRankList;

		public UILabelLocalized myScore;

		public PopupWorldbossDamageReward popupDamageReward;

		public PopupWorldboss parent { get; private set; }

		public void Initialize(PopupWorldboss parent)
		{
		}

		public void SetActive(bool active)
		{
		}

		public void OnEnable()
		{
		}

		public void OnDisable()
		{
		}

		public void OnDestroy()
		{
		}

		private void UpdateTimer()
		{
		}

		public void UpdateUI()
		{
		}

		public override void UpdateData(IReuseCellData bossCellData)
		{
		}

		public void UpdatePhaseInfo(WorldBossPhaseInfo phaseInfo)
		{
		}

		public void UpdateRemainTime()
		{
		}

		public void AddGoBattleClickHander(Action<CellData> callback)
		{
		}

		private void InitTopRankList(int rankingCount)
		{
		}

		public void UpdateRankInfo()
		{
		}

		public void OnClickDamageRewardButton()
		{
		}
	}
}
