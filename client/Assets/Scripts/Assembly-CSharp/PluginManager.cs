using System;
using System.Collections.Generic;
using CaoCaoIAB;

public static class PluginManager
{
	private static string m_nxArenaAuthId;

	private static string m_nxArenaToken;

	public static bool GetFailedEnterLobbyShown
	{
		get
		{
			return false;
		}
	}

	public static bool NPAEnterToySucceed
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public static string NPACode
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public static string NPAnpsn
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public static string NPAToyToken
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public static string NPACountryCode
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public static string NPALoginJson
	{
		get
		{
			return null;
		}
	}

	public static string NPAGetUserInfoJson
	{
		get
		{
			return null;
		}
	}

	public static string GetUUID
	{
		get
		{
			return null;
		}
	}

	public static string GetToyLoginMailAddr
	{
		get
		{
			return null;
		}
	}

	public static string GetAccountInfoSprite
	{
		get
		{
			return null;
		}
	}

	public static bool IsSNSAccount
	{
		get
		{
			return false;
		}
	}

	public static int GetGroupCodeByNationOS
	{
		get
		{
			return 0;
		}
	}

	public static bool NPGpgsIsLogin
	{
		get
		{
			return false;
		}
	}

	public static string NxArenaAuthID
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public static string NxArenaToken
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public static bool IsSteamClient
	{
		get
		{
			return false;
		}
	}

	public static void TrackLogin()
	{
	}

	public static void TrackEnterWorldmap(List<int> lostTownIds, List<int> riotOccurredTownIds)
	{
	}

	public static void TrackBattleEnter()
	{
	}

	public static void TrackBattleFinish(bool win, int rankPoint = -1)
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

	public static void TrackCommodity(EnumData.GoodsType goodsType, string cause, int amount, bool isConsumtion)
	{
	}

	public static void TapjoyConnect()
	{
	}

	public static void SetUserID(string userId)
	{
	}

	public static void SetUserLevel(int level)
	{
	}

	public static void AddUserTag(string tag)
	{
	}

	public static void RemoveUserTag(string tag)
	{
	}

	public static void SetUserTags(string tag)
	{
	}

	public static void ClearUserTags()
	{
	}

	public static void SetPurchasedUserTag(Purchase purchase)
	{
	}

	public static void SetPurchasedUserTag(string productId)
	{
	}

	public static void SetLinkedAccountTag(bool linked)
	{
	}

	public static void SetCountryTag(EnumData.CountryCode countryCode)
	{
	}

	public static void SetPlacementPending(bool value)
	{
	}

	public static void SetFailedEnterLobbyShown(bool value)
	{
	}

	public static void PreloadPlacementContents(string placementName, bool saveCache)
	{
	}

	public static void UnloadPlacmentContents(string placementName)
	{
	}

	public static void ShowPlacementContent(string placementName, bool addFirst = false)
	{
	}

	public static bool CheckContentReady(string placementName = null)
	{
		return false;
	}

	public static void Event(AdjustTracking.EventType type)
	{
	}

	public static void Event(string androidToken)
	{
	}

	public static void SetRevenue(double amount, string currency, string transactionId)
	{
	}

	public static void EventLocalization(string androidToken)
	{
	}

	public static void CaocaoToyPushManagerInit()
	{
	}

	public static void UpdateForcePopularityRewardLocalPush()
	{
	}

	public static void NPASetEntrance()
	{
	}

	public static void TurnOn()
	{
	}

	public static void PushInit()
	{
	}

	public static void EnterToyReq(Action succeed, Action crashed, Action failed, Action maintenance)
	{
	}

	public static void LoginReq(Action<string, string, string> succeed, Action crashed, Action failed, Action cancel)
	{
	}

	public static void LogoutReq()
	{
	}

	public static string NgsmErrMsgFormat(int code)
	{
		return null;
	}

	public static void GetUserInfoReq(Action<string, string, string> succeed, Action crashed, Action failed)
	{
	}

	public static void ShowTermsOfAgreeReq(Action successCallback, Action failedCallback)
	{
	}

	public static void ShowNotice(Action closeCallback)
	{
	}

	public static void ShowBanner(int groupCode)
	{
	}

	public static void ShowEndingBanner()
	{
	}

	public static void ShowWebView(string title, string url)
	{
	}

	public static void ShowSettlementFund(string itemName, string itemPrice, Action successCallback, Action<EnumData.NPErrorCode> failedCallback)
	{
	}

	public static void ShowSettlementFund(string itemName, string itemPrice, string productId, Action successCallback, Action<EnumData.NPErrorCode> failedCallback)
	{
	}

	public static void ShowBasePlate(int groupCode, Action actionPerformedCallback)
	{
	}

	public static void UnregisterService(Action<string> callback)
	{
	}

	public static void GetCountryFromServer(Action callback)
	{
	}

	public static void CancelLocalPush(CaocaoPushType pushType)
	{
	}

	public static bool IsPermissionAllGranted()
	{
		return false;
	}

	public static void RetryPermissionRequest(bool hasFocus)
	{
	}

	public static void RequestPermissions()
	{
	}

	public static void AddPermissionCallback(Action onSuccessCallback, Action onFailCallback)
	{
	}

	public static void RequestPermission(string usePermission)
	{
	}

	public static bool IsAvailableOfferwall()
	{
		return false;
	}

	public static void ShowOfferwall(Action closeCallback)
	{
	}

	public static void NPGpgsLogout(Action<bool> callback = null)
	{
	}

	public static void NPGpgsLogin(Action<bool> callback = null)
	{
	}

	public static void NPGpgsShowAchievement(Action<bool> callback = null)
	{
	}

	public static void NPGpgsLoadAchievementData(Action<bool> callback = null)
	{
	}

	public static void NPGpgsAddLoginEvent(Action<bool> eventHandler)
	{
	}

	public static void NPGpgsDeleteLoginEvent(Action<bool> eventHandler)
	{
	}

	public static void NPGpgsGFSetNationNameLogin()
	{
	}

	public static void NPGpgsUnlockAchievement(string achievementId)
	{
	}

	public static void NPGpgsSetResultFirstLogin(bool isResult)
	{
	}

	public static void SendNXLog(string logType, string data)
	{
	}

	public static int Arena_GetToken(IntPtr tokenPtr)
	{
		return 0;
	}

	public static void GLinkSyncGameUserId(string npaCode)
	{
	}

	public static void GotoCafe()
	{
	}

	public static void GotoCommunity()
	{
	}

	public static void UpdateUserInfoReq(params object[] args)
	{
	}

	public static void TSIPlatformLoginReq(Action<string, int> succeed, Action failed, Action cancel)
	{
	}

	public static void Fabric_Init()
	{
	}

	public static void Crashlytics_SetUserIdentifier(string userIdentifier)
	{
	}

	public static void Crashlytics_SetUserCustomKey(string key, string value)
	{
	}

	public static void Crashlytics_SetUserName(string name)
	{
	}

	public static void Crashlytics_SetUserEmail(string email)
	{
	}

	public static void Crashlytics_RecordCustomException(string name, string reason, string stackTrace)
	{
	}

	public static void Crashlytics_CustomLog(string log)
	{
	}

	public static void SteamManagerInit()
	{
	}

	public static void SteamAchievementInit()
	{
	}

	public static void SteamAchievementAdvance(EnumData.SteamAchievement achievement, int value, bool overwrite)
	{
	}

	public static string GetSteamCountryCode()
	{
		return null;
	}

	public static string GetSteamTicket()
	{
		return null;
	}
}
