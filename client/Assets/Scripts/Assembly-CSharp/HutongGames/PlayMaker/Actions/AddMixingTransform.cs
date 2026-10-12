namespace HutongGames.PlayMaker.Actions
{
	public class AddMixingTransform : BaseAnimationAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString animationName;

		[RequiredField]
		public FsmString transform;

		public FsmBool recursive;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoAddMixingTransform()
		{
		}
	}
}
