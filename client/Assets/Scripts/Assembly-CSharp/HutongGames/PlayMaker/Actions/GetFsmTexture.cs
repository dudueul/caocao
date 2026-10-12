using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetFsmTexture : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString fsmName;

		[RequiredField]
		public FsmString variableName;

		[RequiredField]
		public FsmTexture storeValue;

		public bool everyFrame;

		private GameObject goLastFrame;

		protected PlayMakerFSM fsm;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoGetFsmVariable()
		{
		}
	}
}
