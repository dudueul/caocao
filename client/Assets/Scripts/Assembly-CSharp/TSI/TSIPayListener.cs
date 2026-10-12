using System;

namespace TSI
{
	public class TSIPayListener : ITSIListener, ITSIListenerType
	{
		private Action onNoSupport;

		public TSIPayListener(Action noSupportCallBack)
		{
		}

		public void OnResult(TSIResult result)
		{
		}
	}
}
