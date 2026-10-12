using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorFeetPivotActive : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmFloat feetPivotActive;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoGetFeetPivotActive()
		{
		}
	}
}
