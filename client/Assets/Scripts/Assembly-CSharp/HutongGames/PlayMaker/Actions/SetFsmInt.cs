using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetFsmInt : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString fsmName;

		[RequiredField]
		public FsmString variableName;

		[RequiredField]
		public FsmInt setValue;

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

		private void DoSetFsmInt()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
