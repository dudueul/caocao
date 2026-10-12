using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Runtime.InteropServices;
using System.Text;
using System.Text.RegularExpressions;
using Caocao;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;
using Thingsoft.Pool;
using UnityEngine;

public static class Util
{
	public enum InputPriority
	{
		NetworkIndicator = 0,
		UrgentPopup = 1,
		LoadingProcess = 2,
		GamePaused = 3,
		NetworkProcess = 4,
		Normal = 5
	}

	private class JosaPair
	{
		public string josa1 { get; private set; }

		public string josa2 { get; private set; }

		public JosaPair(string josa1, string josa2)
		{
		}
	}

	private class JosaPattern
	{
		public string josa1 { get; private set; }

		public string josa2 { get; private set; }

		public string pattern { get; private set; }

		public JosaPattern(string pattern, string josa1, string josa2)
		{
		}

		public JosaPattern(string josa1, string josa2)
		{
		}
	}

	[StructLayout((LayoutKind)0, Size = 2)]
	private struct CharRange
	{
		public byte start;

		public byte end;

		public CharRange(byte start, byte end)
		{
			this.start = 0;
			this.end = 0;
		}

		public CharRange(byte oneRange)
		{
			start = 0;
			end = 0;
		}

		public bool Contains(char src)
		{
			return false;
		}
	}

	[StructLayout((LayoutKind)0, Size = 8)]
	private struct JosaMatch
	{
		public int idx;

		public int josaIdx;

		public JosaMatch(int idx, int josaIdx)
		{
			this.idx = 0;
			this.josaIdx = 0;
		}
	}

	public static StringBuilder ms_sb;

	public static Color32 white;

	public static Color32 gray;

	public static Color32 fairRuledColor;

	public static Color32 yellowNameColor;

	private static readonly string[] str_weatherName;

	private static Transform ms_overlayUiRootTrsf;

	private static Transform ms_uiRootTrsf;

	private static UICamera ms_uiCameraScript;

	private static List<Func<int, int, bool>> ms_availableCheckFuncs;

	private static Dictionary<IntVector2, int> ms_getItemTierTypeIdCache;

	private static string ms_getItemTierTypeIdCacheVersion;

	private static Dictionary<IntVector2, UnitJobLv> ms_getUnitJobLvCache;

	private static string ms_getUnitJobLvCacheVersion;

	private static Vector2 worldmapCenter;

	private static GameObject checkPopup;

	private static Dictionary<InputPriority, bool> ms_inputStates;

	private static Dictionary<GameObject, Stack<int>> ms_maskStack;

	private static Regex specialRegex;

	private static Regex josaRegex;

	private static Dictionary<string, JosaPair> josaPatternPaird;

	private static CharRange[] spetialCharRanges;

	private static List<JosaPattern> josaPatterns;

	private static List<JosaMatch> josaMatches;

	private static StringBuilder ms_sbForReplaceJosa;

	private static int ms_seed;

	private static List<EnumData.Locale> supportedLanguage;

	private static Dictionary<string, EnumData.Locale> m_languages;

	private static string m_publicIp;

	public static string COMPETITION_FRIENDLY_MAP_ID_KEY
	{
		get
		{
			return null;
		}
	}

	public static string COMPETITION_FRIENDLY_MODE_ID_KEY
	{
		get
		{
			return null;
		}
	}

	public static string GetWeatherName(Caocao.Weather weatherID)
	{
		return null;
	}

	public static string GetWeatherSpriteName(Caocao.Weather weatherID)
	{
		return null;
	}

	public static DateTime UnixTimeStampToDateTime(double unixTimeStamp)
	{
		return default(DateTime);
	}

	public static IntVector2 convertAreaIdx2GridPos(IntVector2 baseGridPos, IntVector2 areaDimension, IntVector2 areaIdx)
	{
		return default(IntVector2);
	}

	public static int convertAreaIdx2GridPos(int baseGridPos, int areaDimension, int areaIdx)
	{
		return 0;
	}

	public static IntVector2 convertGridPos2AreaIdx(IntVector2 baseGridPos, IntVector2 areaDimension, IntVector2 areaIdx)
	{
		return default(IntVector2);
	}

	public static int convertGridPos2AreaIdx(int baseGridPos, int areaDimension, int areaIdx)
	{
		return 0;
	}

	public static bool IsInside(IntVector2 areaIdx, IntVector2 areaDimension)
	{
		return false;
	}

	public static bool IsInside(int x, int y, IntVector2 areaDimension)
	{
		return false;
	}

	public static bool IsOutside(IntVector2 areaIdx, IntVector2 areaDimension)
	{
		return false;
	}

	public static bool IsBound(int value, IntVector2 range)
	{
		return false;
	}

	public static bool IsBound(Vector3 min, Vector3 max, Vector3 pos)
	{
		return false;
	}

	public static bool IsBound(IntVector2 p, IntRect area)
	{
		return false;
	}

	public static bool IsBound(int x, int y, IntRect area)
	{
		return false;
	}

	public static string setKrSuffix(string name, int type)
	{
		return null;
	}

	public static Comparison<UserUnit> GetSortingLevelAndTileJobDefensePositive(EnumData.SortType type, bool reverse, int tileId)
	{
		return null;
	}

	public static Comparison<UserUnit> GetSortingTileJobDefensePositive(EnumData.SortType type, bool reverse, int tileId = 0)
	{
		return null;
	}

	public static Comparison<UserUnit> GetSortingFunc(EnumData.SortType type)
	{
		return null;
	}

	public static string GetSortingLabel(EnumData.SortType type)
	{
		return null;
	}

	public static string GetSortingValue(UserUnit userUnit, EnumData.SortType type)
	{
		return null;
	}

	public static Comparison<UserUnit> GetGuildWarSortingFunc(EnumData.SortType type)
	{
		return null;
	}

	public static string GetGuildWarSortingValue(UserUnit userUnit, EnumData.SortType type)
	{
		return null;
	}

	public static string GetInventorySortingLabel(EnumData.InvenItemSortType type)
	{
		return null;
	}

	public static EnumData.ItemSlotType ItemSortTypeToSlotType(EnumData.SortType sortType)
	{
		return (EnumData.ItemSlotType)0;
	}

	public static Comparison<UserItem> GetInvenItemSortingFunc(EnumData.InvenItemSortType type, bool descending)
	{
		return null;
	}

	public static Comparison<GuildMember> GetGuildMemberSortingFunc(EnumData.GuildMemberSortType type)
	{
		return null;
	}

	public static string GetGuildMemberSortingValue(GuildMember member, EnumData.GuildMemberSortType type, out bool translation)
	{
		translation = default(bool);
		return null;
	}

	public static string GetGuildMemberSortingColumnName(EnumData.GuildMemberSortType type)
	{
		return null;
	}

	public static string GetGuildJobDesc(EnumData.GuildJopType type)
	{
		return null;
	}

	public static string GetGuildPermissionDesc(EnumData.GuildPermission type)
	{
		return null;
	}

	public static string GetSortingValue(UserUnit userUnit, EnumData.ChangeMayorSortFilter type)
	{
		return null;
	}

	public static int SortTownMayorEffectBonusValue(int townId, UserUnit mayorUnit)
	{
		return 0;
	}

	public static int GetTownMayorEffectBonus(UserUnit mayorUnit, int[] types, int[] effectRanges)
	{
		return 0;
	}

	public static int SortLordEffectBonusValue(UserUnit lordUnit)
	{
		return 0;
	}

	public static int GetLordEffectBonus(UserUnit userUnit, int[] types, int[] effectRanges)
	{
		return 0;
	}

	public static string GetItemStatLabel(EnumData.StatType type)
	{
		return null;
	}

	public static int GetExchangeGold(int value, float goldRate)
	{
		return 0;
	}

	public static float ExchangeGoldRate(EnumData.GoodsType goodsType, int amount)
	{
		return 0f;
	}

	public static float ExchangeGoldRateBySilver(int silver)
	{
		return 0f;
	}

	public static float ExchangeGoldRateByFood(int food)
	{
		return 0f;
	}

	public static float ExchangeGoldRateByHonor(int honor)
	{
		return 0f;
	}

	public static float GetJobTypeFxOffset(int unitId)
	{
		return 0f;
	}

	public static string Commify(this int number)
	{
		return null;
	}

	public static string Commify(this ulong number)
	{
		return null;
	}

	public static int Percent(this int number, int pct)
	{
		return 0;
	}

	public static bool IsInRange(this int number, int minIncluded, int maxExcluded)
	{
		return false;
	}

	public static int GetPassiveIndex(List<ObscuredInt> passives, int spAction)
	{
		return 0;
	}

	public static void SetItemStat(UILabelLocalized label, int itemID, int itemLevel, int lineNumLimit)
	{
	}

	public static string GetItemTypeDesc(int enumItemType)
	{
		return null;
	}

	public static string GetItemFilterTypeDesc(EnumData.ItemFilterType filterType)
	{
		return null;
	}

	public static string GetItemSlotTypeDesc(EnumData.ItemSlotType slotType)
	{
		return null;
	}

	public static bool IsHealHPConsumeItemType(int itemType)
	{
		return false;
	}

	public static bool IsHealHPConsumeItemType(EnumData.ItemType itemType)
	{
		return false;
	}

	public static bool IsHealMPConsumeItemType(int itemType)
	{
		return false;
	}

	public static bool IsHealMPConsumeItemType(EnumData.ItemType itemType)
	{
		return false;
	}

	public static string GetItemJobTypes(int itemId, int currJobType)
	{
		return null;
	}

	public static bool IsAvailableItemWithItemId(UserUnit userUnit, int itemId)
	{
		return false;
	}

	private static bool IsAvailableItemWithItemType(JobType jobType, int itemType)
	{
		return false;
	}

	public static int GetJobId(int jobTypeId, int jobLevel)
	{
		return 0;
	}

	public static int GetStartUnitLvByJobLv(JobType jobTypeData, int jobLevel)
	{
		return 0;
	}

	public static int GetEndUnitLvByJobLv(JobType jobTypeData, int jobLevel)
	{
		return 0;
	}

	public static bool IsJobTypeGroup(int jobType, EnumData.JobTypeGroup group)
	{
		return false;
	}

	public static float GetMultiTargetSuccessRateCorrection(int attackUnitJobType, bool isBaseTarget)
	{
		return 0f;
	}

	public static bool IsMountTile(int tileId)
	{
		return false;
	}

	public static bool IsForestTile(int tileId)
	{
		return false;
	}

	public static bool IsGentleRiver(int tileId)
	{
		return false;
	}

	public static bool IsAvailableScenario(int scenarioId, EnumData.ScenarioModeLevel scenarioModeLevel)
	{
		return false;
	}

	public static bool CheckAmbitionCAOCAO(Scenario scenario)
	{
		return false;
	}

	public static string GetLastPlayScenarioSaveKey()
	{
		return null;
	}

	public static Scenario GetScenarioFromBattleMapId(int battleMapId)
	{
		return null;
	}

	public static Instance GetInscanceFromBattleMapId(int battleMapId)
	{
		return null;
	}

	public static Transform GetOverlayUiRoot()
	{
		return null;
	}

	public static Transform GetUIRoot()
	{
		return null;
	}

	public static GameObject GetUIObjectOnRoot(string name)
	{
		return null;
	}

	public static UICamera GetUICamera()
	{
		return null;
	}

	public static Bounds GetBoundsOnUI(string relativePath)
	{
		return default(Bounds);
	}

	public static Bounds GetBoundsOnUI(Transform targetTrsf)
	{
		return default(Bounds);
	}

	private static Bounds GetColliderBound(Transform tf)
	{
		return default(Bounds);
	}

	public static void ModifyCollierVolume(Transform tf, Vector2 vol)
	{
	}

	public static Vector3 GetPositionInUIRoot(Transform transf)
	{
		return default(Vector3);
	}

	public static Rect ConvertBounds2Rect(Bounds bounds)
	{
		return default(Rect);
	}

	public static Transform GetTransform(string relativePath)
	{
		return null;
	}

	public static string GetDeviceId()
	{
		return null;
	}

	public static bool DidCompleteAsyncLoad()
	{
		return false;
	}

	public static Transform InstantiateUI(GameObject uiPrefab)
	{
		return null;
	}

	public static void SetGoodsText(this UILabelLocalized targetUI, EnumData.GoodsType goodsType, int value)
	{
	}

	public static void SetItemIconsText(this UILabelLocalized targetUI, int itemID, int value)
	{
	}

	public static void Swap<T>(ref T lhs, ref T rhs)
	{
	}

	public static void Swap<T>(IList<T> list, int indexA, int indexB)
	{
	}

	public static TroopStat GetItemBaseStat(int itemId, int level)
	{
		return default(TroopStat);
	}

	public static int GetTreasurePassiveUnlockLevel(int index)
	{
		return 0;
	}

	public static string GetPassiveName(int passiveId, int val)
	{
		return null;
	}

	public static string GetPassiveName(Passive passive, int val)
	{
		return null;
	}

	public static string GetPassiveNameByList(int passiveListId)
	{
		return null;
	}

	public static string GetPassiveType(int passiveListId)
	{
		return null;
	}

	public static string GetPassiveDesc(int passiveId, int val)
	{
		return null;
	}

	public static string GetPassiveDesc(Passive passive, int val)
	{
		return null;
	}

	public static string GetPassiveDesc(int passiveListId, bool useShortDesc = false)
	{
		return null;
	}

	public static string GetPassiveDesc(int passiveId, int val, int triggerRateValue, int conditionVal1, int conditionVal2, bool useShortDesc = false, bool showHandicap = false)
	{
		return null;
	}

	public static string GetTreasureEnhancePassiveName(int passiveTriggerId)
	{
		return null;
	}

	public static string GetTreasureEnhancePassiveType(int passiveTriggerId)
	{
		return null;
	}

	public static string GetTreasureEnhancePassiveDesc(int passiveTriggerId, bool userShortDesc = false)
	{
		return null;
	}

	public static TreasureEnhancePassive GetRandomTreasureEnhancePassive(int tier, int enhanceType, int unlockLevel, int ignore)
	{
		return null;
	}

	public static TroopStat GetTreasureStatWithEnhance(int itemId, int level)
	{
		return default(TroopStat);
	}

	public static TroopStat GetTreasurerEnhanceStat(int itemId, int level)
	{
		return default(TroopStat);
	}

	public static int GetTreasureGrowPassiveListId(int itemId, int level)
	{
		return 0;
	}

	public static string GetTreasureGrowPassiveDeltaString(int itemTier, int enhanceType, int curLevel, int changedLevel)
	{
		return null;
	}

	public static string GetPassiveIcon(int passiveId, int val)
	{
		return null;
	}

	public static string GetPassiveIcon(Passive passive, int val)
	{
		return null;
	}

	public static K GetKey<K, V>(this IDictionary<K, V> instance, V value)
	{
		return default(K);
	}

	public static List<K> GetKeys<K, V>(this IDictionary<K, V> instance, V value)
	{
		return null;
	}

	public static bool TryGetKey<K, V>(this IDictionary<K, V> instance, V value, out K key)
	{
		key = default(K);
		return false;
	}

	public static void StackCount<K>(this IDictionary<K, int> instance, K key, int value)
	{
	}

	public static int GetLineCountInString(string text)
	{
		return 0;
	}

	public static Transform FindSibling(this Transform t, string name)
	{
		return null;
	}

	public static void InitLocalTransform(this Transform t)
	{
	}

	public static GameObject Instantiate(this GameObject prefab)
	{
		return null;
	}

	public static GameObject InstantiateOnWithoutTutorial(this GameObject prefab, Transform parent, bool preserveLocalPosition = false, string targetName = "", bool preserveLocalScale = false)
	{
		return null;
	}

	public static GameObject InstantiateOn(this GameObject prefab, Transform parent, bool preserveLocalPosition = false, string targetName = "", bool preserveLocalScale = false)
	{
		return null;
	}

	public static bool IsTutorialMode()
	{
		return false;
	}

	public static T InstantiateOn<T>(this GameObject prefab, Transform parent, bool preserveLocalPosition = false, string targetName = "") where T : MonoBehaviour
	{
		return null;
	}

	public static GameObject InstantiateOnOverlayUiRoot(this GameObject prefab, bool preserveLocalPosition = false, string targetName = "")
	{
		return null;
	}

	public static GameObject InstantiateOnUIRoot(this GameObject prefab, bool preserveLocalPosition = false, string targetName = "", bool preserveLocalScale = false)
	{
		return null;
	}

	public static T InstantiateOnUIRoot<T>(this GameObject prefab, bool preserveLocalPosition = false, string targetName = "") where T : MonoBehaviour
	{
		return null;
	}

	public static GameObject InstantiateOnTopGoods(this GameObject prefab, EnumData.GoodsType goodsType)
	{
		return null;
	}

	public static void ShowHarvestAll(int sumFood, int sumSilver)
	{
	}

	public static int GetPrimaryStat(this Item item)
	{
		return 0;
	}

	public static int GetData(this JsonData jsonData, string key, int defaultValue)
	{
		return 0;
	}

	public static double GetData(this JsonData jsonData, string key, double defaultValue)
	{
		return 0.0;
	}

	public static long GetData(this JsonData jsonData, string key, long defaultValue)
	{
		return 0L;
	}

	public static ulong GetData(this JsonData jsonData, string key, ulong defaultValue)
	{
		return 0uL;
	}

	public static string GetData(this JsonData jsonData, string key, string defaultValue)
	{
		return null;
	}

	public static bool GetData(this JsonData jsonData, string key, bool defaultValue)
	{
		return false;
	}

	public static JsonData GetData(this JsonData jsonData, string key, JsonData defaultValue)
	{
		return null;
	}

	public static DateTime GetData(this JsonData jsonData, string key, DateTime defaultValue)
	{
		return default(DateTime);
	}

	public static T GetData<T>(this JsonData jsonData, string key, T defaultValue) where T : struct, IConvertible, IFormattable, IComparable
	{
		return default(T);
	}

	public static ObscuredInt GetData(this JsonData jsonData, string key, ObscuredInt defaultValue)
	{
		return default(ObscuredInt);
	}

	public static ObscuredDouble GetData(this JsonData jsonData, string key, ObscuredDouble defaultValue)
	{
		return default(ObscuredDouble);
	}

	public static ObscuredLong GetData(this JsonData jsonData, string key, ObscuredLong defaultValue)
	{
		return default(ObscuredLong);
	}

	public static ObscuredULong GetData(this JsonData jsonData, string key, ObscuredULong defaultValue)
	{
		return default(ObscuredULong);
	}

	public static ObscuredString GetData(this JsonData jsonData, string key, ObscuredString defaultValue)
	{
		return null;
	}

	public static ObscuredBool GetData(this JsonData jsonData, string key, ObscuredBool defaultValue)
	{
		return default(ObscuredBool);
	}

	public static void Swap<T>(this T a, T b)
	{
	}

	public static string GetEnumDescription(Enum value)
	{
		return null;
	}

	public static List<string> EnumToStringList<T>()
	{
		return null;
	}

	public static void WriteProperty(this JsonWriter writer, string key, bool value)
	{
	}

	public static void WriteProperty(this JsonWriter writer, string key, decimal value)
	{
	}

	public static void WriteProperty(this JsonWriter writer, string key, double value)
	{
	}

	public static void WriteProperty(this JsonWriter writer, string key, int value)
	{
	}

	public static void WriteProperty(this JsonWriter writer, string key, long value)
	{
	}

	public static void WriteProperty(this JsonWriter writer, string key, string value)
	{
	}

	public static void WriteProperty(this JsonWriter writer, string key, List<int> value)
	{
	}

	public static void WriteProperty(this JsonWriter writer, string key, List<ObscuredInt> value)
	{
	}

	public static EnumData.StringValidation CheckString(this string str, int length, bool chat)
	{
		return EnumData.StringValidation.Empty;
	}

	public static bool IsVaildMarketLanguage(string input)
	{
		return false;
	}

	public static string GetSpecialCharacters()
	{
		return null;
	}

	public static int CountAsNarrow(string str)
	{
		return 0;
	}

	public static int CountAsNarrow(char character)
	{
		return 0;
	}

	public static string TruncateWithByteCount(this string str, int byteCount)
	{
		return null;
	}

	public static string Truncate(this string source, int maxLength, int maxLine)
	{
		return null;
	}

	[DebuggerHidden]
	public static IEnumerator WaitForRealSeconds(float time)
	{
		return null;
	}

	public static int GetMaterialCount(Mix mix, int itemID)
	{
		return 0;
	}

	public static void SetBoxColliderOffset(this BoxCollider boxCollider, float offset)
	{
	}

	public static DateTime GetDateTime(string yyyymmddString)
	{
		return default(DateTime);
	}

	public static DayOfWeek CalcDayOfWeek(this DateTime date)
	{
		return DayOfWeek.Sunday;
	}

	public static DateTime GetGMTTime(this DateTime date)
	{
		return default(DateTime);
	}

	public static T GetSpecificComponent<T>(this Transform tf)
	{
		return default(T);
	}

	public static T GetSpecificComponent<T>(this Transform tf, string childName)
	{
		return default(T);
	}

	public static GameObject FindGameObject(this Transform tf, string childName)
	{
		return null;
	}

	public static void SetUILabel(this Transform tf, string message)
	{
	}

	public static void SetUILabel(this Transform tf, string message, params object[] args)
	{
	}

	public static void SetUILabelColor(this Transform tf, Color color)
	{
	}

	public static void SetUILabel(this Transform tf, Color color, string message, params object[] args)
	{
	}

	public static void SetUISprite(this Transform tf, string texture)
	{
	}

	public static void SetUITexture(this Transform tf, Texture texture)
	{
	}

	public static void SetUIAtlas(this Transform tf, string atlasPath, string texture)
	{
	}

	public static void SetUISpriteColor(this Transform tf, Color color)
	{
	}

	public static void SetLabelColor(this Transform tf, Color color)
	{
	}

	public static void SetButtonEnable(this Transform tf, bool isEnable)
	{
	}

	public static void SetButtonEnable(this UIButton btn, bool isEnable, bool immediate = true)
	{
	}

	public static void SetButtonEnableColor(this UIButton btn, bool isEnable, bool immediate = true)
	{
	}

	public static void SetButtonClick(this Transform tf, EventDelegate.Callback callback)
	{
	}

	public static void SetButtonClick(this GameObject obj, EventDelegate.Callback callback)
	{
	}

	public static void SetObjectVisible(this Transform tf, bool isActive)
	{
	}

	public static void SetUIGauge(this Transform tf, float val)
	{
	}

	public static void SendFsmEvent(Transform tf, string action)
	{
	}

	public static void ClearChildren(this Transform tf)
	{
	}

	public static bool IsInBoundary<T>(List<T> list, T target)
	{
		return false;
	}

	public static int GetValueAsPercent(int max, int percent)
	{
		return 0;
	}

	public static string GetTownCategoryDesc(int townId)
	{
		return null;
	}

	public static string GetTownGradeDesc(EnumData.TownGrade grade)
	{
		return null;
	}

	public static EnumData.CountryCode GetCountryCode()
	{
		return (EnumData.CountryCode)0;
	}

	public static EnumData.PlatformType GetPlatformType()
	{
		return (EnumData.PlatformType)0;
	}

	public static bool IsValidCountryCode(EnumData.CountryCode countryCode)
	{
		return false;
	}

	public static int GetMarketCode(string marketName)
	{
		return 0;
	}

	public static bool IsValidMarket(int marketFlag, string marketName)
	{
		return false;
	}

	public static int GetVoiceLanguageIndex()
	{
		return 0;
	}

	public static int GetCountryCodeIndex()
	{
		return 0;
	}

	public static string GetLocaleTo2letter(EnumData.Locale locale)
	{
		return null;
	}

	public static string GetCountryCodeTo2letter(bool needLower = false)
	{
		return null;
	}

	public static string GetCountryCodeTo3letter()
	{
		return null;
	}

	public static EnumData.Locale GetDeviceLangauge()
	{
		return EnumData.Locale.NONE;
	}

	public static EnumData.Locale GetVoiceLanguageByCountryCode()
	{
		return EnumData.Locale.NONE;
	}

	public static List<string> GetSupportNations()
	{
		return null;
	}

	public static List<string> GetSupportLanguages()
	{
		return null;
	}

	public static List<string> GetSupportVoiceLanguages()
	{
		return null;
	}

	private static EnumData.Locale GetDefaultVoiceLanguage()
	{
		return EnumData.Locale.NONE;
	}

	public static EnumData.Locale GetLanguageByFullLanguageName(string fullLanguageName)
	{
		return EnumData.Locale.NONE;
	}

	public static string GetFullLanguageName(EnumData.Locale language)
	{
		return null;
	}

	public static int GetAutoProcessDefaultSetting()
	{
		return 0;
	}

	public static List<int> GetRewardID(EnumData.RewardType rType, int id, int battleAvgUnitLev = 1)
	{
		return null;
	}

	public static bool IsEquip(this Item item)
	{
		return false;
	}

	public static bool IsBattleItem(this Item item)
	{
		return false;
	}

	public static bool IsTreasure(EnumData.ItemSlotType slot)
	{
		return false;
	}

	public static int GetGoodFromReward(int rewardID, EnumData.GoodsType goodType)
	{
		return 0;
	}

	public static int GetGoodFromSetGood(int setGoodID, EnumData.GoodsType goodType)
	{
		return 0;
	}

	public static int GetGoodFromSetItem(int setItemID, int itemID)
	{
		return 0;
	}

	public static List<BattleMapInfo> GetBattleMapInfoWithTreasureID(int itemID)
	{
		return null;
	}

	public static List<BattleMapInfo> GetBattleMapInfoWithItemID(int itemID)
	{
		return null;
	}

	private static List<SetItem> GetSetItemListByItemID(int itemID)
	{
		return null;
	}

	private static void AddRewardListContains(List<Reward> rewardList, List<SetItem> setItems)
	{
	}

	private static void AddBattleMapInfosWithRewardID(List<BattleMapInfo> battleMapInfoList, int rewardID)
	{
	}

	private static void AddBattleMapInfoFromScenarios(List<BattleMapInfo> battleMapInfoList, int rewardID)
	{
	}

	private static void AddBattleMapInfoFromTowns(List<BattleMapInfo> battleMapInfoList, int rewardID)
	{
	}

	private static void AddBattleMapInfoFromInstances(List<BattleMapInfo> battleMapInfoList, int rewardID)
	{
	}

	private static void AddBattleMapInfoFromInstancesMajorReward(List<BattleMapInfo> battleMapInfoList, int itemId)
	{
	}

	private static List<EventReward> GetBattleMapInfoFromEventItems(List<Reward> rewardList)
	{
		return null;
	}

	private static void AddBattleMapInfosWithMapID(List<BattleMapInfo> battleMapInfoList, int mapID)
	{
	}

	private static void AddBattleMapInfoFromScenariosWithMapID(List<BattleMapInfo> battleMapInfoList, int mapID)
	{
	}

	private static void AddBattleMapInfoFromTownsWithMapID(List<BattleMapInfo> battleMapInfoList, int mapID)
	{
	}

	private static void AddBattleMapInfoFromInstancesWithMapID(List<BattleMapInfo> battleMapInfoList, int mapID)
	{
	}

	private static bool IsAvailableRewardWithType(int targetRewardID, int type, int rewardTypeID)
	{
		return false;
	}

	private static int GetRewardIDFromRewardRank(int rewardID, int rank)
	{
		return 0;
	}

	private static int GetRewardIDFromRewardVip(int rewardID, int vip)
	{
		return 0;
	}

	private static bool ContatinsRewardIDs(int rewardID, int targetReward)
	{
		return false;
	}

	private static List<int> GetRewardIDFromRewardLv(int rewardID, int level = 0)
	{
		return null;
	}

	public static bool IsMaxTraining(UserUnit userUnit)
	{
		return false;
	}

	private static void _GenerateItemTierTypeIdCache()
	{
	}

	private static int _GetItemTierTypeId(int tierLevel, int itemType)
	{
		return 0;
	}

	public static Item GetItemWithTier(int tierLevel, int itemType)
	{
		return null;
	}

	public static int GetItemIDWithTier(int tierLevel, int itemType)
	{
		return 0;
	}

	private static void _GenerategetUnitIdJobLvIdCache()
	{
	}

	private static UnitJobLv _GetgetUnitIdJobLvId(int unitId, int jobLv)
	{
		return null;
	}

	public static UnitJobLv GetUnitJobLevel(int unitId, int jobLevel)
	{
		return null;
	}

	public static string GetPassiveTypeString(int type)
	{
		return null;
	}

	public static List<Job> GetJobsByJobType(int jobType)
	{
		return null;
	}

	public static Vector3 GetScreenPosByPixelPos(IntVector2 pixelPos)
	{
		return default(Vector3);
	}

	public static Vector3 GetScreenPosByPixelPosWithZPos(IntVector2 pixelPos, float zpos)
	{
		return default(Vector3);
	}

	public static Vector2 GetPixelPosByScreenPos(Vector3 screenPos)
	{
		return default(Vector2);
	}

	public static UserSoldier GetSoldierInfo(ref UnitInfo unitInfo, int battleMapId, int slotId)
	{
		return null;
	}

	public static int GetUnitGenderByUnitId(int unitId)
	{
		return 0;
	}

	public static int GetUnitGenderByHeroId(int heroId)
	{
		return 0;
	}

	public static int GetUnitTreeNodeId(int unitId)
	{
		return 0;
	}

	public static int GetUnitTreeId(int unitId)
	{
		return 0;
	}

	public static List<int> GetUnitTreeUnitOrderByTierDesc(int unitTreeId)
	{
		return null;
	}

	public static int GetTownIdFromBattleMapId(int battleMapId)
	{
		return 0;
	}

	public static string GetCommonMoneySpriteName(EnumData.GoodsType goodsType)
	{
		return null;
	}

	public static string GetGoodsSpriteNameOfIconAtlas(EnumData.GoodsType goodsType)
	{
		return null;
	}

	public static string GetGoodsSpriteAtlasName(EnumData.GoodsType goodsType)
	{
		return null;
	}

	public static string GetGoodsSpriteNameOfItemAtlas1(EnumData.GoodsType goodsType)
	{
		return null;
	}

	public static string GetItemSpriteNameOfIconAtlas(int itemId)
	{
		return null;
	}

	public static string GetItemSpriteNameOfIconAtlas(EnumData.ItemType itemType, int itemId = 0)
	{
		return null;
	}

	public static string GetCommonMoneySpriteName(EnumData.ItemType itemType)
	{
		return null;
	}

	public static bool IsCountable(EnumData.ItemSlotType slotType)
	{
		return false;
	}

	public static string GetGoodsTypeName(EnumData.GoodsType type)
	{
		return null;
	}

	public static EnumData.GoodsOwner GetOwnerType(EnumData.GoodsType type)
	{
		return EnumData.GoodsOwner.User;
	}

	public static int GetOwnedGoods(EnumData.GoodsType goodsType)
	{
		return 0;
	}

	public static bool CanUseGoods(EnumData.GoodsType goodsType, int amount)
	{
		return false;
	}

	public static List<int> GetTreasureBoxesAsCoolTime()
	{
		return null;
	}

	public static DateTime GetTreasureBoxRemainTime()
	{
		return default(DateTime);
	}

	public static void SetDepthAboveParent(GameObject parent, GameObject child)
	{
	}

	public static void UpdateWUIidgetDepthRecursively(Transform t, int depth)
	{
	}

	public static string GetPath(this Transform t)
	{
		return null;
	}

	public static bool IsValidCloud(int cloudID)
	{
		return false;
	}

	public static float GetAspectMagic()
	{
		return 0f;
	}

	public static float GetForceZoomInMax()
	{
		return 0f;
	}

	public static float GetForceZoomOutMax()
	{
		return 0f;
	}

	public static float GetZoomInMax()
	{
		return 0f;
	}

	public static float GetZoomOutMax()
	{
		return 0f;
	}

	public static float GetZoomReturnValue()
	{
		return 0f;
	}

	public static float GetZoomInit()
	{
		return 0f;
	}

	public static void SetZoomSize(float data)
	{
	}

	public static string MakeRelative(string filePath, string referencePath)
	{
		return null;
	}

	public static string GetDefaultItemIcon(EnumData.ItemType itemType)
	{
		return null;
	}

	public static IntVector2 GetCastleSize(UserTown userTown)
	{
		return default(IntVector2);
	}

	public static int GetMinJobLvToGetPassive(int passiveId, int jobTypeId)
	{
		return 0;
	}

	public static int GetTotalScenarioMissionCount(int storybookId)
	{
		return 0;
	}

	public static bool IsEnding(int scenarioID)
	{
		return false;
	}

	public static int GetPassiveNeedLv(int passiveId, int unitId)
	{
		return 0;
	}

	public static int GetPassiveNeedJobLv(int passiveId, int unitId)
	{
		return 0;
	}

	public static int Lerp(int start, int end, float t)
	{
		return 0;
	}

	public static long Lerp(long start, long end, float t)
	{
		return 0L;
	}

	public static int CaculateAmountPlunder(UserTown userTown, EnumData.PlunderType type)
	{
		return 0;
	}

	public static void ShowDisconnectedErrorPopup(string msg = null)
	{
	}

	public static void ShowRetryRequestPopup(string reason)
	{
	}

	public static void GoToAppStore()
	{
	}

	public static void CheckLoadingUIPopup(GameObject popup)
	{
	}

	private static void OnTransitionEnded()
	{
	}

	public static void ShowPurchaseErrorPopup()
	{
	}

	public static void ShowTownInvadedPopup(MatchUp matchUp, Action<EnumData.townStatusType> callback)
	{
	}

	public static string GetTownInvadedMessage(UserTown userTown)
	{
		return null;
	}

	public static string GetTownInvadedMessage(MatchUp matchUp)
	{
		return null;
	}

	public static void ShowTownsFallenPopup(string msg, Action callback)
	{
	}

	public static void ShowGetRewardPopup(List<RewardInfo> rewards, Action callback, EnumData.RewardPopupMode mode)
	{
	}

	public static StringBuilder Clear(this StringBuilder sb)
	{
		return null;
	}

	public static int ConvertJobLevel2AppearanceLevel(int jobLevel)
	{
		return 0;
	}

	public static int GetIndexByLevel(List<ObscuredInt> levels, int level)
	{
		return 0;
	}

	public static int GetLordEffectValue(UserUnit userUnit)
	{
		return 0;
	}

	public static int GetMayorEffectValue(UserUnit userUnit)
	{
		return 0;
	}

	public static int GetMayorEffectBonusValue(UserUnit userUnit, int ddfCnt)
	{
		return 0;
	}

	public static int GetSilverPlunderBonus(UserUnit lordUnit, int state)
	{
		return 0;
	}

	public static int GetFoodPlunderBonus(UserUnit lordUnit, int state)
	{
		return 0;
	}

	public static int GetLeaderEffectValue(IUserDataBase userData, int townId, int[] types, int[] effectRanges)
	{
		return 0;
	}

	public static string GetLeaderEffectTypeDesc(int leaderEffectType)
	{
		return null;
	}

	public static string GetAIDescShort(int id)
	{
		return null;
	}

	public static int GoodTypeToItemID(EnumData.GoodsType goodType)
	{
		return 0;
	}

	public static bool IsSilverGoodsType(EnumData.GoodsType goodsType)
	{
		return false;
	}

	public static bool IsGoldGoodsType(EnumData.GoodsType goodsType)
	{
		return false;
	}

	public static void RestoreShader(this Material mat)
	{
	}

	public static void RestoreShader(this GameObject gameObject)
	{
	}

	public static UnitSide ConvertUnitSide(ObjectSide side)
	{
		return UnitSide.OWN;
	}

	public static Caocao.UnitGroup GetOpponentGroup(UnitSide side)
	{
		return Caocao.UnitGroup.OWNALLY;
	}

	public static bool IsMatch(this string source, string toCheck, RegexOptions options = RegexOptions.IgnoreCase)
	{
		return false;
	}

	public static bool IsMatchAny(this string[] source, string toCheck, RegexOptions options = RegexOptions.IgnoreCase)
	{
		return false;
	}

	public static bool EqualString(this string source, string toCheck, StringComparison stringComparison = StringComparison.OrdinalIgnoreCase)
	{
		return false;
	}

	public static bool EqualStringAny(this ObscuredString[] source, string toCheck, StringComparison stringComparison = StringComparison.OrdinalIgnoreCase)
	{
		return false;
	}

	public static bool EqualStringAny(this List<ObscuredString> source, string toCheck, StringComparison stringComparison = StringComparison.OrdinalIgnoreCase)
	{
		return false;
	}

	public static void IterateEnum<T>(Func<int, T> foreach_do) where T : struct, IComparable, IConvertible, IFormattable
	{
	}

	public static void AddOnClickHandler(this UIButton button, EventDelegate.Callback callback)
	{
	}

	public static UnitSide ForceFlag2UnitSide(ForceFlag flags)
	{
		return UnitSide.OWN;
	}

	public static void ForceFalgs2UnitSides(ForceFlag flags, UnitSides res)
	{
	}

	public static ForceFlag UnitSide2ForceFlags(UnitSide side)
	{
		return (ForceFlag)0;
	}

	public static ForceFlag ForceType2FoceFlags(EnumData.ForceType type)
	{
		return (ForceFlag)0;
	}

	public static ForceFlag GroupID2ForceFlags(int groupID)
	{
		return (ForceFlag)0;
	}

	public static void GetUnitByName(Thingsoft.Pool.UnitData units, string nick, Thingsoft.Pool.UnitData res)
	{
	}

	public static void GetUnitByBFIndex(Thingsoft.Pool.UnitData units, int bfIndex, Thingsoft.Pool.UnitData res)
	{
	}

	public static void GetUnitByBFIndexRange(Thingsoft.Pool.UnitData units, int from, int to, Thingsoft.Pool.UnitData res)
	{
	}

	public static void GetUnitByBFIndexList(Thingsoft.Pool.UnitData units, List<int> bfIndexList, Thingsoft.Pool.UnitData res)
	{
	}

	public static string GetStringFromBfIndexList(List<int> bfIndexList)
	{
		return null;
	}

	public static void GetUnitPickInfos(UserUnit userUnit, out int? squad, out int? bfIndex, out bool picked, out bool isSelected)
	{
		squad = null;
		bfIndex = null;
		picked = default(bool);
		isSelected = default(bool);
	}

	public static Color GetSquadFrameColor(int squad)
	{
		return default(Color);
	}

	public static void GetUnitById(Thingsoft.Pool.UnitData units, int unitId, Thingsoft.Pool.UnitData res)
	{
	}

	public static void GetUnitByArea(List<UnitData> units, IntVector2 start, IntVector2 end, bool getParentData, bool includeDead, bool exceptObjectUnit, Thingsoft.Pool.UnitData res)
	{
	}

	public static void GetUnitByBFGroup(Thingsoft.Pool.UnitData units, int bfGroup, Thingsoft.Pool.UnitData res)
	{
	}

	public static void GetUnitByUserSoldier(Thingsoft.Pool.UnitData units, Thingsoft.Pool.UnitData res)
	{
	}

	public static void GetUnitsOn8ZocAroundUnit(Thingsoft.Pool.UnitData units, string nick, Thingsoft.Pool.UnitData targetUnits, Thingsoft.Pool.UnitData res)
	{
	}

	public static void GetUnitsByJobType(Thingsoft.Pool.UnitData units, int jobType, Thingsoft.Pool.UnitData res)
	{
	}

	public static bool OpenedContentByCountryCode(int countryCode)
	{
		return false;
	}

	public static bool OpenedContentByPlatformTypes(EnumData.PlatformType[] platformTypes)
	{
		return false;
	}

	public static bool OpenedContentByPlatformTypeCode(int platformTypeCode)
	{
		return false;
	}

	public static void Restart()
	{
	}

	public static int GetHpBonustByTile(int tileId, int unitLevel)
	{
		return 0;
	}

	public static int GetMpBonusByTile(int tileId, int unitLevel)
	{
		return 0;
	}

	public static List<ObscuredInt> GetTowns(EnumData.OccupyType occupyType)
	{
		return null;
	}

	public static void UseInputOnTutorial(bool use)
	{
	}

	public static void UseUIInputOnTutorial(bool use)
	{
	}

	public static void UseInputOnNotTutorial(bool use)
	{
	}

	public static void ClearInputState()
	{
	}

	public static void AddInputQueue(InputPriority inputPriority, bool useInput)
	{
	}

	public static void RemoveInputQueue(InputPriority inputPriority)
	{
	}

	private static bool GetInputState()
	{
		return false;
	}

	private static void ApplyInputState()
	{
	}

	public static void UseInput(bool use)
	{
	}

	private static void UseUIInput(bool use)
	{
	}

	private static void UseEventSystemInput(bool use)
	{
	}

	private static void UseCameraInput(bool use)
	{
	}

	public static bool IsUsingMainCameraInput()
	{
		return false;
	}

	public static bool Contains<T>(this T[] array, T value) where T : IEquatable<T>
	{
		return false;
	}

	public static int IndexOf<T>(this T[] array, T value) where T : IEquatable<T>
	{
		return 0;
	}

	public static DailyGift GetDailyGift(int day, EnumData.AttendType attendType)
	{
		return null;
	}

	public static int GetAbsenceDays(string yyyymmdd)
	{
		return 0;
	}

	public static DailyGift GetNextConsecutiveDailyGift()
	{
		return null;
	}

	public static int GetGoodsReward(CommonMission data, EnumData.GoodsType goodsType)
	{
		return 0;
	}

	public static int GetGoodsReward(List<RewardInfo> rewardSet, EnumData.GoodsType goodsType)
	{
		return 0;
	}

	private static int GetGoodsReward(SetGood setGood, EnumData.GoodsType goodsType)
	{
		return 0;
	}

	public static T Prev<T>(this List<T> list, T curr)
	{
		return default(T);
	}

	public static T Next<T>(this List<T> list, T curr)
	{
		return default(T);
	}

	public static int GetAndroidSDKVersion()
	{
		return 0;
	}

	public static string ReplaceJosa(this string src)
	{
		return null;
	}

	private static bool HasJong(char inChar)
	{
		return false;
	}

	private static bool HasJongExceptRieul(char inChar)
	{
		return false;
	}

	private static int GetCheckJosaIndex(string srcString, Match josaMatch)
	{
		return 0;
	}

	public static string ReplaceJosaAdv(this string src)
	{
		return null;
	}

	private static int GetPrevCharIdx(string srcStr, int idx)
	{
		return 0;
	}

	public static int Fr()
	{
		return 0;
	}

	public static long Fr64()
	{
		return 0L;
	}

	public static string Frs(int length)
	{
		return null;
	}

	public static string GetDayOfWeekString(int dayOfWeek)
	{
		return null;
	}

	public static string GetDayOfWeekFullString(int dayOfWeek)
	{
		return null;
	}

	public static string GetPartsOftheDay(int hours)
	{
		return null;
	}

	public static string GetRemainingTime(TimeSpan ts, int maxDisplayCount)
	{
		return null;
	}

	public static string GetRemainingTime(TimeSpan timeSpan)
	{
		return null;
	}

	public static string GetTimeBeforeFromNow(TimeSpan timeSpan)
	{
		return null;
	}

	public static string GetRankDurationString(int duration)
	{
		return null;
	}

	public static Tier GetTierByGrade(int grade)
	{
		return null;
	}

	public static Tier GetTierByGrade(EnumData.TierType tierType, int grade)
	{
		return null;
	}

	public static float GetRankPercent(int current, int maximum)
	{
		return 0f;
	}

	public static int GetIntPercent(int current, int maximum)
	{
		return 0;
	}

	public static League GetLeagueByRank(int userRank)
	{
		return null;
	}

	public static League GetDefaultLeague(EnumData.LeagueType leagueType)
	{
		return null;
	}

	public static Tier GetDefaultTier(EnumData.TierType tierType)
	{
		return null;
	}

	public static Tier GetLeagueTierByPoint(int leagueId, EnumData.TierType tierType, int point)
	{
		return null;
	}

	public static Tier GetTierByPoint(EnumData.TierType tierType, int point)
	{
		return null;
	}

	public static League GetSeasonLeague(EnumData.LeagueType leagueType)
	{
		return null;
	}

	public static League GetSeasonRankLeague(EnumData.rankType rankType)
	{
		return null;
	}

	public static EnumData.LeagueType GetLeagueType(EnumData.rankType rankType)
	{
		return EnumData.LeagueType.None;
	}

	public static League GetExpectedLeague(EnumData.LeagueType leagueType, int rank)
	{
		return null;
	}

	public static List<Dictionary<string, object>> SerializeUnitInfos(List<UserUnitGroup.UnitInfo> unitList)
	{
		return null;
	}

	public static Dictionary<string, object> SerializeBattleFormation(BattleFormation battleFormation)
	{
		return null;
	}

	public static List<Dictionary<string, object>> SerializeBattledeckFormation(List<UserQuickBattleDeckUnit> deckUnits)
	{
		return null;
	}

	public static string GetBattleFormationTargetString(EnumData.BattleFormationTarget battleFormationTarget)
	{
		return null;
	}

	public static string GetBattleFormationAttributeString(EnumData.BattleFormationAttribute battleFormationAttribute)
	{
		return null;
	}

	public static List<EnumData.spAction> GetPassivesForBattleFormationAttribute(EnumData.BattleFormationAttribute attribute)
	{
		return null;
	}

	public static int GetTownPopularityGrade(int townGrade, int popularity)
	{
		return 0;
	}

	public static string GetPopularityGradeString(int popularityGrade)
	{
		return null;
	}

	public static int GetMaxPopularity(int townGrade)
	{
		return 0;
	}

	public static EnumData.UserStatus GetStringToUserStatus(string str)
	{
		return (EnumData.UserStatus)0;
	}

	public static EnumData.AttendType GetStringToAttendType(string str)
	{
		return EnumData.AttendType.Invalid;
	}

	public static EnumData.AchievementCategory GetUserStatusToAchieveCategory(EnumData.UserStatus userStatus)
	{
		return EnumData.AchievementCategory.Common;
	}

	public static int GetEqualCount<T>(T[] array, T checkState)
	{
		return 0;
	}

	public static string GetTownInfoSortingLabel(EnumData.TownSortType type)
	{
		return null;
	}

	public static DayOfWeek NextDay(this DayOfWeek day)
	{
		return DayOfWeek.Sunday;
	}

	public static DayOfWeek PrevDay(this DayOfWeek day)
	{
		return DayOfWeek.Sunday;
	}

	public static DateTime NextWeekDay(this DateTime time, DayOfWeek nextDayOfWeek)
	{
		return default(DateTime);
	}

	public static DateTime MinDateTime(this DateTime a, DateTime b)
	{
		return default(DateTime);
	}

	public static DateTime MaxDateTime(this DateTime a, DateTime b)
	{
		return default(DateTime);
	}

	private static string GetBanContentsTitle(EnumData.BanContentsType type)
	{
		return null;
	}

	public static void ShowPopupBan(EnumData.BanContentsType type)
	{
	}

	public static bool IsDailyDungeon(Instance instData)
	{
		return false;
	}

	public static bool IsSilverDungeon(Instance instData)
	{
		return false;
	}

	public static string ColorToHex(Color32 color)
	{
		return null;
	}

	public static void GotoCafe()
	{
	}

	public static void GotoCommunity()
	{
	}

	public static void AddDepth(GameObject go, int adjustment)
	{
	}

	public static string GetPassiveCategoryString(SpActionBase.Origin origin)
	{
		return null;
	}

	public static string GetBattleModeName(EnumData.BattleMode battleMode)
	{
		return null;
	}

	public static string GetQuickBattleDeckConfirmPopupCenterText(EnumData.BattleMode battleMode)
	{
		return null;
	}

	public static int GetCurrentQuickBattleDeckAddSlotCost(int slotNum = 0)
	{
		return 0;
	}

	public static string GetCurrentQuickBattleDeckTitle(int id = 0)
	{
		return null;
	}

	public static int GetQuickBattleModeIdByBattleMode(EnumData.BattleMode battleMode = EnumData.BattleMode.None)
	{
		return 0;
	}

	public static EnumData.GoodsType GetBattleFieldRewardGoodsType(EnumData.BattleFieldRewardType type)
	{
		return EnumData.GoodsType.None;
	}

	public static int GetBattleFieldRewardItemId(EnumData.BattleFieldRewardType type)
	{
		return 0;
	}

	public static bool IsBattleFieldRewardGoodsType(EnumData.BattleFieldRewardType type)
	{
		return false;
	}

	public static bool IsPlayBattleField()
	{
		return false;
	}

	public static EnumData.BattleFieldMatchingUserPower GetBattleFieldMatchingPower(int groupPower)
	{
		return (EnumData.BattleFieldMatchingUserPower)0;
	}

	public static IntVector2 GetSoldierPos(IntVector2 pos, UnitDirection direction)
	{
		return default(IntVector2);
	}

	public static int GetSoldierId(int soldierIdx, UserSoldierDeck srcSoldierDeck)
	{
		return 0;
	}

	public static bool IsCaculateUnitExpMode()
	{
		return false;
	}

	public static string GetEquipNotice(ulong itemSrl, string defaultNotice)
	{
		return null;
	}

	public static int GetBattleFieldErrorCode(string errorType)
	{
		return 0;
	}

	public static ObjectSide GetMapObjectSideBattleType(ObjectSide prevSide = ObjectSide.ENEMY)
	{
		return ObjectSide.ENEMY;
	}

	public static int GetMinJobLevelByLevel(int unitId, int level)
	{
		return 0;
	}

	public static int GetMinLevelByJobLevel(int unitId, int jobLevel)
	{
		return 0;
	}

	public static int GetReinforceLevelByLevel(int unitId, int level)
	{
		return 0;
	}

	public static int CalculateTowerLevel(ulong uid, int rank)
	{
		return 0;
	}

	public static string GetRaidBossNameByType(int raidType)
	{
		return null;
	}

	public static string GetRaidTitle(int raidType, int useType, string postfix)
	{
		return null;
	}

	public static Texture GetRaidFaceTexture(int raidType)
	{
		return null;
	}

	public static string GetRaidDifficultyString(int difficulty)
	{
		return null;
	}

	public static EnumData.RaidStuffType GetRaidStuffTypeByItemId(int itemId)
	{
		return EnumData.RaidStuffType.NONE;
	}

	public static int GetRaidStuffItemIdByStuffType(EnumData.RaidStuffType stuffType)
	{
		return 0;
	}

	public static string GetRaidStuffSpriteName(int itemId)
	{
		return null;
	}

	public static string GetRaidStuffSpriteName(EnumData.RaidStuffType type)
	{
		return null;
	}

	public static int GetRaidStuffAmount(int itemId)
	{
		return 0;
	}

	public static int GetRaidStuffAmount(EnumData.RaidStuffType type)
	{
		return 0;
	}

	public static bool IsRaidJobType(EnumData.JobTypes jobTypeId)
	{
		return false;
	}

	public static bool IsRaidGoalReversed(EnumData.RaidType raidType)
	{
		return false;
	}

	public static void CheckTreasureTicketExist(this Achievement achievement, out int boxType, out int tier, out bool isTreasure, out EnumData.ItemType itemType, out string itemName)
	{
		boxType = default(int);
		tier = default(int);
		isTreasure = default(bool);
		itemType = default(EnumData.ItemType);
		itemName = null;
	}

	public static string GetGuildWarkFrameSpriteName(int rank, bool isBig)
	{
		return null;
	}

	public static EnumData.GuildSiegeFacilitySlotType GuildSiegeFacilitySlotToSlotType(EnumData.GuildSiegeFacilitySlot slot)
	{
		return EnumData.GuildSiegeFacilitySlotType.Fortress;
	}

	public static int GetTechUserLevel(Tech tech)
	{
		return 0;
	}

	public static int GetNeedNewTagTechCount()
	{
		return 0;
	}

	public static bool IsTechNeedNewTag(Tech tech, int checkLevel)
	{
		return false;
	}

	public static bool IsTechUpgradable(Tech tech)
	{
		return false;
	}

	public static bool IsUnitTreeCanFreeUnlock(UnitTree unitTree)
	{
		return false;
	}

	public static int GetFreeUnlockUnitTreeCount()
	{
		return 0;
	}

	public static float GetPassiveBasestat(Passive passive, UserUnit userUnit)
	{
		return 0f;
	}

	public static float GetPassiveMaxBasestat(Passive passive)
	{
		return 0f;
	}

	public static int GetRuneErrorCode(string errorType)
	{
		return 0;
	}

	public static string GetRuneDecompositionSpriteName(int decompositionType)
	{
		return null;
	}

	public static float GetRunePassiveFixValue(UserUnit userUnit, Passive passive, int passiveValue)
	{
		return 0f;
	}

	public static string GetRunePassiveShortDesc(UserUnit userUnit, Passive passive, int passiveValue, int level = 0)
	{
		return null;
	}

	public static string GetRunePassiveFullDesc(UserUnit userUnit, int passiveId, int passiveValue)
	{
		return null;
	}

	public static void DestroyChildrenFromGrid(Transform parent)
	{
	}

	public static RankUpGiftReward GetRankupGiftReward(int rankupGiftId)
	{
		return null;
	}

	public static bool IsFireObject(int mapObjectId)
	{
		return false;
	}

	public static bool IsFireTile(int tileId)
	{
		return false;
	}

	public static void ShowTooltip(ITooltipController controller)
	{
	}

	public static Color GetTileAttrColor(int tileDefense)
	{
		return default(Color);
	}

	public static GameObject IsAddEffectGridGameObject(UnitData unitData)
	{
		return null;
	}

	public static Comparison<UserUnit> GetSortingCompetitionPickUnits()
	{
		return null;
	}

	public static bool DoNotUseEnergy()
	{
		return false;
	}

	public static bool IsTagActiveUnitState(EnumData.Brawl_Unit_State unitState)
	{
		return false;
	}

	public static GameObject ShowEffect(string poolName, string prefabName, Transform parent, Vector2 pos)
	{
		return null;
	}

	public static string COMPETITION_FRIENDLY_UNITS_SRL_KEY(int mapId)
	{
		return null;
	}

	public static EnumData.AttendType GetAttendTypeBySpecialAttendType(EnumData.SpecialAttendType specialAttendType)
	{
		return EnumData.AttendType.Invalid;
	}

	public static EnumData.SpecialAttendType GetSpecialAttendTypeByAttendType(EnumData.AttendType attendType)
	{
		return EnumData.SpecialAttendType.None;
	}

	public static bool HasStickerSet(int stickerSetId)
	{
		return false;
	}

	public static int GetPassivePointExchangeGold(int lackPoint)
	{
		return 0;
	}

	public static int GetRevisionNumber()
	{
		return 0;
	}

	public static string GetOpenRangeString(EnumData.UserInfoOpenRange replayOpenRange)
	{
		return null;
	}

	public static Stream GenerateStreamFromString(string s)
	{
		return null;
	}

	public static void CopyStream(this Stream input, Stream output)
	{
	}

	public static string GzipDecompress(string inputString)
	{
		return null;
	}

	public static bool isUniqueItem(EnumData.ItemType itemType)
	{
		return false;
	}

	public static bool isUniqueTreasure(EnumData.ItemSlotType slotType, EnumData.TreasureType treasureType)
	{
		return false;
	}

	public static bool IsCurrentLanguageSupported(int flagBit)
	{
		return false;
	}

	public static List<EnumData.Locale> GetSupportedLanguage(int flagBit)
	{
		return null;
	}

	public static Dictionary<string, EnumData.Locale> GetLanguages2Locale()
	{
		return null;
	}

	public static bool CheckManagementBtnAlarm()
	{
		return false;
	}

	public static bool WillCheckCommonMissionWithBattleType(EnumData.CommonMissionCheckType checkType)
	{
		return false;
	}

	public static bool WillCheckAchievementWithBattleType(EnumData.AchievementCheckType checkType)
	{
		return false;
	}

	public static bool WillCheckDefeatAchievementOrMission()
	{
		return false;
	}

	public static string GetPassiveBaseStatString(EnumData.PassiveBaseStat statType)
	{
		return null;
	}

	public static bool IsRelationCompleteUnit(int unitId)
	{
		return false;
	}

	public static string GetWeaknessTypeString(EnumData.WeaknessType type)
	{
		return null;
	}

	public static EnumData.WeaknessType GetWeaponWeaknessAttackType(int itemId)
	{
		return EnumData.WeaknessType.Fan;
	}

	public static EnumData.WeaknessType GetSkillWeaknessAttackType(int skillId)
	{
		return EnumData.WeaknessType.Fan;
	}

	public static bool CanUseItemInBattleMode(EnumData.BattleMode battleMode, Item pdItem)
	{
		return false;
	}

	public static void ShowTerms()
	{
	}

	public static void SettlementFailed(EnumData.NPErrorCode errorCode)
	{
	}

	public static void ShowMaintenancePopup()
	{
	}

	public static int ConvertColor32(Color32 src)
	{
		return 0;
	}

	public static string GetGuildBoardTypeLocalString(int type)
	{
		return null;
	}

	public static string GetGuildBoardTypeLocalString(GuildBoardPostData.Type type)
	{
		return null;
	}

	public static void SetGuildJobLabelObj(Transform jobParent, EnumData.GuildJopType job)
	{
	}

	public static bool IsValidWeekDay(DayOfWeek weekDay, int weekDayCode)
	{
		return false;
	}

	public static void ShowQNA()
	{
	}

	public static string GetPublicIP()
	{
		return null;
	}

	public static void SetPublicIP(string ipAddress)
	{
	}

	public static string GetLauncherName()
	{
		return null;
	}

	public static bool IsAuraCondition(UnitCondition condition)
	{
		return false;
	}

	public static T NextEnum<T>(this T src) where T : struct
	{
		return default(T);
	}

	public static T PrevEnum<T>(this T src) where T : struct
	{
		return default(T);
	}
}
