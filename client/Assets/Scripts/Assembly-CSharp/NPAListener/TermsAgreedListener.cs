using System;
using NPA;

namespace NPAListener
{
	public class TermsAgreedListener : INPListener
	{
		public Action successCallback;

		public Action failedCallback;

		public Action<NXPToyResult> GetAction()
		{
			return null;
		}

		public void OnResult(NPResult result)
		{
		}
	}
}
