using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetVelocity2d : ComponentAction<Rigidbody2D>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmVector2 vector;

		public FsmFloat x;

		public FsmFloat y;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void Awake()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnFixedUpdate()
		{
		}

		private void DoSetVelocity()
		{
		}
	}
}
