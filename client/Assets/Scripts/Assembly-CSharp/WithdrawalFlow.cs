public static class WithdrawalFlow
{
	private enum State
	{
		Idle = 0,
		ShowInfo = 1,
		EnterText = 2,
		AskCancel = 3,
		ProcessUnregisterServer = 4,
		ProcessUnregisterToy = 5,
		UnregistSuccess = 6,
		UnregistFailed = 7
	}

	private static readonly string m_confirmText;

	private static PanelPopup2Buttons popup;

	private static State m_state;

	private static string confirmText
	{
		get
		{
			return null;
		}
	}

	private static State state
	{
		get
		{
			return State.Idle;
		}
		set
		{
		}
	}

	public static void StartFlow()
	{
	}

	public static void StopFlow()
	{
	}

	private static void ClosePanel()
	{
	}

	private static void ShowInfoPopup()
	{
	}

	private static void ShowEnterTextPopup()
	{
	}

	private static bool CheckTextInput(string input)
	{
		return false;
	}

	private static void ShowAskCancelPopup()
	{
	}

	private static void ProcessUnregistServer()
	{
	}

	private static void ProcessUnregisterToy()
	{
	}

	private static void UnregistSuccess()
	{
	}

	private static void UnregistFailed()
	{
	}
}
