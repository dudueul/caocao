using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;
using UnityEngine;

public class LoginUI : MonoBehaviour
{
	private ObscuredString m_authid;

	private ObscuredString m_loggedInCountryCode;

	private bool m_waiting;

	private GameObject m_LoginLog;

	private GameObject m_version;

	private UIInput m_uiInput;

	private List<MonoBehaviour> m_uis;

	private GFShowCI gfShowCI;

	private GFOnLogin gfOnLogin;

	private bool m_waitTouchCancel;

	private bool m_bypassCIShow;

	public bool waiting
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public string loginResult
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public bool waitTouchCancel
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool bypassCIShow
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	private void Start()
	{
	}

	private void OnEnable()
	{
	}

	public void TryAutoLogin()
	{
	}

	public void ClickLoginRequest()
	{
	}

	private void OnDisable()
	{
	}

	private void OnDestroy()
	{
	}

	private void UpdateTotalCombatpower(JsonData response)
	{
	}

	public void ClickSendLoginRequest(int channelId = 0)
	{
	}

	public void ClickNPALoginRequest()
	{
	}

	public void ClickNPALogoutRequest()
	{
	}

	public void SendNPALoginRequest(string npsn, string npToken, string npaCode)
	{
	}

	private void NpaLoginRetryPopupCallback(HttpRequest.HttpRequestContext ctx)
	{
	}

	public void OnNPALoginAuthCrashed()
	{
	}

	public void OnNPALoginFailed()
	{
	}

	public void OnNPAMaintenance()
	{
	}

	public void OnNPALoginCanceled()
	{
	}

	private void OnNPALoginValidationResult(JsonData response)
	{
	}

	private void InformValidationFailed(int code)
	{
	}

	public void ClickNxArenaLoginRequest()
	{
	}

	private void DoNxArenaLogin(string arenaToken)
	{
	}

	private void DoSteamLogin(string ticket)
	{
	}

	private void OnSteamLoginValidationResult(JsonData response)
	{
	}

	private void OnSteamLoginValidationFail(HttpRequest.HttpRequestContext ctx)
	{
	}

	private void OnArenaLoginValidationResult(JsonData response)
	{
	}

	private void OnArenaLoginValidationFail(HttpRequest.HttpRequestContext ctx)
	{
	}

	private void ArenaValidationFailed(int code)
	{
	}

	public void OnLogin(JsonData response)
	{
	}

	public void OnSubmitID()
	{
	}

	public bool IsVisibleCI()
	{
		return false;
	}

	private void GetBanContents(JsonData response)
	{
	}

	private void GetSubScriptions(JsonData response)
	{
	}

	private void OnTSILoginValidationResult(JsonData response)
	{
	}

	public void OnTSILoginSuccess(string citykey, int channelid)
	{
	}

	public void OnTSILoginFail()
	{
	}

	public void OnTSILoginCancel()
	{
	}

	private string GetOsPlatform()
	{
		return null;
	}
}
