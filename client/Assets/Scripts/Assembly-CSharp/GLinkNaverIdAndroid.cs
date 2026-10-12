using UnityEngine;

public class GLinkNaverIdAndroid : IGLinkNaverId
{
	private class OnLoggedInListener : AndroidJavaProxy
	{
		public OnLoggedInListener()
			: base((string)null)
		{
		}

		private void onLoggedIn(bool success)
		{
		}
	}

	private class OnGetProfileListener : AndroidJavaProxy
	{
		public OnGetProfileListener()
			: base((string)null)
		{
		}

		private void onResult(string jsonString)
		{
		}
	}

	private AndroidJavaClass delegateClass;

	private AndroidJavaObject currentActivity;

	public void init(string clientId, string clientSecret)
	{
	}

	public void login()
	{
	}

	public void logout()
	{
	}

	public bool isLogin()
	{
		return false;
	}

	public void getProfile()
	{
	}
}
