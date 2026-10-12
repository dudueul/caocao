using System;
using UnityEngine;

namespace TooltipController
{
	public class RuneRewardTooltip : ITooltipController, IDisposable
	{
		private Rune m_runePd;

		private RunePassive m_runePassivePd;

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

		public RuneRewardTooltip(Rune runePd, RunePassive runePassivePd)
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
