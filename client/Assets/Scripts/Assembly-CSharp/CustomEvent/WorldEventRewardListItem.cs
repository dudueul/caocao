using UnityEngine;

namespace CustomEvent
{
	public class WorldEventRewardListItem : MonoBehaviour
	{
		[SerializeField]
		public UILabelLocalized title;

		[SerializeField]
		public UIButton btnReceive;

		[SerializeField]
		public UILabelLocalized btnLabel;

		[SerializeField]
		public GameObject clear;

		private readonly Vector3 rewardPosition;

		private readonly Vector3 instScale;

		public CustomEventReward eventReward { get; private set; }

		public void UpdateReward(CustomEventReward eReward)
		{
		}

		public void UpdateRecordValue(CustomEventUser eUser)
		{
		}

		public void SetOnClickHandler(EventDelegate.Callback requestCallback)
		{
		}
	}
}
