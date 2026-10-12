using System;
using NPA;

namespace NPAListener
{
	public class ShowPushNSmsListener : INPListener
	{
		public Action callback;

		public ShowPushNSmsListener(Action _callback)
		{
		}

		public void OnResult(NPResult result)
		{
		}
	}
}
