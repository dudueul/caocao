using System;
using NPA;

public class CaocaoToyPushData : NPNotificationData
{
	private bool m_notificationFinished;

	public bool notified;

	public Action<CaocaoToyPushData> OnNotificationFinished;

	public ulong sender { get; private set; }

	public bool isLocalPush { get; private set; }

	public bool notificationFinished
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public CaocaoToyPushData(ulong sender, NPNotificationData notiData, bool isLocalPush)
		: base(0, null, null, null, 0)
	{
	}

	public CaocaoToyPushData(ulong sender, int notificationID, string message, string meta, NPNotificationTime time, int pushType, bool isLocalPush)
		: base(0, null, null, null, 0)
	{
	}

	public CaocaoToyPushData(ulong sender, int notificationID, string message, string meta, NPNotificationTime time, int pushType, int badgeType, bool isLocalPush)
		: base(0, null, null, null, 0)
	{
	}

	public void ShowCenterPopupText()
	{
	}

	private string GetCenterPopupText()
	{
		return null;
	}
}
