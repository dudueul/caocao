using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorCullingMode : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmBool alwaysAnimate;

		public FsmEvent alwaysAnimateEvent;

		public FsmEvent basedOnRenderersEvent;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoCheckCulling()
		{
		}
	}
}
