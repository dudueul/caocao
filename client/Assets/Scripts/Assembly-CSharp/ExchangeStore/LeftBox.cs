using System;
using System.Collections.Generic;
using UnityEngine;

namespace ExchangeStore
{
	public class LeftBox : IDisposable
	{
		public class TicketUI : IDisposable
		{
			public const string defaultTicketIcon = "icon_ExchangeShop_80x80";

			public UISprite Icon { get; private set; }

			public UILabelLocalized Value { get; private set; }

			public string TicketIcon { get; private set; }

			public Item ItemPd { get; private set; }

			public TicketUI(Transform transform)
			{
			}

			public void Update(int ticketItemId, string ticketIcon)
			{
			}

			private void UpdateTicketData()
			{
			}

			public void Dispose()
			{
			}
		}

		public class ResetTimerUI : IDisposable
		{
			private Transform m_transform;

			public UILabelLocalized Text { get; private set; }

			public UILabelLocalized Value { get; private set; }

			public ResetTimerUI(Transform transform)
			{
			}

			public void SetActive(bool active)
			{
			}

			public void SetText(string key)
			{
			}

			public void Update(DateTime endDate)
			{
			}

			public void Dispose()
			{
			}
		}

		private Transform m_transform;

		private UIReuseGrid m_itemGrid;

		private UIReuseScrollView m_scrollView;

		public Action<StoreItem> OnSelectStoreItem;

		public StoreItem SelectedItem { get; private set; }

		public FilterItemType Filter { get; private set; }

		public TicketUI Ticket { get; private set; }

		public ResetTimerUI ResetTimer { get; private set; }

		public LeftBox(Transform transform)
		{
		}

		public void Clear()
		{
		}

		public void UpdateItemList(IList<StoreItem> itemList, bool resetPosition = true)
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
