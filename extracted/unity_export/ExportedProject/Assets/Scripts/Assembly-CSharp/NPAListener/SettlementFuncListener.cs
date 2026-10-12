using System;
using NPA;

namespace NPAListener
{
	public class SettlementFuncListener : INPListener
	{
		public Action successCallback;

		public Action<EnumData.NPErrorCode> failedCallback;

		public Action<NXPToyResult> GetAction()
		{
			return null;
		}

		public void OnResult(NPResult result)
		{
		}
	}
}
