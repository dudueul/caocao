using System;
using LitJson;

namespace SocketIO
{
	public class Ack
	{
		public int packetId;

		public DateTime time;

		private Action<JsonData> action;

		public Ack(int packetId, Action<JsonData> action)
		{
		}

		public void Invoke(JsonData ev)
		{
		}

		public override string ToString()
		{
			return null;
		}
	}
}
