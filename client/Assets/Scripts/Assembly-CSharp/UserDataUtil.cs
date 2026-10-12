using System;
using System.Collections;
using System.Diagnostics;
using Caocao;

public class UserDataUtil
{
	private static PermanentData ms_pd;

	public static ulong Side2UId(UnitSide side)
	{
		return 0uL;
	}

	public static ulong AddNewUserUnit(UserUnits userUnits, UserItems userItems, ulong uid, int unitId, string nickName, int unitJobLevel, int unitLevel, int skinItemId = 0)
	{
		return 0uL;
	}

	public static UserItem[] MakeAutoEquipItem(UserItems userItems, ulong uid, int unitId, int unitLevel)
	{
		return null;
	}

	public static int GetAutoEquipItemByLevel(AutoEquip autoEquip, int unitLevel)
	{
		return 0;
	}

	public static ulong MakeNewEquipable(UserItems userItems, int itemId, int level, int enhanceLevel = 0)
	{
		return 0uL;
	}

	public static ulong AddEquipableItem(UserItems userItems, int itemId, int itemLevel)
	{
		return 0uL;
	}

	public static int GetItemLvByUnitLv(int unitLevel, int itemId)
	{
		return 0;
	}

	public static void RequestOpponentUserData(ulong opponentId, Action callBack, Action failedCallback = null)
	{
	}

	[DebuggerHidden]
	public static IEnumerator RequestOpponentUserDataWithBattleMode(ulong opponentId, EnumData.BattleMode battleMode, int warId, Action callBack)
	{
		return null;
	}

	public static MatchPreset CheckTutorialMatchPreset(int townId)
	{
		return null;
	}

	[DebuggerHidden]
	public static IEnumerator RequestPVPNPCOpponentUserData(ulong opponentId, int warId, Action callBack)
	{
		return null;
	}
}
