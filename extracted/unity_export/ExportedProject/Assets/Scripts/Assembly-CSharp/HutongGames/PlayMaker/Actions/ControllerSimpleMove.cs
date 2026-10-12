using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class ControllerSimpleMove : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmVector3 moveVector;

		public FsmFloat speed;

		public Space space;

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
