using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public static class GuildManager
{
	private class CallbackData
	{
		public static List<CallbackData> removes;

		public string uri;

		public List<Action> onSuccess;

		public List<Action> onFail;

		public CallbackData(string uri, Action onSuccess, Action onFail)
		{
		}

		public void ResponseCallback(JsonData responseData)
		{
		}

		public void Add(Action onSuccess, Action onFail)
		{
		}
	}

	public delegate void GuildErrorEvent(string err, ref bool handled);

	public enum FacilityColor
	{
		Red = 0,
		Blue = 1,
		Purple = 2,
		Black = 3
	}

	private static GuildErrorEvent OnGuildError__BackingField;

	private static Action<List<GuildInfo>> OnUpdateGuildList__BackingField;

	private static GuildErrorMsgTable errorMsgTable;

	private static Dictionary<string, CallbackData> callbacks;

	private static readonly string PREFS_RECEIVED_DATE_KEY;

	public static readonly GuildSummaryCaches summaryCaches;

	private static Dictionary<int, string> dicPreparedPresentTypeText;

	private static ulong justRequestGuildId;

	private static bool justSuccess;

	public static string recentlyErrorMsg;

	public static GuildMember myInfo
	{
		get
		{
			return null;
		}
	}

	public static ulong myGuildId
	{
		get
		{
			return 0uL;
		}
	}

	public static bool isAbleCreateGuild
	{
		get
		{
			return false;
		}
	}

	public static bool isGuildMember
	{
		get
		{
			return false;
		}
	}

	public static bool isMissionAbled
	{
		get
		{
			return false;
		}
	}

	public static bool isApplicant
	{
		get
		{
			return false;
		}
	}

	public static int guildPoint
	{
		get
		{
			return 0;
		}
	}

	public static GuildInfo myGuild
	{
		get
		{
			return null;
		}
	}

	public static ulong appliedGuildId
	{
		get
		{
			return 0uL;
		}
	}

	public static GuildInfo appliedGuild
	{
		get
		{
			return null;
		}
	}

	public static string GetPrefsReceivedDateKey
	{
		get
		{
			return null;
		}
	}

	public static bool isMissionRewardProcessing { get; set; }

	public static event GuildErrorEvent OnGuildError
	{
		add
		{
		}
		remove
		{
		}
	}

	public static event Action<List<GuildInfo>> OnUpdateGuildList
	{
		add
		{
		}
		remove
		{
		}
	}

	private static CallbackData AddCallback(string uri, Action onSuccess, Action onFail)
	{
		return null;
	}

	private static void OnCancelConsuming(string uri)
	{
	}

	private static bool IsAbleReceiveDate(DateTime missionMonday)
	{
		return false;
	}

	public static bool CheckGuildWeeklyMission(Action onError)
	{
		return false;
	}

	public static bool ValidateGuildName(string guildName)
	{
		return false;
	}

	public static bool ValidateRecruitMsg(string recruitMsg, int limitLength)
	{
		return false;
	}

	public static string GetDonationExpireDate()
	{
		return null;
	}

	public static string GetPresentTypeText(GuildPresentHistory history)
	{
		return null;
	}

	public static EnumData.GuildSiegeFacilitySlot GetFacilitySlot(uint facilitySlot)
	{
		return EnumData.GuildSiegeFacilitySlot.LeftFortress;
	}

	public static bool IsFortressSlot(EnumData.GuildSiegeFacilitySlot slot)
	{
		return false;
	}

	public static bool IsFacilitySlot(EnumData.GuildSiegeFacilitySlot slot)
	{
		return false;
	}

	public static int GetMaxFacilityMemberNum(EnumData.GuildSiegeFacilitySlot slot)
	{
		return 0;
	}

	public static int GetMaxFacilityDefUnitNum(EnumData.GuildSiegeFacilitySlot slot)
	{
		return 0;
	}

	public static int GetMaxFacilityAtkUnitNum(EnumData.GuildSiegeFacilitySlot slot)
	{
		return 0;
	}

	public static int GetMaxAttackUnitNum(EnumData.GuildSiegeFacilitySlot slot)
	{
		return 0;
	}

	public static FacilityColor GetFacilityColor(EnumData.GuildSiegeFacilitySlot facilitySlot)
	{
		return FacilityColor.Red;
	}

	public static bool IsNpcMember(ulong uid)
	{
		return false;
	}

	public static void Clear()
	{
	}

	public static void RequestMyGuild()
	{
	}

	public static void RequestGuildInfo(ulong guildId, Action success, Action fail)
	{
	}

	public static void RequestBeforeWeekMissionInfo(Action onSuccess, Action onFail)
	{
	}

	public static void RequestOppoentGuild(ulong guildId)
	{
	}

	public static void RequestMe(Action onSuccess, Action onFail, bool showIndicator)
	{
	}

	public static void ChangeGuildNotice(string motd)
	{
	}

	public static void RequestRecruitingGuildList(EnumData.GuildAcceptType acceptType)
	{
	}

	public static void SearchRecruitGuildList(EnumData.GuildAcceptType acceptType, string searchName)
	{
	}

	public static void ApplyGuild(ulong guildId)
	{
	}

	public static void CancelApplyGuild(ulong guildId)
	{
	}

	public static void CreateGuild(string guildName, EnumData.GuildAcceptType acceptType, string recruitMsg, int markTexIndex, int rankLimit, Texture markTexture)
	{
	}

	public static void RequestChangeMark(int texIndex, bool isFree, Action callback)
	{
	}

	public static void RequestChangeName(string guildName, Action callback)
	{
	}

	public static void Leave()
	{
	}

	public static void RequestApplicants()
	{
	}

	public static void ProcessApplicant(ulong applicant, bool accept)
	{
	}

	public static void SetJoinConfig(string recruitMsg, EnumData.GuildAcceptType acceptType, int rankLimit)
	{
	}

	[DebuggerHidden]
	public static IEnumerator GetGuildMember(ulong guildId, Action success, Action fail)
	{
		return null;
	}

	public static void GetGuildMembersDetail(ulong guildId)
	{
	}

	public static void ChangeMemberJob(EnumData.GuildJopType jobType, ulong uid)
	{
	}

	public static void KickMember(ulong uid)
	{
	}

	public static void DismissGuild()
	{
	}

	public static void GetShopHistory()
	{
	}

	public static void PurchaseItem(int shopId, int amount, Action onSuccess)
	{
	}

	public static void RequestGuildMissions(Action onSuccess, Action onFail, bool showIndicator)
	{
	}

	public static void RequestMissionReward(Action onError)
	{
	}

	public static void UpdatePlayedTime()
	{
	}

	public static void GetPresentLog(int lastSrl)
	{
	}

	public static void GetStorageLog(int lastSrl)
	{
	}

	public static void GetGuildHistoryLog(int lastSrl, Action callBack)
	{
	}

	public static void PresentItems(bool isGoods, int itemId, params object[] param)
	{
	}

	public static void RequestMemberPresentHistory()
	{
	}

	public static void RequestSucceedToMaster()
	{
	}

	public static void GetGuildCompetitionRanking(ulong guildId)
	{
	}

	public static void SendHttpRequest(string uri, bool showIndicator, Action onSuccess, Action onFail, bool excludeDuplicatedCallback, params object[] args)
	{
	}

	[DebuggerHidden]
	public static IEnumerator SendAndWaitHttpRequest(string uri, bool showIndicator, Action onSuccess, Action onFail, params object[] args)
	{
		return null;
	}

	public static void OnPurchasedItem(JsonData jsonData)
	{
	}

	public static void OnGuildResponse(string uri, JsonData jsonData)
	{
	}

	public static void OnLogin(JsonData jsonData)
	{
	}

	public static void OnGuildResponse(JsonData jsonData)
	{
	}

	public static void SetSiegeWarDefaultDeck(JsonData response)
	{
	}

	private static void Deserialize(JsonData packet)
	{
	}

	public static GuildInfo AddGuildInfo(JsonData jsonData)
	{
		return null;
	}

	public static void DeserializeSiegeWarGuildData(JsonData jsonData)
	{
	}

	public static void DeserializeSiegeWarMyData(JsonData jsonData)
	{
	}

	private static void DeserializeMe(JsonData me)
	{
	}

	private static void DeserializeGuild(JsonData guild)
	{
	}

	public static void DeserializeGuildList(JsonData listJson)
	{
	}

	private static void ProcessGuildError(string errorType)
	{
	}

	public static string GetErrorMessage(string errorType)
	{
		return null;
	}
}
