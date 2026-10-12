using System;
using UnityEngine;

namespace ExchangeStore
{
	public class BtnPurchase : IDisposable
	{
		private UIButton m_btnPurchase;

		private Transform m_money3;

		private Transform m_money2;

		private Transform m_money1;

		public UISprite Icon { get; private set; }

		public UILabelLocalized Cost { get; private set; }

		public int TotalPrice { get; private set; }

		public BtnPurchase(Transform transform)
		{
		}

		public void Clear()
		{
		}

		public void SetPriceIcon(string icon)
		{
		}

		public void UpdatePrice(StoreItem item, int count)
		{
		}

		public void SetOnClickHandler(MonoBehaviour behaviour, string methodName)
		{
		}

		public void Dispose()
		{
		}
	}
}
