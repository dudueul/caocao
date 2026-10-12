using System.Collections.Generic;
using UnityEngine;

namespace CustomEvent
{
	public class WorldEventRule : MonoBehaviour
	{
		[SerializeField]
		private UILabelLocalized title;

		[SerializeField]
		private UIGrid grid;

		private const int MAX_EVENT_RULE = 5;

		private CustomEventUnits unitContainer;

		public List<ulong> eventUnitIds { get; private set; }

		public List<CustomEventUnit> eventUnitList { get; private set; }

		public void SetEventInfo(CustomEventUnits eventUnits, List<ulong> eventUnitIds)
		{
		}

		public void UpdateUI()
		{
		}
	}
}
