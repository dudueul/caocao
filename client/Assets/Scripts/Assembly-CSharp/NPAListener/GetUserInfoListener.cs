using System;
using NPA;
using NPA.Auth;

namespace NPAListener
{
	public class GetUserInfoListener : INPListener
	{
		private Action<string, string, string> onSucceed;

		private Action onCrashed;

		private Action onFailed;

		public GetUserInfoListener(Action<string, string, string> succeed, Action crashed, Action failed)
		{
		}

		public Action<NXPToyUserInfoResult> GetAction()
		{
			return null;
		}

		public void OnResult(NPResult result)
		{
		}
	}
}
