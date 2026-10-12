using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetControllerCollisionFlags : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool isGrounded;

		public FsmBool none;

		public FsmBool sides;

		public FsmBool above;

		public FsmBool below;

		private GameObject previousGo;

		private CharacterController controller;

		public override void Reset()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
