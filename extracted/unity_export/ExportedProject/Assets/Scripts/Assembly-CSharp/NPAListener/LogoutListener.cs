using System;
using NPA;

namespace NPAListener
{
	public class LogoutListener : INPListener
	{
		public Action<NXPToyResult> GetAction()
		{
			return null;
		}

		public void OnResult(NPResult npResult)
		{
		}
	}
}
