using System.Collections.Generic;

namespace TreasureBook
{
	public class ObtainableUniqueInBoxShop : ObtainableBookMode
	{
		private enum TabMenu
		{
			All = 0,
			UnitSkin = 1,
			Dedicated = 2
		}

		private class ItemCompaerer : IEqualityComparer<Item>
		{
			public bool Equals(Item x, Item y)
			{
				return false;
			}

			public int GetHashCode(Item obj)
			{
				return 0;
			}
		}

		private TooltipTailSkinDetailBtn skinDetailBtn;

		private ItemCompaerer itemComp;

		public BoxShop boxPd { get; private set; }

		public ObtainableUniqueInBoxShop(int boxshopId)
		{
		}

		private void SetTabButton(TabMenu menu, string text)
		{
		}

		protected override void SetTabButtons()
		{
		}

		protected override void SetDetailTooltip()
		{
		}

		protected override void OnItemSelected(TreasureBookCellData selectedCellData)
		{
		}

		protected override List<int> GetObtainableItems(int subIdx)
		{
			return null;
		}

		private List<int> _getObtainableItems(HashSet<TabMenu> filters)
		{
			return null;
		}

		private bool CheckItemFilter(TabMenu menu, Item item)
		{
			return false;
		}

		private bool CheckItemFilterList(HashSet<TabMenu> filterList, Item item)
		{
			return false;
		}
	}
}
