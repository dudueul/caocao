using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetVelocity2d : ComponentAction<Rigidbody2D>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmVector2 vector;

		public FsmFloat x;

		public FsmFloat y;

		public Space space;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoGetVelocity()
		{
		}
	}
}
