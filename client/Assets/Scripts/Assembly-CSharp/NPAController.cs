using System;
using System.Collections.Generic;
using NPA;
using SimpleJSON;

public class NPAController : Singleton<NPAController>
{
	public enum PermissionFlow
	{
		DENY = 0,
		DONTASKAGAIN_DENY = 1,
		SHOW_APPSETTING = 2
	}

	private class PermissionInfo
	{
		public string name;

		public int option;

		public string caution;

		public PermissionInfo(string setName, int setOption, string setCautionText)
		{
		}
	}

	private Toy m_toy;

	public bool enterToySucceed;

	public string npaCode;

	public string npsn;

	public string toyToken;

	public string countryCode;

	public JSONNode loginJson;

	public JSONNode getUserInfoJson;

	private readonly List<string> m_checkPermissions;

	private readonly List<int> m_chekcPermissionOption;

	private List<string> m_descPermissions;

	private PermissionFlow m_flow;

	public Action onPermissionSuccess;

	public Action onPermissionFail;

	private List<PermissionInfo> runtimePermissions;

	private bool m_isRetryRequest;

	private string m_SENDER_ID
	{
		get
		{
			return null;
		}
	}

	public static NPCountry GetNPCountry
	{
		get
		{
			return (NPCountry)0;
		}
	}

	public PermissionFlow permissionFlow
	{
		get
		{
			return PermissionFlow.DENY;
		}
		set
		{
		}
	}

	public int permissionRequestCode { get; set; }

	public static NPLocale GetLocaleByLanguage()
	{
		return NPLocale.EN_AU;
	}

	public void TurnOn()
	{
	}

	protected override void Awake()
	{
	}

	public void PushInit()
	{
	}

	public string GetUUID()
	{
		return null;
	}

	public void SetOptions(NPOptions option)
	{
	}

	public void EnterToyReq(Action succeed, Action crashed, Action failed, Action maintenance)
	{
	}

	public void LoginReq(Action<string, string, string> succeed, Action crashed, Action failed, Action cancel)
	{
	}

	public void LogoutReq()
	{
	}

	public void GetUserInfoReq(Action<string, string, string> succeed, Action crashed, Action failed)
	{
	}

	public void ShowTermsOfAgreeReq(Action successCallback, Action failedCallback)
	{
	}

	public void ShowNotice(Action closeCallback)
	{
	}

	public void SetCountry(NPCountry country)
	{
	}

	public void SetLocale(NPLocale locale)
	{
	}

	public void ShowBanner(int groupCode)
	{
	}

	public void ShowEndingBanner()
	{
	}

	public void ShowWebView(string title, string url)
	{
	}

	public void ShowSettlementFund(string itemName, string itemPrice, Action successCallback, Action<EnumData.NPErrorCode> failedCallback)
	{
	}

	public void ShowSettlementFund(string itemName, string itemPrice, string productId, Action successCallback, Action<EnumData.NPErrorCode> failedCallback)
	{
	}

	public void ShowBasePlate(int groupCode, Action actionPerformedCallback)
	{
	}

	public NPLoginType GetLoginType()
	{
		return NPLoginType.NPLoginTypeDefault;
	}

	public bool IsSNSAccount()
	{
		return false;
	}

	public void UnregisterService(Action<string> callback)
	{
	}

	public void GetCountryFromServer(Action callback)
	{
	}

	public static int GetGroupCodeByNationOS()
	{
		return 0;
	}

	public void SendLocalPush(NPNotificationData data)
	{
	}

	public void CancelLocalPush(int notificationId)
	{
	}

	public void CancelLocalPushAll()
	{
	}

	public bool IsAvailableOfferwall()
	{
		return false;
	}

	public void ShowOfferwall(Action closeCallback)
	{
	}

	public void RequestPermissions()
	{
	}

	private PermissionInfo GetRuntimePermission(string getName)
	{
		return null;
	}

	public void RequestPermission(string requestPermission)
	{
	}

	public bool IsPermissionAllGranted()
	{
		return false;
	}

	public void RetryPermissionRequest(bool isActivityFocus)
	{
	}

	public void SetPermissionFlow(string flow)
	{
	}

	private bool IsPermissionCheckVersion()
	{
		return false;
	}

	public void SendNXLog(string logType, string data)
	{
	}
}
