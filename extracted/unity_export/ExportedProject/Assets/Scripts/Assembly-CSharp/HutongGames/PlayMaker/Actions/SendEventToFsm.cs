using System;
using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	[Obsolete]
	public class SendEventToFsm : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString fsmName;

		[RequiredField]
		public FsmString sendEvent;

		public FsmFloat delay;

		private bool requireReceiver;

		private GameObject go;

		private DelayedEvent delayedEvent;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
