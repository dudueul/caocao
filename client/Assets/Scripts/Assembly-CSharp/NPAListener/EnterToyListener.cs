using System;
using NPA;

namespace NPAListener
{
	public class EnterToyListener : INPListener
	{
		private Action onSucceed;

		private Action onCrashed;

		private Action onFailed;

		private Action onMaintenance;

		public EnterToyListener(Action succeed, Action crashed, Action failed, Action maintenance)
		{
		}

		public Action<NXPToyResult> GetAction()
		{
			return null;
		}

		public void OnResult(NPResult npResult)
		{
		}
	}
}
