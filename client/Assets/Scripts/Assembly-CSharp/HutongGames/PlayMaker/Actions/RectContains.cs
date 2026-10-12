namespace HutongGames.PlayMaker.Actions
{
	public class RectContains : FsmStateAction
	{
		[RequiredField]
		public FsmRect rectangle;

		public FsmVector3 point;

		public FsmFloat x;

		public FsmFloat y;

		public FsmEvent trueEvent;

		public FsmEvent falseEvent;

		public FsmBool storeResult;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoRectContains()
		{
		}
	}
}
