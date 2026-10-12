using LitJson;

namespace SocketIO
{
	public class Packet
	{
		public EnginePacketType enginePacketType;

		public SocketPacketType socketPacketType;

		public int attachments;

		public string nsp;

		public int id;

		public JsonData json;

		public Packet()
		{
		}

		public Packet(EnginePacketType enginePacketType)
		{
		}

		public Packet(EnginePacketType enginePacketType, SocketPacketType socketPacketType, int attachments, string nsp, int id, JsonData json)
		{
		}

		public override string ToString()
		{
			return null;
		}
	}
}
