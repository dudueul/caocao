namespace HutongGames.PlayMaker.Actions
{
	public class FloatInterpolate : FsmStateAction
	{
		public InterpolationType mode;

		[RequiredField]
		public FsmFloat fromFloat;

		[RequiredField]
		public FsmFloat toFloat;

		[RequiredField]
		public FsmFloat time;

		[RequiredField]
		public FsmFloat storeResult;

		public FsmEvent finishEvent;

		public bool realTime;

		private float startTime;

		private float currentTime;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
