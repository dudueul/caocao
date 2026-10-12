namespace HutongGames.PlayMaker.Actions
{
	public class AnimatorStopRecording : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmFloat recorderStartTime;

		public FsmFloat recorderStopTime;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
