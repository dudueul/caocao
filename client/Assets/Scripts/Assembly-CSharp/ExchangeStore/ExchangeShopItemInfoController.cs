using System;
using UnityEngine;

namespace ExchangeStore
{
	public class ExchangeShopItemInfoController : ITooltipController, IDisposable
	{
		private ExchangeStoreItem m_currentStoreItem;

		private Item m_itemPd;

		public TooltipComponent.Type componentType
		{
			get
			{
				return TooltipComponent.Type.Item;
			}
		}

		public GameObject tooltipPrefab
		{
			get
			{
				return null;
			}
		}

		public void SetStoreItem(ExchangeStoreItem storeItem)
		{
		}

		public void Update(TooltipComponent comp, TooltipContentsGenerator generator)
		{
		}

		public void UpdateHead(TooltipItemHeadForStore comp)
		{
		}

		public void UpdateStatItem(TooltipItemStat comp)
		{
		}

		public void UpdateSPItem(TooltipItemSP comp, bool isActive, TooltipContentsGenerator generator)
		{
		}

		public void UpdateExplanation(TooltipExplanation comp)
		{
		}

		public void UpdateWearJobType(TooltipItemWearJobType comp)
		{
		}

		public void Dispose()
		{
		}
	}
}
