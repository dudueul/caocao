using System;
using NPA;
using NPA.Auth;

namespace NPAListener
{
	public class GetCountryFromServerListener : INPListener
	{
		private Action m_callback;

		public GetCountryFromServerListener(Action callback)
		{
		}

		public Action<NXPToyCountryResult> GetAction()
		{
			return null;
		}

		public void OnResult(NPResult result)
		{
		}
	}
}
