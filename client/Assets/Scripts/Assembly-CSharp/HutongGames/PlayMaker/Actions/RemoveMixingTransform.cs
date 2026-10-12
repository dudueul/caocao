namespace HutongGames.PlayMaker.Actions
{
	public class RemoveMixingTransform : BaseAnimationAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString animationName;

		[RequiredField]
		public FsmString transfrom;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoRemoveMixingTransform()
		{
		}
	}
}
