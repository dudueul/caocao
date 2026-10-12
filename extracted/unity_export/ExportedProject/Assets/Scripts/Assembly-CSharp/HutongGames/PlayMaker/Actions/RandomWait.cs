namespace HutongGames.PlayMaker.Actions
{
	public class RandomWait : FsmStateAction
	{
		[RequiredField]
		public FsmFloat min;

		[RequiredField]
		public FsmFloat max;

		public FsmEvent finishEvent;

		public bool realTime;

		private float startTime;

		private float timer;

		private float time;

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
