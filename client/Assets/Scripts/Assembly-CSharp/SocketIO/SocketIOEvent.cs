using LitJson;

namespace SocketIO
{
	public class SocketIOEvent
	{
		public string name { get; set; }

		public JsonData data { get; set; }

		public SocketIOEvent(string name)
		{
			// [RE] AssetRipper stub left ctors empty — event name was null →
			// Dictionary.TryGetValue(null) crash (fix loop 11)
			this.name = name;
		}

		public SocketIOEvent(string name, JsonData data)
		{
			this.name = name;
			this.data = data;
		}

		public override string ToString()
		{
			return string.Format("[SocketIOEvent: {0}]", name);
		}
	}
}
