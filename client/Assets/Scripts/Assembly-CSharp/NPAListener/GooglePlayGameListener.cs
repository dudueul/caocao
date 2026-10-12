using System;
using NPA;

namespace NPAListener
{
	public class GooglePlayGameListener : INPListener
	{
		public Action<bool> callBack;

		public GooglePlayGameListener(Action<bool> _callback)
		{
		}

		public void OnResult(NPResult result)
		{
		}

		private void OnLoginResult(int errorCode, string jsonData)
		{
		}

		private void OnLogoutResult(int errorCode, string jsonData)
		{
		}

		private void OnLoadAchievementData(int errorCode, string jsonData)
		{
		}
	}
}
