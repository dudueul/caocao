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
			this.packetId = packetId;
			this.action = action;
			time = DateTime.UtcNow;
		}

		public void Invoke(JsonData ev)
		{
			if (action != null)
				action(ev);
		}

		public override string ToString()
		{
			return string.Format("[Ack: id={0}]", packetId);
		}
	}
}
