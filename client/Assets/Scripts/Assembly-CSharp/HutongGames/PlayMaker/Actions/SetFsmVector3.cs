using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetFsmVector3 : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString fsmName;

		[RequiredField]
		public FsmString variableName;

		[RequiredField]
		public FsmVector3 setValue;

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

		private void DoSetFsmVector3()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
