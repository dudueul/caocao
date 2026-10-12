using System;

namespace WebSocketSharp
{
	public class CloseEventArgs : EventArgs
	{
		private bool _clean;

		private ushort _code;

		private string _reason;

		public ushort Code
		{
			get
			{
				return 0;
			}
		}

		public string Reason
		{
			get
			{
				return null;
			}
		}

		public bool WasClean
		{
			get
			{
				return false;
			}
			internal set
			{
			}
		}

		internal CloseEventArgs(PayloadData payload)
		{
		}
	}
}
