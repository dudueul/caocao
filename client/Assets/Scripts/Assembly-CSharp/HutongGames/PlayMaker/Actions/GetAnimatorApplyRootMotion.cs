using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorApplyRootMotion : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmBool rootMotionApplied;

		public FsmEvent rootMotionIsAppliedEvent;

		public FsmEvent rootMotionIsNotAppliedEvent;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void GetApplyMotionRoot()
		{
		}
	}
}
