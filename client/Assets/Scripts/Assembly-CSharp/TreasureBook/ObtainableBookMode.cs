using System;
using System.Collections.Generic;
using UnityEngine;

namespace TreasureBook
{
	public abstract class ObtainableBookMode : IDisposable
	{
		private class ToolipController : ITooltipController
		{
			private GameObject m_prefabObj;

			private int m_itemId;

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

			public void Update(TooltipComponent comp, TooltipContentsGenerator generator)
			{
			}

			private void Update<TooltipComp>(TooltipComp comp) where TooltipComp : TooltipComponent
			{
			}
		}

		protected readonly Color Gradient2TopColor;

		protected readonly Color Gradient2BottomColor;

		protected TreasureBookCellData m_selectedCellData;

		protected UIButton[] m_buttons;

		protected TooltipContentsGenerator m_itemInfo;

		protected UIReuseScrollView m_scrollView;

		protected UIReuseGrid m_grid;

		protected int m_currentTabIdx;

		protected float m_originalPosXSubTab;

		private bool forceUpdateTooltipInPanel;

		public PopupObtainableTreasureBook parent { get; private set; }

		public void Init(PopupObtainableTreasureBook parent)
		{
		}

		public void Dispose()
		{
		}

		protected abstract void SetTabButtons();

		protected abstract List<int> GetObtainableItems(int subIdx);

		protected virtual void SetDetailTooltip()
		{
		}

		protected virtual void UpdateDetailUI()
		{
		}

		protected void UpdateGrid(int subIdx)
		{
		}

		protected void ActiveTabBtn(UIButton targetBtn, bool isActive)
		{
		}

		protected virtual void OnItemSelected(TreasureBookCellData selectedCellData)
		{
		}

		public void OnTabChanged(int subIdx)
		{
		}
	}
}
