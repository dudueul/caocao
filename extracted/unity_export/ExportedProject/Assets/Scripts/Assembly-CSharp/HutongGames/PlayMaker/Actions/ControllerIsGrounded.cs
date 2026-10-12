using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class ControllerIsGrounded : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmEvent trueEvent;

		public FsmEvent falseEvent;

		public FsmBool storeResult;

		public bool everyFrame;

		private GameObject previousGo;

		private CharacterController controller;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoControllerIsGrounded()
		{
		}
	}
}
