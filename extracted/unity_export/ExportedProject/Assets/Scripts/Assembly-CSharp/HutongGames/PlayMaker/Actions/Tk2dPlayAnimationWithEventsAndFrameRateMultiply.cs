namespace HutongGames.PlayMaker.Actions
{
	public class Tk2dPlayAnimationWithEventsAndFrameRateMultiply : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString clipName;

		public FsmFloat multiplyFactor;

		public FsmEvent animationTriggerEvent;

		public FsmEvent animationCompleteEvent;

		private tk2dSpriteAnimator _sprite;

		private void _getSprite()
		{
		}

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnExit()
		{
		}

		private void DoPlayAnimationWithEvents()
		{
		}

		private void AnimationEventDelegate(tk2dSpriteAnimator sprite, tk2dSpriteAnimationClip clip, int frameNum)
		{
		}

		private void AnimationCompleteDelegate(tk2dSpriteAnimator sprite, tk2dSpriteAnimationClip clip)
		{
		}
	}
}
