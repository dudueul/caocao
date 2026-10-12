using System;
using System.Collections.Generic;

namespace Chat
{
	public class ChattingUser : EventArgs
	{
		public string UUID { get; set; }

		public string UserName { get; set; }

		public ChattingUserState State { get; set; }

		public List<string> JoinedChannelIds { get; set; }

		public string NormalChannelId { get; set; }

		public EChatRoomLocale RoomLocale { get; set; }
	}
}
