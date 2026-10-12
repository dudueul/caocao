using System;
using UnityEngine;

namespace ExchangeStore
{
	public class RightBox : IDisposable
	{
		private Transform m_rightBox;

		private ExchangeShopItemInfoController m_controller;

		public TooltipContentsGenerator itemInfo { get; private set; }

		public StoreItemNumControl itemNumControl { get; private set; }

		public TooltipTailSkinDetailBtn btnSkinDetail { get; private set; }

		public RightBox(Transform transform)
		{
		}

		public void Clear()
		{
		}

		public void SetSelectStoreItem(StoreItem storeItem)
		{
		}

		public void Dispose()
		{
		}
	}
}
