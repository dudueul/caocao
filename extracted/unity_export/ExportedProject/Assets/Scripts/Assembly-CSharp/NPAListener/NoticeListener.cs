using System;
using NPA;
using NPA.Promotion;

namespace NPAListener
{
	public class NoticeListener : INPOnCloseListener
	{
		public Action closeCallback;

		public Action<NXPToyCloseResult> GetAction()
		{
			return null;
		}

		public void OnClose(NPCloseResult result)
		{
		}
	}
}
