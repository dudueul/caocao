using System;

namespace Chat
{
	public class ChattingUserLeaveFromChannel : EventArgs
	{
		public ChattingUser User { get; set; }

		public string ChannelId { get; set; }

		public ChattingUserLeaveFromChannel()
		{
		}

		public ChattingUserLeaveFromChannel(ChattingUser user, string channelId)
		{
		}
	}
}
