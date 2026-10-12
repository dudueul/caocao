using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class Vector2RotateTowards : FsmStateAction
	{
		[RequiredField]
		public FsmVector2 currentDirection;

		[RequiredField]
		public FsmVector2 targetDirection;

		[RequiredField]
		public FsmFloat rotateSpeed;

		private Vector3 current;

		private Vector3 target;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
