using System;
using NPA;

namespace NPAListener
{
	public class EndingBannerListener : INPEndingBannerListener
	{
		private Toy toy;

		public Action<string> GetClickAction()
		{
			return null;
		}

		public void OnEndingBannerClick(string landInfo)
		{
		}

		public Action<NXPToyResult> GetFailedAction()
		{
			return null;
		}

		public void OnEndingBannerFailed(NPResult npResult)
		{
		}

		public Action GetDismissAction()
		{
			return null;
		}

		public void OnEndingBannerDismiss()
		{
		}

		public Action GetExitAction()
		{
			return null;
		}

		public void OnEndingBannerExit()
		{
		}
	}
}
