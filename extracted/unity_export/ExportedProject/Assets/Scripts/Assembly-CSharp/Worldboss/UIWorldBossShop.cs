using UnityEngine;

namespace Worldboss
{
	public class UIWorldBossShop : MonoBehaviour, IWorldBossTab
	{
		public UIButton btnRanking;

		public UILabelLocalized maxToken;

		public UILabelLocalized curToken;

		public UILabelLocalized tierTitle;

		public PopupTotalRanking popupRanking;

		public UIScrollView scrollView;

		public UIGrid grid;

		public PopupWorldboss parent { get; private set; }

		public void Initialize(PopupWorldboss parent)
		{
		}

		public void SetActive(bool active)
		{
		}

		public void OnDestroy()
		{
		}

		public void Show()
		{
		}

		public void UpdateShopList()
		{
		}

		public void UpdateSeasonInfo()
		{
		}
	}
}
