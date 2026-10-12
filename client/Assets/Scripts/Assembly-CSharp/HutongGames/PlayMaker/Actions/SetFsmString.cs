using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetFsmString : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString fsmName;

		[RequiredField]
		public FsmString variableName;

		public FsmString setValue;

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

		private void DoSetFsmString()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
