using System;
using UnityEngine;

namespace TooltipController
{
	public class RuneTooltip : ITooltipController, IDisposable
	{
		private UserRuneItem m_userRuneItem;

		private Rune m_runePd;

		private bool isUserData;

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

		public RuneTooltip(UserRuneItem userRuneItem)
		{
		}

		public RuneTooltip(Rune runePd)
		{
		}

		public void Dispose()
		{
		}

		public void Update(TooltipComponent comp, TooltipContentsGenerator generator)
		{
		}

		private void UpdateHead(TooltipRuneHead comp)
		{
		}

		private void UpdateStat(TooltipItemStat comp)
		{
		}

		private void UpdatePassive(TooltipRunePassive comp)
		{
		}

		private void UpdateExplain(TooltipExplanation comp)
		{
		}

		private void UpdateTail(TooltipTailRuneBtn comp)
		{
		}
	}
}
