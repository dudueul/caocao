using System;
using NPAListener;

public class NPGpgsController : Singleton<NPGpgsController>
{
	private Action<bool> m_globalLoginCallBackFunc;

	public static readonly string REQUEST_FIRSTLOGIN;

	public static readonly string GPGS_RESULT_LOGIN;

	public void AddLoginEvent(Action<bool> eventHandler)
	{
	}

	public void DeleteLoginEvent(Action<bool> eventHandler)
	{
	}

	public void Login(Action<bool> callback = null)
	{
	}

	public void Logout(Action<bool> callback = null)
	{
	}

	public bool IsLogin()
	{
		return false;
	}

	public void SetResultFirstLogin(bool isResult)
	{
	}

	public void UnlockAchievement(string achievementId)
	{
	}

	public void ShowAchievement(Action<bool> callback = null)
	{
	}

	public void LoadAchievementData(Action<bool> callback = null)
	{
	}

	private void SetAchievementsForFirstLogin()
	{
	}

	private void GetListener(out GooglePlayGameListener listener, Action<bool> callback = null)
	{
		listener = null;
	}
}
