using UnityEngine;

namespace CustomEvent
{
	public class WorldEventBanner : MonoBehaviour
	{
		[SerializeField]
		private UILabelLocalized title;

		[SerializeField]
		private UILabelLocalized subTitle;

		[SerializeField]
		private UITexture bannerImage;

		private PanelCustomEvents panel;

		private CustomEventIngames eventContainer;

		public ulong eventIngameId { get; private set; }

		public CustomEventIngame eventIngame
		{
			get
			{
				return null;
			}
		}

		public void SetEventInfo(ulong eventIngameId, CustomEventIngames eventContainer, PanelCustomEvents panel)
		{
		}

		public void UpdateUI()
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
