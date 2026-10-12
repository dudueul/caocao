using System;
using UnityEngine;

namespace Worldboss
{
	public class WorldBossShopListItem : MonoBehaviour
	{
		public Transform[] tierTrans;

		public RewardItemUI[] rewardItemList;

		public ItemNumControl itemNumControl;

		public UIButton btnPurchase;

		public UILabelLocalized costValue;

		private int selectedIndex;

		private PopupWorldboss container;

		private Action<int, int> onPurchaseHandler;

		private WorldBossShop[] shopDataList;

		private const int MAX_ITEMCONTROL_NUM = 10;

		public UILabelLocalized title { get; private set; }

		public UILabelLocalized stock { get; private set; }

		public WorldBossTier tier { get; private set; }

		public void OnDestroy()
		{
		}

		public void Init(WorldBossTier tier, PopupWorldboss container)
		{
		}

		public void UpdateUI()
		{
		}

		public void SetOnPurchaseHandler(Action<int, int> handler)
		{
		}

		private int GetGroupPurchaseCount()
		{
			return 0;
		}

		private int GetPurchaseCount(int shopId)
		{
			return 0;
		}

		public void OnSelectRewardItem(int index, bool forceUpdate = false)
		{
		}

		private void _updateSlot(int index, int groupStock)
		{
		}

		private int _getMaxItemControl(WorldBossShop shopData, int globalStock)
		{
			return 0;
		}

		private void _updateSlotCount(int index, WorldBossShop shopData, int globalStock)
		{
		}
	}
}
