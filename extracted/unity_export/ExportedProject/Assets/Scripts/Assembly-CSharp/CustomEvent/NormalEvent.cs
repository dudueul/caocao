using UnityEngine;

namespace CustomEvent
{
	public class NormalEvent : MonoBehaviour
	{
		[SerializeField]
		private PanelCustomEvents panel;

		[SerializeField]
		private UIGrid gridUnit;

		[SerializeField]
		private UIScrollView scrollViewUnit;

		[SerializeField]
		private UITexture uiTexture;

		public CustomEventIngame selected { get; private set; }

		private CustomEventUnits eventUnits
		{
			get
			{
				return null;
			}
		}

		private CustomEventUsers eventUsers
		{
			get
			{
				return null;
			}
		}

		public void Show(CustomEventIngame eventIngame)
		{
		}

		public void UpdateUI()
		{
		}

		private void UpdateEventUnitGrid()
		{
		}

		private void AddEventUnitItem(CustomEventUnit eventUnitInfo)
		{
		}

		private void ClearGrid(UIGrid grid, UIScrollView scroll)
		{
		}

		private void UpdateEventIngameImage()
		{
		}

		private void OnGetEventIngameImage(Texture texture)
		{
		}
	}
}
