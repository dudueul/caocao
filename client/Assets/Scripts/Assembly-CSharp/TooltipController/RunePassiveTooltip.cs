using System;
using UnityEngine;

namespace TooltipController
{
	public class RunePassiveTooltip : ITooltipController, IDisposable
	{
		private int m_passiveId;

		private int m_passiveValue;

		private string m_desc;

		private UserUnit m_userUnit;

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

		public RunePassiveTooltip(UserUnit userUnit, RuneItemSlot runeItemSlot)
		{
		}

		public void Dispose()
		{
		}

		public void Update(TooltipComponent comp, TooltipContentsGenerator generator)
		{
		}

		private void UpdateHead(TooltipPassiveHead comp)
		{
		}

		private void UpdateExplainPassive(TooltipExplanation comp)
		{
		}

		private void UpdatePassiveTerms(TooltipPassiveTerms comp)
		{
		}

		private void UpdateTail(TooltipTailPassiveBtn comp)
		{
		}
	}
}
