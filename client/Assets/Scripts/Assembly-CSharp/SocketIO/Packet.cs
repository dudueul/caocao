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
			this.enginePacketType = enginePacketType;
		}

		public Packet(EnginePacketType enginePacketType, SocketPacketType socketPacketType, int attachments, string nsp, int id, JsonData json)
		{
			// [RE] the AssetRipper stub left these ctors EMPTY — with
			// default(EnginePacketType) == 0 == OPEN, every packet encoded as
			// "OPEN with null json" → NullReferenceException (fix loop 9).
			this.enginePacketType = enginePacketType;
			this.socketPacketType = socketPacketType;
			this.attachments = attachments;
			this.nsp = nsp;
			this.id = id;
			this.json = json;
		}

		public override string ToString()
		{
			return string.Format("[{0}/{1} nsp={2} id={3}]", enginePacketType, socketPacketType, nsp, id);
		}
	}
}
