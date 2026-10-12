using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetAnimatorTarget : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public AvatarTarget avatarTarget;

		public FsmFloat targetNormalizedTime;

		public bool everyFrame;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnPreprocess()
		{
		}

		public override void OnEnter()
		{
		}

		public override void DoAnimatorMove()
		{
		}

		private void SetTarget()
		{
		}
	}
}
