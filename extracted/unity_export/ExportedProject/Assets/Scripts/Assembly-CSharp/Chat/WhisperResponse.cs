using System;

namespace Chat
{
	public class WhisperResponse : EventArgs
	{
		public string Message { get; set; }

		public string UUID { get; set; }

		public string UserName { get; set; }

		public int MessageIndex { get; set; }

		public WhisperResponseType Type { get; set; }

		public ChattingMessage.ChattingSubMsgType SubType { get; private set; }

		public ChattingMessage.ChattingMessageInputType InputType { get; set; }

		public WhisperResponse()
		{
		}

		public WhisperResponse(string message, string uuid, string userName, int messageIndex, WhisperResponseType type, ChattingMessage.ChattingMessageInputType inputType)
		{
		}

		public void DeserializeSubType()
		{
		}
	}
}
