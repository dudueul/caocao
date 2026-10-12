namespace HutongGames.PlayMaker.Actions
{
	public class Vector2Interpolate : FsmStateAction
	{
		public InterpolationType mode;

		[RequiredField]
		public FsmVector2 fromVector;

		[RequiredField]
		public FsmVector2 toVector;

		[RequiredField]
		public FsmFloat time;

		[RequiredField]
		public FsmVector2 storeResult;

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
