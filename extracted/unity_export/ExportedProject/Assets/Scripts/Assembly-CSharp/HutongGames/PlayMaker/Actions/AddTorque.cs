using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class AddTorque : ComponentAction<Rigidbody>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmVector3 vector;

		public FsmFloat x;

		public FsmFloat y;

		public FsmFloat z;

		public Space space;

		public ForceMode forceMode;

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

		private void DoAddTorque()
		{
		}
	}
}
