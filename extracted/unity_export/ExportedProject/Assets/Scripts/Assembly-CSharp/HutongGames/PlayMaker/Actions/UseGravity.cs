using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class UseGravity : ComponentAction<Rigidbody>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmBool useGravity;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoUseGravity()
		{
		}
	}
}
