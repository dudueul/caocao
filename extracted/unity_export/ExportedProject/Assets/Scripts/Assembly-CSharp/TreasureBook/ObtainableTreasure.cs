using System.Collections.Generic;

namespace TreasureBook
{
	public class ObtainableTreasure : ObtainableBookMode
	{
		private int[] m_tiers;

		private List<EnumData.ItemSlotType> m_exclude;

		public ObtainableTreasure(int[] tiers, List<EnumData.ItemSlotType> exclude)
		{
		}

		protected override void SetTabButtons()
		{
		}

		protected override List<int> GetObtainableItems(int subIdx)
		{
			return null;
		}

		private void _GetObtainableTreasureItems(ItemRatio itemRatio, EnumData.ItemSlotType slotType, ref List<int> targets)
		{
		}

		private EnumData.ItemSlotType GetSlotType(EnumData.TreasureTapMenu subIdx)
		{
			return (EnumData.ItemSlotType)0;
		}
	}
}
