namespace HutongGames.PlayMaker.Actions
{
	public class Tk2dIsPlaying : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString clipName;

		public FsmBool isPlaying;

		public FsmEvent isPlayingEvent;

		public FsmEvent isNotPlayingEvent;

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

		private void DoIsPlaying()
		{
		}
	}
}
