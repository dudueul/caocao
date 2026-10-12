using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorGravityWeight : FsmStateActionAnimatorBase
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmFloat gravityWeight;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnActionUpdate()
		{
		}

		private void DoGetGravityWeight()
		{
		}
	}
}
