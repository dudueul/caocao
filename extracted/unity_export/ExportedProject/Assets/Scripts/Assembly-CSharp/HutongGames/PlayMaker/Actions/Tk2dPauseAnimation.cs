namespace HutongGames.PlayMaker.Actions
{
	public class Tk2dPauseAnimation : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool pause;

		public bool everyframe;

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

		private void DoPauseAnimation()
		{
		}
	}
}
