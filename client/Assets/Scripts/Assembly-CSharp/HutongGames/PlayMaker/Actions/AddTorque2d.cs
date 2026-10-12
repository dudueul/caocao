using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class AddTorque2d : ComponentAction<Rigidbody2D>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public ForceMode2D forceMode;

		public FsmFloat torque;

		public bool everyFrame;

		public override void OnPreprocess()
		{
		}

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnFixedUpdate()
		{
		}

		private void DoAddTorque()
		{
		}
	}
}
