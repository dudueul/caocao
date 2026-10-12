using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

namespace Chat
{
	public class Room
	{
		public ObscuredString id;

		public int index;

		public ObscuredInt userCnt;

		public ERoomStatus status;

		public ERoomType roomType;

		public void Deserialize(JsonData jsonData)
		{
		}

		public static int Sort(Room x, Room y)
		{
			return 0;
		}
	}
}
