using System;

namespace HutongGames.PlayMaker.Actions
{
	[Obsolete]
	public class BroadcastEvent : FsmStateAction
	{
		[RequiredField]
		public FsmString broadcastEvent;

		public FsmGameObject gameObject;

		public FsmBool sendToChildren;

		public FsmBool excludeSelf;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
