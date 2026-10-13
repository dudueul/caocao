// [RE] Rebuilt from decompiled `SocketIO_Encoder__Encode` call site
// (GuildWar.Backend.PImpl.SessionImpl$$UpdateSocketLogin @0x1A31D08):
//   Packet { enginePacketType=4(MESSAGE), socketPacketType=2(EVENT),
//            nsp="", id=-1, json=[event, payload] } → ws.Send(encoded).
// Framing standard: Engine.IO v4 (matches recovered URL literal
// `ws://{0}/socket.io/?EIO=4&transport=websocket`) — see Lab 07 §7.2–7.3.
using System.Text;

namespace SocketIO
{
	public class Encoder
	{
		public string Encode(Packet packet)
		{
			var sb = new StringBuilder();
			switch (packet.enginePacketType)
			{
				case EnginePacketType.OPEN:
					sb.Append('0');
					if (packet.json != null)
						sb.Append(packet.json.ToJson());
					break;
				case EnginePacketType.CLOSE:
					sb.Append('6');
					break;
				case EnginePacketType.PING:
					sb.Append('2');
					break;
				case EnginePacketType.PONG:
					sb.Append('3');
					break;
				case EnginePacketType.MESSAGE:
					sb.Append('4').Append((int)packet.socketPacketType);
					// [RE] decompiled Packet fields: nsp="" and id=-1 for game events
					if (!string.IsNullOrEmpty(packet.nsp) && packet.nsp != "/")
						sb.Append(packet.nsp).Append(',');
					if (packet.id != -1)
						sb.Append(packet.id);
					if (packet.json != null)
						sb.Append(packet.json.ToJson());
					break;
				default:
					break;
			}
			return sb.ToString();
		}
	}
}
