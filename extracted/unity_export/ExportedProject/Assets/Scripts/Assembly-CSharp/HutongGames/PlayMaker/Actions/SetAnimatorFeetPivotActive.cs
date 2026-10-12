using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetAnimatorFeetPivotActive : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmFloat feetPivotActive;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoFeetPivotActive()
		{
		}
	}
}
