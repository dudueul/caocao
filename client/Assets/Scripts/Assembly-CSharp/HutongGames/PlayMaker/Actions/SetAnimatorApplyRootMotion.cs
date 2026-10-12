using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetAnimatorApplyRootMotion : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool applyRootMotion;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoApplyRootMotion()
		{
		}
	}
}
