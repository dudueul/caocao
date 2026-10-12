using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetDrag : ComponentAction<Rigidbody>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmFloat drag;

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

		private void DoSetDrag()
		{
		}
	}
}
