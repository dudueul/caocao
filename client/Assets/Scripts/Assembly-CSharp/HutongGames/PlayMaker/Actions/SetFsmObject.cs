using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetFsmObject : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString fsmName;

		[RequiredField]
		public FsmString variableName;

		public FsmObject setValue;

		public bool everyFrame;

		private GameObject goLastFrame;

		private string fsmNameLastFrame;

		private PlayMakerFSM fsm;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSetFsmBool()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
