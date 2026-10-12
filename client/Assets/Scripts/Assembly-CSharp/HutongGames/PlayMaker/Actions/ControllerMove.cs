using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class ControllerMove : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmVector3 moveVector;

		public Space space;

		public FsmBool perSecond;

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
