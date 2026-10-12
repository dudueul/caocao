using System;

namespace Chat
{
	public class ChattingUserJoinToChannel : EventArgs
	{
		private int m_index;

		public string LocaleName;

		public ChattingUser User { get; set; }

		public string ChannelId { get; set; }

		public int GetIndex
		{
			get
			{
				return 0;
			}
		}

		public ChattingUserJoinToChannel()
		{
		}

		public ChattingUserJoinToChannel(ChattingUser user, string channelId)
		{
		}
	}
}
