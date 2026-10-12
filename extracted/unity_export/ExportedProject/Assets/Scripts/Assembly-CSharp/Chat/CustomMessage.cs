using System;

namespace Chat
{
	public class CustomMessage : EventArgs
	{
		public object MessageObject { get; set; }

		public CustomMessage()
		{
		}

		public CustomMessage(object messageValue)
		{
		}
	}
}
