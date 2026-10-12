using LitJson;

namespace Chat
{
	public class Broadcast
	{
		private const int defaultPlatformType = -1;

		public BroadcastType Type { get; set; }

		public string Json { get; set; }

		public string ToRoomId { get; set; }

		public int PlatformType { get; set; }

		public bool isMine
		{
			get
			{
				return false;
			}
		}

		public Broadcast()
		{
		}

		public Broadcast(BroadcastType type, string json, string toRoomId, int platformType = -1)
		{
		}

		public void Deserialize(JsonData jsonData)
		{
		}
	}
}
