using UnityEngine;

namespace CustomEvent
{
	public class WorldEventTotalRewardListItem : MonoBehaviour
	{
		[SerializeField]
		private UILabelLocalized title;

		[SerializeField]
		private Transform items;

		[SerializeField]
		private UIButton btnReceive;

		[SerializeField]
		private UILabelLocalized btnLabel;

		[SerializeField]
		private GameObject dim;

		[SerializeField]
		private GameObject normal;

		[SerializeField]
		private GameObject special;

		private readonly Vector3 instSale;

		public CustomEventReward eventReward { get; private set; }

		public void UpdateReward(CustomEventReward eReward, bool isLast = false)
		{
		}

		public void UpdateRecordValue(CustomEventUser eUser, int recordValue)
		{
		}

		public void SetOnClickHandler(EventDelegate.Callback requestCallback)
		{
		}
	}
}
