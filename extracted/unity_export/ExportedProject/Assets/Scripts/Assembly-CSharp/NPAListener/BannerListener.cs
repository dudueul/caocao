using System;
using NPA;

namespace NPAListener
{
	public class BannerListener : INPBannerListener
	{
		public Action<string> GetClickAction()
		{
			return null;
		}

		public void OnBannerClick(string landInfo)
		{
		}

		public Action<NXPToyResult> GetFailedAction()
		{
			return null;
		}

		public void OnBannerFailed(NPResult npResult)
		{
		}

		public Action GetDismissAction()
		{
			return null;
		}

		public void OnBannerDismiss()
		{
		}
	}
}
