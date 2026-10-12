using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetFsmColor : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString fsmName;

		[RequiredField]
		public FsmString variableName;

		[RequiredField]
		public FsmColor storeValue;

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

		private void DoGetFsmColor()
		{
		}
	}
}
