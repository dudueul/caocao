namespace HutongGames.PlayMaker.Actions
{
	public class Vector3Interpolate : FsmStateAction
	{
		public InterpolationType mode;

		[RequiredField]
		public FsmVector3 fromVector;

		[RequiredField]
		public FsmVector3 toVector;

		[RequiredField]
		public FsmFloat time;

		[RequiredField]
		public FsmVector3 storeResult;

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
