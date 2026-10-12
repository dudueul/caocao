namespace HutongGames.PlayMaker.Actions
{
	public class AnimatorStartRecording : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmInt frameCount;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
