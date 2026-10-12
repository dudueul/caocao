using System;

namespace Chat
{
	public class TypeValuePair : EventArgs
	{
		public string Type { get; set; }

		public string Value { get; set; }

		public TypeValuePair()
		{
		}

		public TypeValuePair(string type, string value)
		{
		}
	}
}
