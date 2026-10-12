using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetAnimatorTrigger : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString trigger;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void SetTrigger()
		{
		}
	}
}
