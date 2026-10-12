using System;

namespace TSI
{
	public class TSIUpdateUserInfoListener : ITSIListener, ITSIListenerType
	{
		private Action onSuccess;

		private Action onFail;

		private Action onCancel;

		public TSIUpdateUserInfoListener(Action success, Action fail, Action cancel)
		{
		}

		public void OnResult(TSIResult result)
		{
		}
	}
}
