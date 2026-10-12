using System.Collections.Generic;
using UnityEngine;

namespace CustomEvent
{
	public class WorldEvent : MonoBehaviour
	{
		[SerializeField]
		private PanelCustomEvents panel;

		[SerializeField]
		private WorldEventBanner eventBannerUI;

		[SerializeField]
		private WorldEventTotal worldEventUI;

		[SerializeField]
		private WorldEventUser userEventUI;

		[SerializeField]
		private WorldEventRule ruleEventUI;

		private UITable m_grid;

		private UIScrollView m_scrollView;

		private CustomEventIngame eventIngame;

		public CustomEventIngames eventIngames
		{
			get
			{
				return null;
			}
		}

		public CustomEventUnits eventUnits
		{
			get
			{
				return null;
			}
		}

		public CustomEventUsers eventUsers
		{
			get
			{
				return null;
			}
		}

		public CustomEventRewards eventRewards
		{
			get
			{
				return null;
			}
		}

		public UITable grid
		{
			get
			{
				return null;
			}
		}

		public UIScrollView scrollView
		{
			get
			{
				return null;
			}
		}

		public ulong worldUnitId { get; private set; }

		public ulong userUnitId { get; private set; }

		public ulong rankUnitId { get; private set; }

		public List<ulong> ruleUnitIds { get; private set; }

		public void Show(CustomEventIngame eventIngame)
		{
		}

		public void UpdateUI()
		{
		}

		public void UpdateWorldRecordValue(int recordValue)
		{
		}

		public void UpdateEventState()
		{
		}
	}
}
