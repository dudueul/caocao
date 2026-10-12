using System;

public class TooltipContentsGenerator : CaocaoTooltip
{
	public class PassiveComponent
	{
		public int PassiveIndex;

		public int PassiveCount;

		public int ItemId;

		public int EquipUnitId;
	}

	public class SkinItemComponent
	{
		public int SkinItemId;
	}

	private PassiveComponent m_passiveComponent;

	private void ResetPassiveIndex(Item pdItem, UserUnit userUnit)
	{
	}

	public bool IsActive(TooltipComponent comp)
	{
		return false;
	}

	public bool AddItemMultiSpActionListItem(TooltipComponent comp, Item pdItem, UserUnit userUnit, UserItem userItem)
	{
		return false;
	}

	public void UpdateItemTooltip(int itemId, UserItem userItem)
	{
	}

	public void UpdateItemRegisterTooltip(int itemId, UserItem userItem, bool isRegistered, Action callback)
	{
	}

	public void UpdateItemTooltip(int itemId, UserItem userItem, UserUnit userUnit)
	{
	}

	public void UpdateItemTooltip(int itemId, UserItem userItem, UserUnit userUnit, TroopStat compItemStat)
	{
	}

	public void UpdateItemTooltip(int itemId, UserItem userItem, UserUnit userUnit, TroopStat compItemStat, int quickSlotDeckId)
	{
	}

	public void UpdateItemTooltip(int itemId, UserItem userItem, UserUnit userUnit, int quickSlotDeckId)
	{
	}

	public void UpdateItemTooltip(int itemId, RewardInfo rewardInfo)
	{
	}

	public void UpdateStoreItemTooltip(StoreItem storeItem)
	{
	}

	public void UpdateStoreItemTooltip(StoreItem storeItem, Action btnCallback)
	{
	}

	public void UpdateGuildItemTooltip(int itemId, int amount, bool hasPresentPermission, Action onPresent, Action onCondition)
	{
	}

	public void UpdateMoneyTooltip(EnumData.GoodsType goodsType, int amount)
	{
	}

	public void UpdateMoneyTooltip(RewardInfo rewardInfo)
	{
	}

	public void UpdateUnitTooltip(int unitId)
	{
	}

	public void UpdateUnitTooltip(int unitId, UserUnit userUnit)
	{
	}

	public void UpdateUnitTooltip(int unitId, SkinItemComponent skinItemComponent, RewardInfo rewardInfo)
	{
	}

	public void UpdatePassiveTooltip(int passiveId, int passiveVal, Item triggerItem)
	{
	}

	public void UpdatePassiveTooltip(int passiveId, int passiveVal, Unit triggerUnit)
	{
	}

	public void UpdatePassiveTooltip(PassiveList passiveList)
	{
	}

	public void UpdatePassiveTooltip(PassiveList passiveList, TreasureEnhancePassive enhancePassive)
	{
	}

	public void UpdatePassiveTooltip(int passiveId, int passiveVal)
	{
	}

	public void UpdatePassiveTooltip(int passiveId, int[] passiveParams, UserUnit userUnit)
	{
	}

	public void UpdatePassiveTooltip(int passiveId, int passiveVal, UserUnit userUnit)
	{
	}

	public void UpdatePassiveTooltip(PassiveList passiveList, UserUnit userUnit, int index)
	{
	}

	public void UpdatePassiveTooltip(PassiveList passiveList, string descTypeText)
	{
	}

	public void UpdatePassiveTooltip(int passive, int val, string descTypeText)
	{
	}

	public void UpdateTreasureEnhancePassiveTooltip(UserItem userItem)
	{
	}

	public void UpdateRunePassiveTooltip(int passiveId, UserUnit userUnit, int runeSlotIdx)
	{
	}

	public void UpdateRunePassiveTooltip(int passiveId, UserUnit userUnit, RuneItemSlot runeItemSlot)
	{
	}

	public void UpdateRunePassiveTooltip(PassiveList passiveList, UserUnit unit)
	{
	}

	public void UpdateArenaPassiveTooltip(UserArenaPassive userArenaPassive, UserArenaUnit userArenaUnit, int index)
	{
	}

	public void UpdateArenaPassiveTooltip(ArenaPassiveList pdArenaPassive, string descType = "", EnumData.ArenaPassiveType globalPassiveType = EnumData.ArenaPassiveType.None)
	{
	}

	public void UpdateArenaPassiveTooltip(int passiveId, int[] passiveParams, UserUnit userUnit, EnumData.ArenaPassiveType globalPassiveType = EnumData.ArenaPassiveType.None)
	{
	}

	public void UpdateLordEffect(UserUnit userUnit)
	{
	}

	public void UpdateMayorEffect(UserUnit userUnit, UserTown userTown)
	{
	}

	public void UpdateBaseStatEffect(EnumData.StatType statType, int statValue)
	{
	}

	public void UpdateItemTooltip(int itemId)
	{
	}

	public void UpdateTooltip(ITooltipController controller)
	{
	}
}
