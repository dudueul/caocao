using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetAnimatorCullingMode : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool alwaysAnimate;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void SetCullingMode()
		{
		}
	}
}
