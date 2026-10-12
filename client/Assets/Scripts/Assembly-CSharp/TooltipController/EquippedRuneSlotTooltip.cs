using System;
using UnityEngine;

namespace TooltipController
{
	public class EquippedRuneSlotTooltip : ITooltipController, IDisposable
	{
		private RuneItemSlot m_runeItemSlot;

		private UserRuneSlot m_userRuneSlot;

		private UserUnit m_userUnit;

		private bool m_showBtn;

		private Action m_callback;

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

		public EquippedRuneSlotTooltip(UserUnit userUnit, int runeSlotNum, bool showBtn, Action callback = null)
		{
		}

		public EquippedRuneSlotTooltip(UserUnit userUnit, int runeSlotIndex, int runeSlotNum, bool showBtn, Action callback = null)
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

		private void OnReinforce(TooltipTailRuneBtn comp)
		{
		}

		private void OnChange(TooltipTailRuneBtn comp)
		{
		}
	}
}
