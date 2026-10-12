using LitJson;

namespace SocketIO
{
	public class SocketIOEvent
	{
		public string name { get; set; }

		public JsonData data { get; set; }

		public SocketIOEvent(string name)
		{
		}

		public SocketIOEvent(string name, JsonData data)
		{
		}

		public override string ToString()
		{
			return null;
		}
	}
}
