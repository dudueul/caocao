using System;

namespace WebSocketSharp
{
	public class MessageEventArgs : EventArgs
	{
		private string _data;

		private Opcode _opcode;

		private byte[] _rawData;

		public string Data
		{
			get
			{
				return null;
			}
		}

		public byte[] RawData
		{
			get
			{
				return null;
			}
		}

		public Opcode Type
		{
			get
			{
				return Opcode.Cont;
			}
		}

		internal MessageEventArgs(Opcode opcode, byte[] data)
		{
		}

		internal MessageEventArgs(Opcode opcode, PayloadData payload)
		{
		}

		private static string convertToString(Opcode opcode, byte[] data)
		{
			return null;
		}
	}
}
