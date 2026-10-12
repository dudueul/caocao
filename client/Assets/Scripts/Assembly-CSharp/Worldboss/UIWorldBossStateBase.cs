using System;

namespace Worldboss
{
	public abstract class UIWorldBossStateBase : UIReuseScrollViewCell
	{
		public class CellData : IReuseCellData
		{
			public int Index { get; set; }

			public string yyyymmdd { get; set; }

			public int phase { get; set; }

			public int worldBossId { get; set; }

			public ulong curHp { get; set; }

			public ulong maxHp { get; set; }

			public bool isClear { get; set; }

			public ulong rankRecord { get; set; }

			public DateTime startDate { get; set; }

			public DateTime endDate { get; set; }

			public DateTime earlyClearDate { get; set; }

			public DateTime completeDate { get; set; }

			public PopupWorldboss popupBase { get; set; }

			public WorldBossPhaseInfo phaseInfo
			{
				get
				{
					return null;
				}
			}

			public bool alreadyReceivedReward
			{
				get
				{
					return false;
				}
			}

			public void SetData(WorldBossInstance instance)
			{
			}
		}

		public UISlider gaugeSlider;

		public UILabelLocalized gaugeValue;

		public RewardItemUI[] rewardItemList;

		public UIButton btnBossInfo;

		public UIButton btnReceive;

		public UILabelLocalized bossName;

		public UITexture bossFace;

		public UILabelLocalized bossSpeech;

		public UILabelLocalized highScore;

		public UILabelLocalized rewardLabel;

		protected bool updatingGaugeBar;

		public CellData cellData
		{
			get
			{
				return null;
			}
			private set
			{
			}
		}

		protected void UpdateGaugeBar(ulong fromValue, ulong toValue, float time = 1f)
		{
		}

		protected void OnWorldBossHPUpdate(float hp)
		{
		}

		protected void OnWorldBossHPComplete(ulong hp)
		{
		}

		public override void UpdateData(IReuseCellData bossCellData)
		{
		}

		protected virtual bool CheckEarlyClearSuccess()
		{
			return false;
		}

		protected void _setReward(int slotIndex, RewardInfo reward)
		{
		}

		protected void DimRewards(bool dim)
		{
		}

		protected void UpdateRewardFrame(RewardItemUI.FrameType frameType)
		{
		}

		public void AddBossInfoClickHandler(Action<CellData> callback)
		{
		}
	}
}
