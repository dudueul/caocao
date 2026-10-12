namespace HutongGames.PlayMaker.Actions
{
	public class AddAnimationClip : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmObject animationClip;

		[RequiredField]
		public FsmString animationName;

		public FsmInt firstFrame;

		public FsmInt lastFrame;

		public FsmBool addLoopFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoAddAnimationClip()
		{
		}
	}
}
