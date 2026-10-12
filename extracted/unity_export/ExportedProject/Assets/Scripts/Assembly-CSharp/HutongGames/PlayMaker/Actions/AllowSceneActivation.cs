namespace HutongGames.PlayMaker.Actions
{
	public class AllowSceneActivation : FsmStateAction
	{
		[RequiredField]
		public FsmInt aSynchOperationHashCode;

		public FsmBool allowSceneActivation;

		public FsmFloat progress;

		public FsmBool isDone;

		public FsmEvent doneEvent;

		public FsmEvent failureEvent;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoAllowSceneActivation()
		{
		}
	}
}
