using System;

namespace TSI
{
	public class TSIFloatToolbarListener : ITSIListener, ITSIListenerType
	{
		private Action onNoSupport;

		public TSIFloatToolbarListener(Action noSupportCallBack)
		{
		}

		public void OnResult(TSIResult result)
		{
		}
	}
}
