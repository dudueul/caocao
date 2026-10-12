namespace HutongGames.PlayMaker.Actions
{
	public class Tk2dPlayAnimation : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString animLibName;

		[RequiredField]
		public FsmString clipName;

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

		private void DoPlayAnimation()
		{
		}
	}
}
