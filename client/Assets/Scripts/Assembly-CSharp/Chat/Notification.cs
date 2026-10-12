using System;

namespace Chat
{
	public class Notification : EventArgs
	{
		public bool Seen { get; set; }

		public object Value { get; set; }

		public Notification()
		{
		}

		public Notification(bool seen, object value)
		{
		}
	}
}
