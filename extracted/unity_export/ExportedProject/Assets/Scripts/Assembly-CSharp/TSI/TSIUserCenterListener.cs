using System;

namespace TSI
{
	public class TSIUserCenterListener : ITSIListener, ITSIListenerType
	{
		private Action onNoSupport;

		public TSIUserCenterListener(Action noSupportCallBack)
		{
		}

		public void OnResult(TSIResult result)
		{
		}
	}
}
