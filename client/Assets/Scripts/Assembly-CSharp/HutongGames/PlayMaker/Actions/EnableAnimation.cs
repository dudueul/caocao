using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class EnableAnimation : BaseAnimationAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString animName;

		[RequiredField]
		public FsmBool enable;

		public FsmBool resetOnExit;

		private AnimationState anim;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoEnableAnimation(GameObject go)
		{
		}

		public override void OnExit()
		{
		}
	}
}
