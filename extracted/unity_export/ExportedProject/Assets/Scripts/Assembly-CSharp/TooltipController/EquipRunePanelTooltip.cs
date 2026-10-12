using System;
using LitJson;
using UnityEngine;

namespace TooltipController
{
	public class EquipRunePanelTooltip : ITooltipController, IDisposable
	{
		private int m_runeSlotIndex;

		private int m_runeSlotNum;

		private UserRuneItem m_userRuneItem;

		private UserUnit m_userUnit;

		private Action m_callback;

		private TooltipContentsGenerator m_instance;

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

		public EquipRunePanelTooltip(UserUnit userUnit, UserRuneItem userRuneItem, int runeSlotIndex, int runeSlotNum, Action callback = null)
		{
		}

		public void Dispose()
		{
		}

		public void SetUserRuneItem(UserRuneItem userRuneItem)
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

		private void OnEquip(TooltipTailRuneBtn comp, EnumData.RuneEquipType runeEquipType)
		{
		}

		private void EquipRune()
		{
		}

		private void OnEquipRune(JsonData response)
		{
		}
	}
}
