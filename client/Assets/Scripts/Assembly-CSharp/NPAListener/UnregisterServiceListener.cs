using System;
using NPA;

namespace NPAListener
{
	public class UnregisterServiceListener : INPListener
	{
		public Action<string> callback;

		public UnregisterServiceListener(Action<string> _callback)
		{
		}

		public Action<NXPToyResult> GetAction()
		{
			return null;
		}

		public void OnResult(NPResult result)
		{
		}
	}
}
