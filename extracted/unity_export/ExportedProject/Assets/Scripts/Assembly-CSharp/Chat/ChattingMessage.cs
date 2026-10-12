using System;

namespace Chat
{
	public class ChattingMessage : EventArgs
	{
		public enum ChattingMessageType
		{
			Channel = 0,
			Whisper = 1,
			Notice = 2,
			Guild = 3,
			Error = 4,
			All = 5,
			None = 6
		}

		public enum ChattingMessageInputType
		{
			Text = 0,
			Emoticon = 1
		}

		public enum ChattingSubMsgType
		{
			TRESURE_ENHANCE = 0,
			TRESURE_GET = 1,
			MULTIPLAY_SHARE = 2,
			SKIN_GET = 3,
			MAX = 4
		}

		public virtual string Message { get; set; }

		public string FromUuid { get; set; }

		public string FromUserName { get; set; }

		public string ToChannelId { get; set; }

		public int MessageIndex { get; set; }

		public ChattingMessageType Type { get; set; }

		public ChattingMessageInputType InputType { get; set; }

		public ChattingSubMsgType subType { get; private set; }

		public string sharedBattleKey { get; private set; }

		public ulong hostUid { get; set; }

		public EnumData.MultiplayShareMode shareMode { get; private set; }

		public ChattingMessage()
		{
		}

		public ChattingMessage(string message, string fromUuid, string fromUserName, string toChannelId, int messageIndex, ChattingMessageType type, ChattingMessageInputType inputType)
		{
		}

		public static string SerializeMsg(ChattingSubMsgType subMsgType, params object[] args)
		{
			return null;
		}

		public string DeserializeMsg()
		{
			return null;
		}

		public void DeserializeSubType()
		{
		}
	}
}
