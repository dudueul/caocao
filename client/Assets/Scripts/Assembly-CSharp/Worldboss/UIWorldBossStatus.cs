using LitJson;
using UnityEngine;

namespace Worldboss
{
	public class UIWorldBossStatus : MonoBehaviour, IWorldBossTab
	{
		public UIWorldBossContribution close;

		public UIWorldBossState open;

		public PopupBossInfo popupBossInfo;

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

		public void UpdateUI(bool updateBackground = false)
		{
		}

		protected void GoToBattleDeck(UIWorldBossStateBase.CellData data)
		{
		}

		protected void BuyWorldBossTicket(UIWorldBossStateBase.CellData data)
		{
		}

		private void OnResponseBuyWorldbossTicket(JsonData response)
		{
		}
	}
}
