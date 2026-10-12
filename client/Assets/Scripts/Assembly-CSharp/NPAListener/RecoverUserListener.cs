using System;
using NPA;

namespace NPAListener
{
	public class RecoverUserListener : INPListener
	{
		private Action<int> callback;

		public RecoverUserListener(Action<int> _callback)
		{
		}

		public void OnResult(NPResult npResult)
		{
		}
	}
}
