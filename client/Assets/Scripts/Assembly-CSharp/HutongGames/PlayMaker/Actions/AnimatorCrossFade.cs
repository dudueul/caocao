using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class AnimatorCrossFade : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString stateName;

		public FsmFloat transitionDuration;

		public FsmInt layer;

		public FsmFloat normalizedTime;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
