using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class ControllerSettings : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmFloat height;

		public FsmFloat radius;

		public FsmFloat slopeLimit;

		public FsmFloat stepOffset;

		public FsmVector3 center;

		public FsmBool detectCollisions;

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

		private void DoControllerSettings()
		{
		}
	}
}
