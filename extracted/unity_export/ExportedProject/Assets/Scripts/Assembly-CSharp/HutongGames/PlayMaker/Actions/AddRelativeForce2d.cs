using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class AddRelativeForce2d : ComponentAction<Rigidbody2D>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public ForceMode2D forceMode;

		public FsmVector2 vector;

		public FsmFloat x;

		public FsmFloat y;

		public FsmVector3 vector3;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnPreprocess()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnFixedUpdate()
		{
		}

		private void DoAddRelativeForce()
		{
		}
	}
}
