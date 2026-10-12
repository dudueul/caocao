namespace HutongGames.PlayMaker.Actions
{
	public class Tk2dSetAnimationFrameRate : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmFloat framePerSeconds;

		public bool everyFrame;

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

		public override void OnUpdate()
		{
		}

		private void DoSetAnimationFPS()
		{
		}
	}
}
