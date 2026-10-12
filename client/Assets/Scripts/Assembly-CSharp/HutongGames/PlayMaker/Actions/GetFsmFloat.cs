using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetFsmFloat : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString fsmName;

		[RequiredField]
		public FsmString variableName;

		[RequiredField]
		public FsmFloat storeValue;

		public bool everyFrame;

		private GameObject goLastFrame;

		private PlayMakerFSM fsm;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoGetFsmFloat()
		{
		}
	}
}
