using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetGravity2dScale : ComponentAction<Rigidbody2D>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmFloat gravityScale;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSetGravityScale()
		{
		}
	}
}
