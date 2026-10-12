namespace HutongGames.PlayMaker.Actions
{
	public class AnimatorInterruptMatchTarget : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool completeMatch;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
