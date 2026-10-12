using UnityEngine;

namespace CustomEvent
{
	public class WorldEventRuleListItem : MonoBehaviour
	{
		[SerializeField]
		private UILabelLocalized title;

		[SerializeField]
		private UILabelLocalized value;

		public void UpdateUI(CustomEventUnit eUnit)
		{
		}
	}
}
