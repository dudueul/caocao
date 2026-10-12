using System;
using NPA;
using NPA.Auth;

namespace NPAListener
{
	public class LoginListener : INPListener
	{
		private Action<string, string, string> onSucceed;

		private Action onCrashed;

		private Action onFailed;

		private Action onCancel;

		public LoginListener(Action<string, string, string> succeed, Action crashed, Action failed, Action cancel)
		{
		}

		public Action<NXPToyLoginResult> GetAction()
		{
			return null;
		}

		public void OnResult(NPResult npResult)
		{
		}
	}
}
