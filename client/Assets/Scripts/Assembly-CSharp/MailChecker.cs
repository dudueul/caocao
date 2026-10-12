using System;
using LitJson;

public class MailChecker : Singleton<MailChecker>
{
	private bool m_existNewMail;

	private bool m_isCheckingNewMail;

	private DateTime m_lastChecked;

	private DateTime m_lastGmMailUpdated;

	public Action<bool> OnCheckNewMail;

	public void Init()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void Register()
	{
	}

	private void Unregister()
	{
	}

	public void CheckNewMailOnTimer()
	{
	}

	public void CheckNewMail(bool checkNow = false)
	{
	}

	private bool IsFrequentlyRequested()
	{
		return false;
	}

	private bool WillUpdateGmMail()
	{
		return false;
	}

	private void OnCheckNewMails(JsonData response)
	{
	}
}
