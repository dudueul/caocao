// [RE] Parser: socket.io EVENT payload `["name", arg0, ...]` → SocketIOEvent.
// Decompiled anchor: SocketIO.Parser (TypeDefIndex 4687) + JsonData usage in
// SessionImpl handlers (OnHeartBeats(JsonData response) etc.).
using System.Collections.Generic;
using LitJson;

namespace SocketIO
{
	public class Parser
	{
		public SocketIOEvent Parse(JsonData json)
		{
			if (json == null || !json.IsArray || json.Count < 1)
				return null;
			string name = json[0].ToString();
			JsonData args = null;
			if (json.Count > 1)
			{
				// [RE] original exposes args as a JsonData array; SessionImpl
				// reads response fields by key (OnHeartBeats(JsonData)).
				var wrapper = new JsonData();
				var list = new List<JsonData>();
				for (int i = 1; i < json.Count; i++)
					list.Add(json[i]);
				if (list.Count == 1)
					wrapper = json[1];
				else
				{
					wrapper = JsonMapper.ToObject("[]");
					foreach (var item in list) wrapper.Add(item);
				}
				args = wrapper;
			}
			return new SocketIOEvent(name, args);
		}
	}
}
