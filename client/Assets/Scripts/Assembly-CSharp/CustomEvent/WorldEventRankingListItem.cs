using UnityEngine;

namespace CustomEvent
{
	public class WorldEventRankingListItem : MonoBehaviour
	{
		[SerializeField]
		private UILabelLocalized title;

		private readonly Vector3 rewardPosition;

		private readonly Vector3 instScale;

		public void UpdateReward(CustomEventReward eReward)
		{
		}
	}
}
