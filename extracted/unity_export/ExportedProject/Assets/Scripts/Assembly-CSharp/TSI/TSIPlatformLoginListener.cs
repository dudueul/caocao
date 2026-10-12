using System;

namespace TSI
{
	public class TSIPlatformLoginListener : ITSIListener, ITSIListenerType
	{
		private Action<string, int> onSuccess;

		private Action onFail;

		private Action onCancel;

		public TSIPlatformLoginListener(Action<string, int> success, Action fail, Action cancel)
		{
		}

		public void OnResult(TSIResult result)
		{
		}
	}
}
