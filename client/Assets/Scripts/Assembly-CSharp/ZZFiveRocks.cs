using System.Collections.Generic;
using System.Runtime.InteropServices;
using System.Text;
using CaoCaoIAB;
using CodeStage.AntiCheat.ObscuredTypes;

public static class ZZFiveRocks
{
	private enum Category
	{
		OnceADay = 0,
		Battle = 1,
		Currency = 2,
		Treasure = 3,
		Item = 4,
		General = 5,
		Training = 6,
		City = 7,
		Mission = 8,
		Purchase = 9,
		Shop = 10
	}

	private enum Event
	{
		Login = 0,
		EnterWorlmap = 1,
		Battle = 2,
		Siege = 3,
		Annihilation = 4,
		ThousandBattle = 5,
		Commodity = 6,
		Gotcha = 7,
		TreasurePurchase = 8,
		UseItem = 9,
		GetItem = 10,
		ManufactureItem = 11,
		GetGeneral = 12,
		Training = 13,
		City = 14,
		Mission = 15,
		Purchase = 16,
		Materials = 17,
		Materials_snapshot = 18,
		GoodsShop = 19,
		Unionshop = 20,
		Enhance = 21,
		Exchange = 22
	}

	[StructLayout((LayoutKind)0, Size = 96)]
	private struct UserGoodsInfo
	{
		public ObscuredInt freeGold;

		public ObscuredInt chargedGold;

		public ObscuredInt gold;

		public ObscuredInt food;

		public ObscuredInt silver;

		public ObscuredInt stamp;

		public ObscuredInt fame;

		public ObscuredInt alliencePt;

		public void Clear()
		{
		}
	}

	private struct UserCraftItemInfo
	{
		private int[] ids;

		private Dictionary<ObscuredInt, ObscuredInt> id2amount;

		public UserCraftItemInfo(int[] ids)
		{
			this.ids = null;
			id2amount = null;
		}

		public void Clear()
		{
		}

		public void UpdateAmount(int id, int amount)
		{
		}

		public int GetAmount(int id)
		{
			return 0;
		}
	}

	private static List<string> strs;

	private static List<int> ints;

	private static string enterWorldmapTrackKey;

	private static List<string> strs2;

	private static List<int> ints2;

	private static List<string> treasureStrs;

	private static string str_consumption;

	private static string str_gain;

	private static StringBuilder stringBuilder;

	private static UserGoodsInfo backupUserGoodsInfo;

	private static int[] craftItemIds;

	private static List<int> currentCraftItemAmounts;

	private static UserCraftItemInfo backupUserCraftItemInfo;

	private static string deviceId
	{
		get
		{
			return null;
		}
	}

	private static string authId
	{
		get
		{
			return null;
		}
	}

	public static void TrackLogin()
	{
	}

	public static void TrackEnterWorldmap(List<int> lostTownIds, List<int> riotOccurredTownIds)
	{
	}

	private static void _buildBattleFinishCommonNums()
	{
	}

	private static void _buildBattleCommonStrs()
	{
	}

	public static void TrackBattleEnter()
	{
	}

	public static void TrackBattleFinish(bool win, int rankPoint = -1)
	{
	}

	private static void _buildBattleSiegeCommonStrs(int townId)
	{
	}

	public static void TrackBattleSiegeEnter(int townId)
	{
	}

	public static void TrackBattleSiegeFinish(bool win, int townId)
	{
	}

	public static void TrackAnnihilationStart()
	{
	}

	public static void TrackAnnihilationEnd(bool win, int gainPoint)
	{
	}

	public static void TrackThousandBattleStart()
	{
	}

	public static void TrackThousandBattleEnd(bool win)
	{
	}

	public static void TrackThousandBattleUnitRecover()
	{
	}

	public static void TrackCommodity(EnumData.GoodsType goodsType, string cause, int amount, bool isConsumtion)
	{
	}

	public static void TrackGotcha(string openCostType, int boxId, EnumData.TreasureBoxItemType resultType, int resultId, int resultAmount)
	{
	}

	public static void TrackPurchaseTreasure(int treasureId)
	{
	}

	public static void TrackUseItemInBattle(int itemId)
	{
	}

	public static void TrackGetEquipment(ulong itemSrl)
	{
	}

	public static void TrackManufactureItem(int itemId)
	{
	}

	public static void TrackManufactureItem(ulong itemSrl)
	{
	}

	private static void TrackMaterial(int itemId, string cause, bool isConsumption, int amount)
	{
	}

	private static void TrackMaterialSnapshot(List<int> itemAmounts)
	{
	}

	public static void TrackEnhance(UserItem treasure, bool enhanced, int deltaLevel, bool useKeeper, int silverCost)
	{
	}

	public static void TrackExchange(int ticketId, string cause, int amount, bool isConsumption)
	{
	}

	public static void TrackGetGeneral(int getUnitId)
	{
	}

	public static void TrackTraining(int str, int wis, int cha, int dex, int luck, int exp)
	{
	}

	public static void TrackCity(int stateId)
	{
	}

	public static void TrackMission(int commonMissionId)
	{
	}

	public static void TrackGoodsShop(GoodsShopItemInfo goodsShopItemInfo, int befFreeGold, int befChargedGold)
	{
	}

	public static void TrackUnionShop(GuildShop guildShopPd, int amount)
	{
	}

	private static void TrackEvent(Category category, Event eventName, List<string> _strs, List<int> _ints)
	{
	}

	private static string BuildParameter2(List<string> _strs, List<int> _ints)
	{
		return null;
	}

	private static void AppendStringParams(List<string> _strs)
	{
	}

	private static void AppendIntParams(List<int> _ints)
	{
	}

	public static void SendFunnelEvent(int group, int idx)
	{
	}

	public static void SetUserCustomCohort(EnumData.ZZCohort cohort, string value)
	{
	}

	public static void TrackPurchase(SkuDetails skuDetails)
	{
	}

	public static void BackUpUserData(UserData userData)
	{
	}

	public static void TrackEventByUserData(string uri)
	{
	}
}
