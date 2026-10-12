using System;

namespace TSI
{
	public class TSILogoutListener : ITSIListener, ITSIListenerType
	{
		private Action onSuccess;

		private Action onFail;

		private Action onCancel;

		public TSILogoutListener(Action success, Action fail, Action cancel)
		{
		}

		public void OnResult(TSIResult result)
		{
		}
	}
}
