using System;
using System.Collections.Generic;
using UnityEngine;

namespace CustomEvent
{
	public static class CustomEventUtil
	{
		public class RewardOrder : IComparer<CustomEventReward>
		{
			public int Compare(CustomEventReward x, CustomEventReward y)
			{
				return 0;
			}
		}

		public class UnitOrder : IComparer<CustomEventUnit>
		{
			public int Compare(CustomEventUnit x, CustomEventUnit y)
			{
				return 0;
			}
		}

		public static readonly RewardOrder rewardOrder;

		public static readonly UnitOrder unitOrder;

		public static bool isWorldUnit(CustomEventUnit unitInfo)
		{
			return false;
		}

		public static bool isPostRewardUnit(CustomEventUnit unitInfo)
		{
			return false;
		}

		public static bool isClear(CustomEventUser userInfo)
		{
			return false;
		}

		public static bool isNew(CustomEventUnit unitInfo, CustomEventUser userInfo)
		{
			return false;
		}

		public static bool canGetReward(CustomEventUnit unitInfo, CustomEventUser userInfo)
		{
			return false;
		}

		public static bool isClear(CustomEventReward rewardInfo, List<ulong> rewardSrls)
		{
			return false;
		}

		public static bool canGiveReward(CustomEventReward rewardInfo, int recordValue)
		{
			return false;
		}

		public static PrefabInstance GetPrefabInstance(Transform parent, GameObject prefab, string instName, Vector3 localPosition, Vector3 scale, int depth)
		{
			return null;
		}

		public static void ActivePrefabInstance(Transform parent, string instName)
		{
		}

		public static void UpdateRewardGood(Transform parent, RewardInfo reward, Vector3 localPosition, Vector3 scale, int depth, Action<GoodUI> showTooltip)
		{
		}

		public static void UpdateRewardItem(Transform parent, RewardInfo reward, Vector3 localPosition, Vector3 scale, int depth, Action<ItemUI> showTooltip)
		{
		}

		public static void UpdateRewardRune(Transform parent, RewardInfo reward, Vector3 localPosition, Vector3 scale, int depth, Action<RuneUI> showTooltip)
		{
		}

		public static void ShowRewardPopup()
		{
		}

		public static CustomEventContentsType BattleModeToContentType(EnumData.BattleMode battleMode, bool isRaidUser = true)
		{
			return CustomEventContentsType.instance;
		}
	}
}
