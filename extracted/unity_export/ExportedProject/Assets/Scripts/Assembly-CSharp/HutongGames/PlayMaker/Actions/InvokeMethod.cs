using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class InvokeMethod : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString behaviour;

		[RequiredField]
		public FsmString methodName;

		public FsmFloat delay;

		public FsmBool repeating;

		public FsmFloat repeatDelay;

		public FsmBool cancelOnExit;

		private MonoBehaviour component;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoInvokeMethod(GameObject go)
		{
		}

		public override void OnExit()
		{
		}
	}
}
