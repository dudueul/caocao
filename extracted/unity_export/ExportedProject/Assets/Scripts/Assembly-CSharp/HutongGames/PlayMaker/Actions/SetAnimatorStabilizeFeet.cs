using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetAnimatorStabilizeFeet : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool stabilizeFeet;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoStabilizeFeet()
		{
		}
	}
}
