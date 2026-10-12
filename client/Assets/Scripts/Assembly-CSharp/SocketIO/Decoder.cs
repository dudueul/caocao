// [RE] Wire decoder for Engine.IO v4 + Socket.IO v4 text frames.
// Grammar reference: docs/lab/07-protocol-reconstruction.md §7.2–7.3.
using LitJson;

namespace SocketIO
{
	public class Decoder
	{
		public Packet Decode(WebSocketSharp.MessageEventArgs e)
		{
			if (e == null || e.Type != WebSocketSharp.Opcode.Text)
				return null;
			string frame = e.Data;
			if (string.IsNullOrEmpty(frame))
				return null;

			var p = new Packet();
			char t = frame[0];
			string rest = frame.Length > 1 ? frame.Substring(1) : "";

			switch (t)
			{
				case '0': // engine.io OPEN — handshake JSON {sid, pingInterval, ...}
					p.enginePacketType = EnginePacketType.OPEN;
					p.json = JsonMapper.ToObject(rest);
					break;
				case '2':
					p.enginePacketType = EnginePacketType.PING;
					break;
				case '3':
					p.enginePacketType = EnginePacketType.PONG;
					break;
				case '6':
					p.enginePacketType = EnginePacketType.CLOSE;
					break;
				case '4':
					p.enginePacketType = EnginePacketType.MESSAGE;
					if (!ParseMessage(rest, p))
						return null;
					break;
				default:
					return null;
			}
			return p;
		}

		// [RE] socket.io layer: <type>[nsp,][id]payload — v4 puts ack id directly
		// after the type digit (e.g. "42"=EVENT, "425"=EVENT acking id 5).
		private bool ParseMessage(string rest, Packet p)
		{
			if (string.IsNullOrEmpty(rest))
				return false;
			p.socketPacketType = (SocketPacketType)(rest[0] - '0');
			string body = rest.Substring(1);

			if (!string.IsNullOrEmpty(body) && body[0] == '/')
			{
				int comma = body.IndexOf(',');
				if (comma < 0) return false;
				p.nsp = body.Substring(0, comma);
				body = body.Substring(comma + 1);
			}
			else
			{
				p.nsp = "/";
			}

			if (p.socketPacketType == SocketPacketType.EVENT ||
				p.socketPacketType == SocketPacketType.ACK)
			{
				// consume leading ack-id digits
				int i = 0;
				while (i < body.Length && body[i] >= '0' && body[i] <= '9') i++;
				if (i > 0)
				{
					int.TryParse(body.Substring(0, i), out p.id);
					body = body.Substring(i);
				}
				else
				{
					p.id = -1;
				}
				if (!string.IsNullOrEmpty(body))
				{
					try { p.json = JsonMapper.ToObject(body); }
					catch { return false; }
				}
			}
			else if (p.socketPacketType == SocketPacketType.CONNECT && !string.IsNullOrEmpty(body))
			{
				try { p.json = JsonMapper.ToObject(body); } catch { }
			}
			return true;
		}
	}
}
