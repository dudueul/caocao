using System.Collections.Generic;
using UnityEngine;

namespace CustomEvent
{
	public class WorldEventTotal : MonoBehaviour
	{
		[SerializeField]
		private UILabelLocalized title;

		[SerializeField]
		private UILabelLocalized scoreTitle;

		[SerializeField]
		private UILabelLocalized totalValue;

		[SerializeField]
		private UISlider totalSlider;

		[SerializeField]
		private UIGrid totalRewardGrid;

		public const int MAX_REWARD_COUNT = 4;

		private WorldEvent parent;

		private CustomEventUnits unitContainer;

		private CustomEventRewards rewardContainer;

		private CustomEventUsers recordContainer;

		public ulong eventUnitId { get; private set; }

		public CustomEventUnit eventUnit
		{
			get
			{
				return null;
			}
		}

		public CustomEventUser eventUser
		{
			get
			{
				return null;
			}
		}

		public int recordValue { get; private set; }

		public List<CustomEventReward> eventRewardList { get; private set; }

		public void SetEventInfo(WorldEvent parent, ulong eventUnitId)
		{
		}

		protected void OnDisable()
		{
		}

		public void UpdateEventRecord()
		{
		}

		public void UpdateUI()
		{
		}

		private void UpdateTitle()
		{
		}

		private void UpdateRewards()
		{
		}

		private void UpdateRecordValue()
		{
		}

		private void UpdateSliderValue()
		{
		}

		private void UpdateSliderValue(int fromValue, int toValue, float time = 1f)
		{
		}

		private void OnWorldPointUpdate(float worldPoint)
		{
		}

		private void OnWorldPointCompleted(int worldPoint)
		{
		}
	}
}
