using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SendMessage : FsmStateAction
	{
		public enum MessageType
		{
			SendMessage = 0,
			SendMessageUpwards = 1,
			BroadcastMessage = 2
		}

		[RequiredField]
		public FsmOwnerDefault gameObject;

		public MessageType delivery;

		public SendMessageOptions options;

		[RequiredField]
		public FunctionCall functionCall;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSendMessage()
		{
		}
	}
}
